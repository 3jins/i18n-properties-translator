package com.sejin.i18npropertiestranslator.translator.dto;

import com.sejin.i18npropertiestranslator.propertiesparser.dto.PropertyDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PropertiesTranslationResponseDto {
    private String languageType;
    private List<PropertyDto> translatedPropertiesData;
}
