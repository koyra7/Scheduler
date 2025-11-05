package com.example.scheduler.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.ZoneId;

@Getter
public class UpdateSchedulerResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private LocalDate date = LocalDate.now();

    public UpdateSchedulerResponse(Long id, String title, String content, String name, LocalDate date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.date = LocalDate.now(ZoneId.of("Asia/Seoul"));
    }
}
