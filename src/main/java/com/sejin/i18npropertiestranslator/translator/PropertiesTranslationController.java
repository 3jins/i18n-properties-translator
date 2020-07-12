package com.sejin.i18npropertiestranslator.translator;

import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationParamDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationRequestDto;
import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/translation")
public class PropertiesTranslationController {
    private final ModelMapper modelMapper;
    private final PropertiesTranslationService propertiesTranslationService;

    @PostMapping("properties")
    public List<PropertiesTranslationResponseDto> translateProperties(@RequestBody final PropertiesTranslationRequestDto requestDto) {
        final PropertiesTranslationParamDto paramDto = modelMapper.map(requestDto, PropertiesTranslationParamDto.class);
        return propertiesTranslationService.translatePropertiesData(paramDto);
    }
}
