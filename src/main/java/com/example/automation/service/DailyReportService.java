package com.example.automation.service;

import com.example.automation.dto.DiscordMessageDto;
import com.example.automation.notification.DiscordNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyReportService {

    private final DiscordNotificationService notificationService;
    private final ExchangeRateService exchangeRateService;

    public void run() {
        log.info("일일 리포트 생성 중...");

        List<DiscordMessageDto.Embed> embeds = new ArrayList<>();

        embeds.add(exchangeRateService.buildEmbed());
        // TODO: embeds.add(jobService.buildEmbed());  ← 사람인 API 키 오면 추가

        DiscordMessageDto message = DiscordMessageDto.builder()
                .embeds(embeds)
                .build();

        notificationService.send(message);
        log.info("일일 리포트 전송 완료");
    }
}