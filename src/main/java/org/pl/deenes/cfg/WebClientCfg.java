package org.pl.deenes.cfg;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pl.deenes.osm.ApiClient;
import org.pl.deenes.osm.infrastructure.MapApi;
import org.pl.deenes.osm.infrastructure.MiscApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientCfg {

    @Value("${api.osm.url}")
    private String apiUrl;

    @Bean
    public ApiClient osmApiClient(final ObjectMapper objectMapper) {
        final var strategies = ExchangeStrategies
                .builder().codecs(configurer -> {
                    configurer
                            .defaultCodecs().jackson2JsonEncoder(
                                    new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                    configurer
                            .defaultCodecs()
                            .jackson2JsonDecoder(
                                    new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                }).build();
        final var webClient = WebClient.builder()
                .exchangeStrategies(strategies).build();
        ApiClient apiClient = new ApiClient(webClient);
        apiClient.setBasePath(apiUrl);
        return apiClient;
    }

    @Bean
    public MapApi osmApi(final ApiClient apiClient) {
        return new MapApi(apiClient);
    }

    @Bean
    public MiscApi osmMiscApi(final ApiClient apiClient) {
        return new MiscApi(apiClient);
    }
}


