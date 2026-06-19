package com.example.automation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DiscordMessageDto {

    private String content;
    private List<Embed> embeds;

    @Getter
    @Builder
    public static class Embed {
        private String title;
        private String description;
        private int color;
        private Footer footer;
        private List<Field> fields;
    }

    @Getter
    @Builder
    public static class Footer {
        private String text;
    }

    @Getter
    @Builder
    public static class Field {
        private String name;
        private String value;
        @JsonProperty("inline")
        private boolean inline;
    }
}