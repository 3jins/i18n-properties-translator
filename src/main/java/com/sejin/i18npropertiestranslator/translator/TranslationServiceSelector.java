package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.constant.TranslatorName;
import com.sejin.i18npropertiestranslator.common.exception.NotSupportedTranslatorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslationServiceSelector {
    private final List<TranslationService> translationServiceList;

    public TranslationService selectService(final TranslatorName translatorName) {
        return translationServiceList.stream()
                .filter(translationService -> translationService.supports(translatorName))
                .findFirst()
                .orElseThrow(NotSupportedTranslatorException::new);
    }

}
