package com.sejin.i18npropertiestranslator.translator.dto;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import org.modelmapper.PropertyMap;

public class PropertiesTranslationRequestToParamMapper extends PropertyMap<PropertiesTranslationRequestDto, PropertiesTranslationParamDto> {

    @Override
    protected void configure() {
        using(new StringToLanguageTypeConverter())
                .map(source.getSourceLanguageTypeCode(), destination.getSourceLanguageType());
        using(new StringListToLanguageTypeListConverter())
                .map(source.getTargetLanguageTypeCodeList(), destination.getTargetLanguageTypeList());
    }

}
