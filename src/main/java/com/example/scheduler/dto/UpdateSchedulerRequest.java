package com.example.scheduler.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class UpdateSchedulerRequest {

    private String title;
    private String content;
    private String name;
    private int password;
    private LocalDate date = LocalDate.now();
}
