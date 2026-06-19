package com.example.automation.service;

import com.example.automation.client.ExchangeRateClient;
import com.example.automation.dto.DiscordMessageDto;
import com.example.automation.dto.ExchangeRateResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private static final int COLOR_GOLD = 15844367;
    private static final Map<String, String> TARGET_CURRENCIES = Map.of(
            "KRW", "🇰🇷 원 (KRW)",
            "JPY", "🇯🇵 엔 (JPY)",
            "EUR", "🇪🇺 유로 (EUR)"
    );

    private final ExchangeRateClient exchangeRateClient;

    public DiscordMessageDto.Embed buildEmbed() {
        ExchangeRateResponseDto response = exchangeRateClient.fetchRates();

        if (response == null || response.getRates() == null) {
            return DiscordMessageDto.Embed.builder()
                    .title("💱 환율 정보")
                    .description("환율 정보를 가져오지 못했습니다.")
                    .color(COLOR_GOLD)
                    .build();
        }

        Map<String, Double> rates = response.getRates();
        List<DiscordMessageDto.Field> fields = List.of(
                buildField("🇰🇷 USD → KRW", formatKrw(rates.get("KRW"))),
                buildField("🇯🇵 USD → JPY", formatJpy(rates.get("JPY"))),
                buildField("🇪🇺 USD → EUR", formatEur(rates.get("EUR")))
        );

        return DiscordMessageDto.Embed.builder()
                .title("💱 오늘의 환율 (기준: USD)")
                .color(COLOR_GOLD)
                .fields(fields)
                .build();
    }

    private DiscordMessageDto.Field buildField(String name, String value) {
        return DiscordMessageDto.Field.builder()
                .name(name)
                .value(value)
                .inline(true)
                .build();
    }

    private String formatKrw(Double rate) {
        if (rate == null) return "정보 없음";
        return String.format("**%,.0f 원**", rate);
    }

    private String formatJpy(Double rate) {
        if (rate == null) return "정보 없음";
        return String.format("**%,.0f 엔**", rate);
    }

    private String formatEur(Double rate) {
        if (rate == null) return "정보 없음";
        return String.format("**%.4f EUR**", rate);
    }
}