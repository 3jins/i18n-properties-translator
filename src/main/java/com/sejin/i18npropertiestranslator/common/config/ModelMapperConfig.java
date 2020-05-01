package com.sejin.i18npropertiestranslator.common.config;

import com.sejin.i18npropertiestranslator.translator.dto.PropertiesTranslationRequestToParamMapper;
import com.sejin.i18npropertiestranslator.translator.dto.StringToLanguageTypeConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean(name = "modelMapper")
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertiesTranslationRequestToParamMapper());
        return modelMapper;
    }
}
