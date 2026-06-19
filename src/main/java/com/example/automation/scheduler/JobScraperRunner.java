package com.example.automation.scheduler;

import com.example.automation.notification.DiscordNotificationService;
import com.example.automation.service.DailyReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("ci")
@Component
@RequiredArgsConstructor
public class JobScraperRunner implements CommandLineRunner {

    private final DailyReportService dailyReportService;
    private final DiscordNotificationService notificationService;

    @Override
    public void run(String... args) {
        try {
            dailyReportService.run();
        } catch (Exception e) {
            log.error("일일 리포트 실패: {}", e.getMessage());
            notificationService.sendError("자동화 봇 오류", "작업 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}