package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.common.constant.TranslatorName;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParamDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParsedParamDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationResponseDto;
import com.sejin.i18npropertiestranslator.propertiesparser.dto.PropertyDto;
import com.sejin.i18npropertiestranslator.propertiesparser.PropertiesParsingService;
import com.sejin.i18npropertiestranslator.translator.papago.nmt.PapagoTranslationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
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
    private TranslationService translationService;

    @MockBean
    private PapagoTranslationService papagoTranslationService;

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
                .translatorName(TranslatorName.PAPAGO)
                .build();

        final List<PropertyDto> propertiesData = List.of(
                PropertyDto.builder()
                        .key(propertiesRawKey)
                        .value(propertiesRawValue)
                        .comment(propertiesRawComment)
                        .build()
        );
        doReturn(propertiesData).when(propertiesParsingService).parsePropertiesRawContent(anyString());
        doReturn(true).when(translationService).supports(eq(TranslatorName.PAPAGO));
        doReturn("무언가로 번역이 되었을겁니다.").when(translationService).translate(any(PropertiesTranslationParsedParamDto.class));

        final List<PropertiesTranslationResponseDto> result = propertiesTranslationService.translatePropertiesData(paramDto, papagoTranslationService);
        assertThat(result).hasSameSizeAs(targetLanguageTypeList);
    }
}