package com.sejin.i18npropertiestranslator.translator.parser;

import com.sejin.i18npropertiestranslator.common.exception.PropertiesDataFormatException;
import com.sejin.i18npropertiestranslator.translator.dto.PropertyDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertiesParsingService {
    public List<PropertyDto> parsePropertiesRawContent(final String propertiesRawContent) {
        final String[] properties = propertiesRawContent.split("\n");
        return Arrays.stream(properties)
                .map(property -> {
                    if (StringUtils.isEmpty(property)) {
                        return PropertyDto.builder().build();
                    }

                    final int commentSeparatorIdx = property.indexOf('#');
                    final String comment = commentSeparatorIdx >= 0 ? property.substring(commentSeparatorIdx + 1) : null;
                    if (commentSeparatorIdx == 0) {
                        return PropertyDto.builder().comment(comment).build();
                    }

                    final int valueSeparatorIdx = property.indexOf('=');
                    if (valueSeparatorIdx < 0 && commentSeparatorIdx < 0) {
                        throw new PropertiesDataFormatException(null, property);
                    }
                    final String key = property.substring(0, valueSeparatorIdx).trim();
                    if (StringUtils.isEmpty(key)) {
                        throw new PropertiesDataFormatException(null, property);
                    }
                    final String value = commentSeparatorIdx < 0
                            ? property.substring(valueSeparatorIdx + 1).trim()
                            : property.substring(valueSeparatorIdx + 1, commentSeparatorIdx).trim();

                    return PropertyDto.builder()
                            .key(key)
                            .value(value)
                            .comment(comment)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
