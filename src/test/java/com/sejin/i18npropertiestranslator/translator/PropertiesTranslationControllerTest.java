package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.CommonTestUtil;
import com.sejin.i18npropertiestranslator.common.config.ModelMapperConfig;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationRequestDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = {ModelMapperConfig.class, PropertiesTranslationController.class})
@ActiveProfiles("TEST")
class PropertiesTranslationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertiesTranslationService propertiesTranslationService;

    @Test
    void translateProperties() throws Exception {
        final PropertiesTranslationRequestDto requestDto = PropertiesTranslationRequestDto.builder()
                .propertiesRawContent("lyrics = 나는 나는 저팔계 왜 날 싫어하나")
                .sourceLanguageTypeCode("ko")
                .targetLanguageTypeCodeList(List.of("en", "zh-cn", "ja"))
                .build();

        mockMvc.perform(post("/translation/properties")
                .header("content-type", "application/json")
                .content(CommonTestUtil.asJsonString(requestDto)))
                .andExpect(status().isOk());
    }
}