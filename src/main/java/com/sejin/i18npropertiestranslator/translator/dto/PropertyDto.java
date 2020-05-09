package com.sejin.i18npropertiestranslator.translator.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class PropertyDto {
    private String key;
    private String value;
    private String comment;
}
