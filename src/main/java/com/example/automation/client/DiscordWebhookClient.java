package com.example.automation.client;

import com.example.automation.dto.DiscordMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class DiscordWebhookClient {

    private final RestClient restClient;
    private final String webhookUrl;

    public DiscordWebhookClient(@Value("${discord.webhook.url}") String webhookUrl) {
        this.webhookUrl = webhookUrl;
        this.restClient = RestClient.create();
    }

    public void send(DiscordMessageDto message) {
        try {
            restClient.post()
                    .uri(webhookUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(message)
                    .retrieve()
                    .toBodilessEntity();
            log.info("Discord 알림 전송 성공");
        } catch (Exception e) {
            log.error("Discord 알림 전송 실패: {}", e.getMessage());
        }
    }
}