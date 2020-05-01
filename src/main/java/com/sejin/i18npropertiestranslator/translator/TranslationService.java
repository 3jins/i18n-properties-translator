package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;

public interface TranslationService {
    public String translate(final String text, final LanguageType sourceLanguageType, final LanguageType targetLanguageType);
}
