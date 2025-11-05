package com.example.scheduler.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateSchedulerRequest {

    private String title;
    private String content;
    private String name;
    private int password;
    private LocalDate date;
}
