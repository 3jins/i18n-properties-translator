package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParamDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationResponseDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertyDto;
import com.sejin.i18npropertiestranslator.translator.papago.nmt.PapagoNmtService;
import com.sejin.i18npropertiestranslator.translator.parser.PropertiesParsingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertiesTranslationService {
    private final PropertiesParsingService propertiesParsingService;
    private final PapagoNmtService papagoNmtService;

    public List<PropertiesTranslationResponseDto> translatePropertiesData(final PropertiesTranslationParamDto paramDto) {
        final LanguageType sourceLanguageType = paramDto.getSourceLanguageType();
        final List<PropertyDto> propertyList = propertiesParsingService
                .parsePropertiesRawContent(paramDto.getPropertiesRawContent());

        return paramDto.getTargetLanguageTypeList().stream()
                .map(targetLanguageType -> {
                    final List<PropertyDto> translatedPropertiesData = propertyList.stream()
                            .map(propertiesParsingResult -> translatePropertyData(propertiesParsingResult, sourceLanguageType, targetLanguageType))
                            .collect(Collectors.toList());
                    return PropertiesTranslationResponseDto.builder()
                            .languageType(targetLanguageType.getPapagoCode())
                            .translatedPropertiesData(translatedPropertiesData)
                            .build();
                })
                .collect(Collectors.toList());
    }

    private PropertyDto translatePropertyData(PropertyDto propertiesParsingResult, LanguageType sourceLanguageType, LanguageType targetLanguageType) {
        final String propertyKey = propertiesParsingResult.getKey();
        final String propertyValue = propertiesParsingResult.getValue();
        final String propertyComment = propertiesParsingResult.getComment();

        if (StringUtils.isEmpty(propertyKey) && StringUtils.isEmpty(propertyValue)) {
            return PropertyDto.builder().comment(propertyComment).build();
        }
        final String translatedValue = StringUtils.isEmpty(propertyValue)
                ? ""
                : papagoNmtService.translate(
                propertyValue,
                sourceLanguageType,
                targetLanguageType
        );

        return PropertyDto.builder()
                .key(propertyKey)
                .value(translatedValue)
                .comment(propertyComment)
                .build();
    }
}
