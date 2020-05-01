package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.common.constant.LanguageType;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParamDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationResponseDto;
import com.sejin.i18npropertiestranslator.translator.papago.nmt.PapagoNmtService;
import com.sejin.i18npropertiestranslator.translator.parser.PropertiesParsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertiesTranslationService {
    private final PropertiesParsingService propertiesParsingService;
    private final PapagoNmtService papagoNmtService;

    public List<PropertiesTranslationResponseDto> translatePropertiesData(final PropertiesTranslationParamDto paramDto) {
        final LanguageType sourceLanguageType = paramDto.getSourceLanguageType();
        final Map<String, String> propertiesData = propertiesParsingService.parsePropertiesRawContent(paramDto.getPropertiesRawContent());
        return paramDto.getTargetLanguageTypeList().stream()
                .map(targetLanguageType -> {
                    final Map<String, String> translatePropertiesData = propertiesData.entrySet().stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    property -> papagoNmtService.translate(property.getValue(), sourceLanguageType, targetLanguageType)
                            ));
                    return PropertiesTranslationResponseDto.builder()
                            .languageType(targetLanguageType)
                            .translatedPropertiesData(translatePropertiesData)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
