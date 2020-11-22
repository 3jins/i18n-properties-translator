package com.sejin.i18npropertiestranslator.translator.dto;

import com.sejin.i18npropertiestranslator.common.constant.TranslatorName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesTranslationRequestDto {
    private String propertiesRawContent;
    private String sourceLanguageTypeCode;
    private List<String> targetLanguageTypeCodeList;
    @Builder.Default
    private TranslatorName translatorName = TranslatorName.PAPAGO;
}
