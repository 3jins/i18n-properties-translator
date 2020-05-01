package com.sejin.i18npropertiestranslator.translator.dto;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PropertiesTranslationParamDto {
    private String propertiesRawContent;
    private LanguageType sourceLanguageType;
    private List<LanguageType> targetLanguageTypeList;
}
