package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.common.constant.TranslatorName;

public interface TranslationService {
    Boolean supports(final TranslatorName translatorName);
    String translate(final String text, final LanguageType sourceLanguageType, final LanguageType targetLanguageType);
}
