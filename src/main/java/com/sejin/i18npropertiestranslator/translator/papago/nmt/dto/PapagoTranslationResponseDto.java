package com.sejin.i18npropertiestranslator.translator.papago.nmt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PapagoTranslationResponseDto {
    private PapagoTranslationResponseMessageResultDto message;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PapagoTranslationResponseMessageResultDto {
        private PapagoTranslationResponseResultDto result;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PapagoTranslationResponseResultDto {
        private String translatedText;
        @JsonProperty("srcLangType")
        private String sourceLanguageType;
        @JsonProperty("tarLangType")
        private String targetLanguageType;
    }
}
