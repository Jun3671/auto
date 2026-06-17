package com.example.automation.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Profile("!ci")
@Component
public class JobScraperScheduler {

    @Scheduled(cron = "0 0 6 * * *")
    public void runDailyJobScraper() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("스케줄러 작동 시작 시간: {}", now);
        log.info("채용 사이트 API에서 개발자 구인공고를 가져오는 중...");
        log.info("스케줄러 작업 완료!");
    }
}