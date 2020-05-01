package com.sejin.i18npropertiestranslator.common.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LanguageType {
    KO("Korean", "ko"),
    JA("Japanese", "ja"),
    ZH_CN("simplified Chinese", "zh-cn"),
    ZH_TW("traditional Chinese", "zh-tw"),
    HI("Hindi", "hi"),
    EN("English", "en"),
    ES("Espanol", "es"),
    FR("French", "fr"),
    DE("Deutsch", "de"),
    PT("Portuguese", "pt"),
    VI("vietnamese", "vi"),
    ID("Indonesian", "id"),
    FA("Farsee", "fa"),
    AR("Arabian", "ar"),
    MM("Myanmarese", "mm"),
    TH("Thai", "th"),
    RU("Russian", "ru"),
    IT("Italian", "it");

    private final String name;
    private final String papagoCode;
}
