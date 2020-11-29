package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.common.constant.TranslatorName;
import com.sejin.i18npropertiestranslator.propertiesparser.PropertiesParsingService;
import com.sejin.i18npropertiestranslator.propertiesparser.dto.PropertyDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParamDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParsedParamDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertiesTranslationService {
    private final PropertiesParsingService propertiesParsingService;

    public List<PropertiesTranslationResponseDto> translatePropertiesData(final PropertiesTranslationParamDto paramDto, final TranslationService translationService) {
        final LanguageType sourceLanguageType = paramDto.getSourceLanguageType();
        final List<PropertyDto> propertyList = propertiesParsingService.parsePropertiesRawContent(paramDto.getPropertiesRawContent());
        final TranslatorName translatorName = paramDto.getTranslatorName();

        return paramDto.getTargetLanguageTypeList().stream()
                .map(targetLanguageType -> {
                    final List<PropertiesTranslationParsedParamDto> parsedParamDtoList = makeParsedParamDtoList(propertyList, sourceLanguageType, targetLanguageType);
                    final List<PropertyDto> translatedPropertiesData = parsedParamDtoList.stream()
                            .map(parsedParamDto -> translatePropertyData(parsedParamDto, translationService))
                            .collect(Collectors.toList());
                    return PropertiesTranslationResponseDto.builder()
                            .languageType(targetLanguageType.getPapagoCode())
                            .translatedPropertiesData(translatedPropertiesData)
                            .build();
                })
                .collect(Collectors.toList());
    }

    private List<PropertiesTranslationParsedParamDto> makeParsedParamDtoList(final List<PropertyDto> propertyList, final LanguageType sourceLanguageType, final LanguageType targetLanguageType) {
        return propertyList.stream()
                .map(property -> PropertiesTranslationParsedParamDto.builder()
                        .property(property)
                        .sourceLanguageType(sourceLanguageType)
                        .targetLanguageType(targetLanguageType)
                        .build())
                .collect(Collectors.toList());
    }

    private PropertyDto translatePropertyData(final PropertiesTranslationParsedParamDto parsedParamDto, final TranslationService translationService) {
        final PropertyDto propertyDto = parsedParamDto.getProperty();
        final String propertyKey = propertyDto.getKey();
        final String propertyValue = propertyDto.getValue();
        final String propertyComment = propertyDto.getComment();

        if (StringUtils.isEmpty(propertyKey) && StringUtils.isEmpty(propertyValue)) {
            return PropertyDto.builder().comment(propertyComment).build();
        }
        final String translatedValue = StringUtils.isEmpty(propertyValue)
                ? ""
                : translationService.translate(parsedParamDto);

        return PropertyDto.builder()
                .key(propertyKey)
                .value(translatedValue)
                .comment(propertyComment)
                .build();
    }
}
