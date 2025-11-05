package com.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.ZoneId;

@Getter
@Entity
@Table(name = "schedulers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String name;
    private int password;
    private LocalDate date = LocalDate.now();

    public Scheduler(String title, String content, String name, int password) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
        date = LocalDate.now(ZoneId.of("Asia/Seoul"));
    }

    public void updateScheduler(String title, String content, String name, int password ) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
        date = LocalDate.now(ZoneId.of("Asia/Seoul"));
    }
}
