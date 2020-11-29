package com.sejin.i18npropertiestranslator.translator.papago.nmt;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.propertiesparser.dto.PropertyDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParsedParamDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PapagoTranslationService.class, PapagoTranslatonConfig.class})
@ActiveProfiles("TEST")
class PapagoTranslationServiceTest {
    @Autowired
    private PapagoTranslationService papagoTranslator;

    @Test
    void translate() {
        final String text = "안녕하세요";
        final LanguageType sourceLanguageType = LanguageType.KO;
        final LanguageType targetLanguageType = LanguageType.EN;
        final PropertiesTranslationParsedParamDto parsedParamDto = PropertiesTranslationParsedParamDto.builder()
                .property(PropertyDto.builder().value(text).build())
                .sourceLanguageType(sourceLanguageType)
                .targetLanguageType(targetLanguageType)
                .build();

        final String result = papagoTranslator.translate(parsedParamDto);
        log.debug("papago nmt result = {}", result);
        assertThat(result).isNotEmpty();
        assertThat(result).isNotEqualTo(text);
    }
}