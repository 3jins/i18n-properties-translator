package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParamDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationResponseDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertyDto;
import com.sejin.i18npropertiestranslator.translator.papago.nmt.PapagoNmtService;
import com.sejin.i18npropertiestranslator.translator.parser.PropertiesParsingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = {PropertiesTranslationService.class}
)
@ActiveProfiles("TEST")
class PropertiesTranslationServiceTest {
    @SpyBean
    private PropertiesTranslationService propertiesTranslationService;

    @MockBean
    private PropertiesParsingService propertiesParsingService;

    @MockBean
    private PapagoNmtService papagoNmtService;

    @Test
    void translatePropertiesData() {
        final StringBuilder propertiesRawContentBuilder = new StringBuilder();
        final String propertiesRawKey = "lyrics";
        final String propertiesRawValue = "나는 나는 저팔계 왜 날 싫어하나";
        final String propertiesRawContent = propertiesRawKey + "=" + propertiesRawValue;
        final String propertiesRawComment = "날아라 슈퍼보드에 나온 노래";
        final LanguageType sourceLanguageType = LanguageType.KO;
        final List<LanguageType> targetLanguageTypeList = List.of(
                LanguageType.KO,
                LanguageType.EN,
                LanguageType.ZH_CN,
                LanguageType.JA
        );
        final PropertiesTranslationParamDto paramDto = PropertiesTranslationParamDto.builder()
                .propertiesRawContent(propertiesRawContent)
                .sourceLanguageType(sourceLanguageType)
                .targetLanguageTypeList(targetLanguageTypeList)
                .build();

        final List<PropertyDto> propertiesData = List.of(
                PropertyDto.builder()
                        .key(propertiesRawKey)
                        .value(propertiesRawValue)
                        .comment(propertiesRawComment)
                        .build()
        );
        doReturn(propertiesData).when(propertiesParsingService).parsePropertiesRawContent(anyString());
        final String translatedToHangukmal = "나는 나는 저팔계 왜 날 싫어하나";
        final String translatedToEnglish = "I\'m I\'m Zhu Bajie Why does everybody hate me";
        final String translatedToZhonguo = "我是 我是 猪八戒";
        final String translatedToNihongo = "わたしは わたしは 猪八戒";
        doReturn(translatedToHangukmal).when(papagoNmtService).translate(eq(propertiesRawValue), eq(LanguageType.KO), eq(LanguageType.KO));
        doReturn(translatedToEnglish).when(papagoNmtService).translate(eq(propertiesRawValue), eq(LanguageType.KO), eq(LanguageType.EN));
        doReturn(translatedToZhonguo).when(papagoNmtService).translate(eq(propertiesRawValue), eq(LanguageType.KO), eq(LanguageType.ZH_CN));
        doReturn(translatedToNihongo).when(papagoNmtService).translate(eq(propertiesRawValue), eq(LanguageType.KO), eq(LanguageType.JA));

        final String test = papagoNmtService.translate(propertiesRawContent, LanguageType.KO, LanguageType.KO);
        final List<PropertiesTranslationResponseDto> result = propertiesTranslationService
                .translatePropertiesData(paramDto);
        assertThat(result).hasSameSizeAs(targetLanguageTypeList);
    }
}