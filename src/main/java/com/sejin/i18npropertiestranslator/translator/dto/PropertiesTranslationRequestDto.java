package com.sejin.i18npropertiestranslator.translator.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PropertiesTranslationRequestDto {
    private String propertiesRawContent;
    private String sourceLanguageTypeCode;
    private List<String> targetLanguageTypeCodeList;
}
