package com.example.automation.client;

import com.example.automation.dto.ExchangeRateResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class ExchangeRateClient {

    private static final String BASE_URL = "https://open.er-api.com/v6/latest/USD";

    private final RestClient restClient;

    public ExchangeRateClient() {
        this.restClient = RestClient.create();
    }

    public ExchangeRateResponseDto fetchRates() {
        try {
            return restClient.get()
                    .uri(BASE_URL)
                    .retrieve()
                    .body(ExchangeRateResponseDto.class);
        } catch (Exception e) {
            log.error("환율 조회 실패: {}", e.getMessage());
            return null;
        }
    }
}