package com.sejin.i18npropertiestranslator.translator.papago.nmt;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.common.constant.TranslatorName;
import com.sejin.i18npropertiestranslator.propertiesparser.dto.PropertyDto;
import com.sejin.i18npropertiestranslator.translator.TranslationService;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParsedParamDto;
import com.sejin.i18npropertiestranslator.translator.papago.nmt.dto.PapagoTranslationRequestDto;
import com.sejin.i18npropertiestranslator.translator.papago.nmt.dto.PapagoTranslationResponseDto;
import com.sejin.i18npropertiestranslator.translator.papago.nmt.exception.PapagoMalfunctioningException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PapagoTranslationService implements TranslationService {
    private final PapagoTranslatonConfig papagoTranslatonConfig;
    private final RestTemplate papagoTranslatorRestTemplate;

    @Override
    public Boolean supports(final TranslatorName translatorName) {
        return translatorName == TranslatorName.PAPAGO;
    }

    @Override
    public String translate(final PropertiesTranslationParsedParamDto parsedParamDto) {
        final PropertyDto propertyDto = parsedParamDto.getProperty();
        final String propertyValue = propertyDto.getValue();
        final LanguageType sourceLanguageType = parsedParamDto.getSourceLanguageType();
        final LanguageType targetLanguageType = parsedParamDto.getTargetLanguageType();

        final PapagoTranslationRequestDto papagoTranslationRequestDto = PapagoTranslationRequestDto.builder()
                .text(propertyValue)
                .sourceLanguageType(sourceLanguageType.getPapagoCode())
                .targetLanguageType(targetLanguageType.getPapagoCode())
                .build();
        final ResponseEntity<PapagoTranslationResponseDto> responseEntity = papagoTranslatorRestTemplate.postForEntity(
                papagoTranslatonConfig.getUrl(),
                new HttpEntity<>(papagoTranslationRequestDto),
                PapagoTranslationResponseDto.class
        );
        final PapagoTranslationResponseDto papagoTranslationResponseDto = responseEntity.getBody();
        if (papagoTranslationRequestDto == null) {
            throw new PapagoMalfunctioningException();
        }
        return papagoTranslationResponseDto.getTranslatedText();
    }
}
