package com.example.automation.scheduler;

import com.example.automation.notification.DiscordNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Profile("!ci")
@Component
@RequiredArgsConstructor
public class JobScraperScheduler {

    private final DiscordNotificationService notificationService;

    @Scheduled(cron = "0 0 6 * * *")
    public void runDailyJobScraper() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("스케줄러 작동 시작 시간: {}", now);

        try {
            log.info("채용 사이트 API에서 개발자 구인공고를 가져오는 중...");

            // TODO: 사람인 API 연동 후 실제 채용공고 데이터로 교체
            notificationService.sendInfo(
                    "오늘의 채용공고",
                    "사람인 API 연동 전 테스트 메시지입니다.\n" + now
            );

            log.info("스케줄러 작업 완료!");
        } catch (Exception e) {
            log.error("작업 실패: {}", e.getMessage());
            notificationService.sendError("채용공고 봇 오류", "작업 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}