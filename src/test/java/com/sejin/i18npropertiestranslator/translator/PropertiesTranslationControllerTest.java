package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.CommonTestUtil;
import com.sejin.i18npropertiestranslator.common.config.ModelMapperConfig;
import com.sejin.i18npropertiestranslator.common.constant.TranslatorName;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationRequestDto;
import com.sejin.i18npropertiestranslator.translator.papago.nmt.PapagoTranslationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = {PropertiesTranslationController.class, ModelMapperConfig.class})
@ActiveProfiles("TEST")
class PropertiesTranslationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TranslationServiceSelector translationServiceSelector;

    @MockBean
    private PropertiesTranslationService propertiesTranslationService;

    @Mock
    private PapagoTranslationService papagoTranslationService;

    @Test
    void translateProperties() throws Exception {
        final TranslatorName translatorName = TranslatorName.PAPAGO;
        final PropertiesTranslationRequestDto requestDto = PropertiesTranslationRequestDto.builder()
                .propertiesRawContent("lyrics = 나는 나는 저팔계 왜 날 싫어하나")
                .sourceLanguageTypeCode("ko")
                .targetLanguageTypeCodeList(List.of("en", "zh-cn", "ja"))
                .translatorName(translatorName)
                .build();
        doReturn(papagoTranslationService).when(translationServiceSelector).selectService(translatorName);

        mockMvc.perform(post("/translation/properties")
                .header("content-type", "application/json")
                .content(CommonTestUtil.asJsonString(requestDto)))
                .andExpect(status().isOk());
    }
}