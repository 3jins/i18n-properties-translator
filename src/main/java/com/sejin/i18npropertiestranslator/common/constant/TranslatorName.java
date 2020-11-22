package com.sejin.i18npropertiestranslator.common.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TranslatorName {
    PAPAGO("Papago NMT"),
    ;

    private final String name;
}
