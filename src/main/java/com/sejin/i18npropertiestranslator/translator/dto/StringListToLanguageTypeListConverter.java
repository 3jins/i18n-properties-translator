package com.sejin.i18npropertiestranslator.translator.dto;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import org.modelmapper.AbstractConverter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class StringListToLanguageTypeListConverter extends AbstractConverter<List<String>, List<LanguageType>> {

    @Override
    protected List<LanguageType> convert(List<String> source) {
        if (CollectionUtils.isEmpty(source)) {
            return List.of();
        }
        return source.stream()
                .map(LanguageType::getLanguageTypeByPapagoCode)
                .collect(Collectors.toList());
    }

}
