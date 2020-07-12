package com.sejin.i18npropertiestranslator.translator.dto;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.AbstractConverter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class StringToLanguageTypeConverter extends AbstractConverter<String, LanguageType> {

    @Override
    protected LanguageType convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        return LanguageType.getLanguageTypeByPapagoCode(source);
    }

}
