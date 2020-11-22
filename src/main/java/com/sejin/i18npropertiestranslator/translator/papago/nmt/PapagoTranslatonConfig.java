package com.sejin.i18npropertiestranslator.translator.papago.nmt;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "papago.nmt")
public class PapagoTranslatonConfig {
    @NonNull
    private String url;
    @NonNull
    private String clientId;
    @NonNull
    private String clientSecret;

    @Bean
    public RestTemplate papagoTranslationRestTemplate() {
        final RestTemplate papagoTranslationRestTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        final List<ClientHttpRequestInterceptor> interceptors = papagoTranslationRestTemplate.getInterceptors();
        interceptors.add(new PapagoTranslationRestTemplateInterceptor(clientId, clientSecret));
        papagoTranslationRestTemplate.setInterceptors(CollectionUtils.isEmpty(interceptors) ? new ArrayList<>() : interceptors);
        return papagoTranslationRestTemplate;
    }
}
