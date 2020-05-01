package com.sejin.i18npropertiestranslator.translator.papago.nmt;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
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
@SpringBootTest(classes = {PapagoNmtService.class, PapagoNmtConfig.class})
@ActiveProfiles("TEST")
class PapagoNmtServiceTest {
    @Autowired
    private PapagoNmtService papagoNmtService;

    @Test
    void translate() {
        final String text = "안녕하세요";
        final LanguageType sourceLanguageType = LanguageType.KO;
        final LanguageType targetLanguageType = LanguageType.EN;

        final String result = papagoNmtService.translate(text, sourceLanguageType, targetLanguageType);
        log.debug("papago nmt result = {}", result);
        assertThat(result).isNotEmpty();
        assertThat(result).isNotEqualTo(text);
    }
}