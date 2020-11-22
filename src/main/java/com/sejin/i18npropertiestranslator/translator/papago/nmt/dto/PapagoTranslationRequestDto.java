package com.sejin.i18npropertiestranslator.translator.papago.nmt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PapagoTranslationRequestDto {
    private String text;
    @JsonProperty("source")
    private String sourceLanguageType;
    @JsonProperty("target")
    private String targetLanguageType;
}
