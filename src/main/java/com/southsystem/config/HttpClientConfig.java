package com.southsystem.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class HttpClientConfig {

    @Value("${cpf.api}")
    private String cpfURI;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(cpfURI));
        return restTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        final var timeoutInMillis = 30000;
        final var clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectionRequestTimeout(timeoutInMillis);
        clientHttpRequestFactory.setConnectTimeout(timeoutInMillis);
        clientHttpRequestFactory.setReadTimeout(timeoutInMillis);
        clientHttpRequestFactory.setHttpClient(httpClient());
        return clientHttpRequestFactory;
    }

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }
}
