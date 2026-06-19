package com.example.automation.notification;

import com.example.automation.client.DiscordWebhookClient;
import com.example.automation.dto.DiscordMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscordNotificationService {

    private static final int COLOR_BLUE = 3447003;
    private static final int COLOR_GREEN = 3066993;
    private static final int COLOR_RED = 15158332;

    private final DiscordWebhookClient discordWebhookClient;

    public void sendInfo(String title, String description) {
        send(title, description, COLOR_BLUE);
    }

    public void sendSuccess(String title, String description) {
        send(title, description, COLOR_GREEN);
    }

    public void sendError(String title, String description) {
        send(title, description, COLOR_RED);
    }

    public void sendWithFields(String title, String description, List<DiscordMessageDto.Field> fields) {
        DiscordMessageDto message = DiscordMessageDto.builder()
                .embeds(List.of(
                        DiscordMessageDto.Embed.builder()
                                .title(title)
                                .description(description)
                                .color(COLOR_BLUE)
                                .fields(fields)
                                .footer(buildFooter())
                                .build()
                ))
                .build();
        discordWebhookClient.send(message);
    }

    private void send(String title, String description, int color) {
        DiscordMessageDto message = DiscordMessageDto.builder()
                .embeds(List.of(
                        DiscordMessageDto.Embed.builder()
                                .title(title)
                                .description(description)
                                .color(color)
                                .footer(buildFooter())
                                .build()
                ))
                .build();
        discordWebhookClient.send(message);
    }

    private DiscordMessageDto.Footer buildFooter() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return DiscordMessageDto.Footer.builder()
                .text("auto-bot | " + today)
                .build();
    }
}