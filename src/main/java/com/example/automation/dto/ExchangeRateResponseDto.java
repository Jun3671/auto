package com.example.automation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@Getter
public class ExchangeRateResponseDto {

    private String result;

    @JsonProperty("base_code")
    private String baseCode;

    private Map<String, Double> rates;
}