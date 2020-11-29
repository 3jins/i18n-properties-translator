package com.sejin.i18npropertiestranslator.translator.dto;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.propertiesparser.dto.PropertyDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PropertiesTranslationParsedParamDto {
    private PropertyDto property;
    private LanguageType sourceLanguageType;
    private LanguageType targetLanguageType;
}
