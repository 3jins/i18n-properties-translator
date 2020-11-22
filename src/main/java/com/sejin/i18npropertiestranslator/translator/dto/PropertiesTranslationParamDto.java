package com.sejin.i18npropertiestranslator.translator.dto;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.common.constant.TranslatorName;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesTranslationParamDto {
    private String propertiesRawContent;
    private LanguageType sourceLanguageType;
    private List<LanguageType> targetLanguageTypeList;
    private TranslatorName translatorName;
}
