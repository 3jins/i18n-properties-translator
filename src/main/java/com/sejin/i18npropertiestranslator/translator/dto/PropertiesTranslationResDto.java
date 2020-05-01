package com.sejin.i18npropertiestranslator.translator.dto;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class PropertiesTranslationResDto {
    private LanguageType languageType;
    private Map<String, String> translatedPropertiesData;
}
