package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.constant.TranslatorName;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParsedParamDto;

public interface TranslationService {
    Boolean supports(final TranslatorName translatorName);
    String translate(final PropertiesTranslationParsedParamDto parsedParamDto);
}
