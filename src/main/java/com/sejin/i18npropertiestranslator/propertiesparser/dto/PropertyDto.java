package com.sejin.i18npropertiestranslator.propertiesparser.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PropertyDto {
    private String key;
    private String value;
    private String comment;
}
