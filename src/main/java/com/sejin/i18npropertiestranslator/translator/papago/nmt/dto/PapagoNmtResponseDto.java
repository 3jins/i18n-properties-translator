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
public class PapagoNmtResponseDto {
    private PapagoNmtResponseMessageResultDto message;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PapagoNmtResponseMessageResultDto{
        private PapagoNmtResponseResultDto result;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PapagoNmtResponseResultDto{
        private String translatedText;
        @JsonProperty("srcLangType")
        private String sourceLanguageType;
        @JsonProperty("tarLangType")
        private String targetLanguageType;
    }
}
