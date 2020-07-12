package com.sejin.i18npropertiestranslator.translator.papago.nmt;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.translator.TranslationService;
import com.sejin.i18npropertiestranslator.translator.papago.nmt.dto.PapagoNmtRequestDto;
import com.sejin.i18npropertiestranslator.translator.papago.nmt.dto.PapagoNmtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PapagoNmtService implements TranslationService {
    private final PapagoNmtConfig papagoNmtConfig;
    private final RestTemplate papagoNmtRestTemplate;

    @Override
    public String translate(final String text, final LanguageType sourceLanguageType, final LanguageType targetLanguageType) {
        final PapagoNmtRequestDto papagoNmtRequestDto = PapagoNmtRequestDto.builder()
                .text(text)
                .sourceLanguageType(sourceLanguageType.getPapagoCode())
                .targetLanguageType(targetLanguageType.getPapagoCode())
                .build();
        final ResponseEntity<PapagoNmtResponseDto> responseEntity = papagoNmtRestTemplate.postForEntity(
                papagoNmtConfig.getUrl(),
                new HttpEntity<>(papagoNmtRequestDto),
                PapagoNmtResponseDto.class
        );
        final PapagoNmtResponseDto papagoNmtResponseDto = responseEntity.getBody();
        return papagoNmtResponseDto.getMessage().getResult().getTranslatedText();
    }
}
