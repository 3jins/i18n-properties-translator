package com.sejin.i18npropertiestranslator.translator.parser;

import com.sejin.i18npropertiestranslator.common.exception.PropertiesDataFormatException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PropertiesParsingService {
    public Map<String, String> parsePropertiesRawContent(final String propertiesRawContent) {
        final String[] properties = propertiesRawContent.split("\n");
        final Map<String, String> propertiesData = Arrays.stream(properties)
                .filter(property -> !StringUtils.isEmpty(property))
                .map(property -> {
                    final int separatorIdx = property.indexOf('=');
                    if (separatorIdx < 0) {
                        throw new PropertiesDataFormatException(null, property);
                    }
                    return new String[]{
                            property.substring(0, separatorIdx),
                            property.substring(separatorIdx + 1)
                    };
                })
                .collect(Collectors.toMap(property -> property[0], property -> property[1]));
        return propertiesData;
    }
}
