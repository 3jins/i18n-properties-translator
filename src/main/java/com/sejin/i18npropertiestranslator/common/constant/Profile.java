package com.sejin.i18npropertiestranslator.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Profile {
    REAL("REAL"),
    LOCAL("LOCAL"),
    TEST("TEST");

    private final String code;
}
