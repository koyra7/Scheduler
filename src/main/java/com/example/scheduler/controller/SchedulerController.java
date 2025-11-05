package com.example.scheduler.controller;

import com.example.scheduler.dto.CreateSchedulerRequest;
import com.example.scheduler.dto.CreateSchedulerResponse;
import com.example.scheduler.dto.GetSchedulerResponse;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.SchedulerRepository;
import com.example.scheduler.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchedulerController {

    private final SchedulerService schedulerService;

    @PostMapping("/schedulers")
    public ResponseEntity<CreateSchedulerResponse> createScheduler(
            @RequestBody CreateSchedulerRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(schedulerService.createScheduler(request));
    }

    @GetMapping("/schedulers/{schedulerId}")
    public ResponseEntity<GetSchedulerResponse> getScheduler(
            @PathVariable Long schedulerId
    ) {
        return ResponseEntity.ok(schedulerService.findOf(schedulerId));
    }

    @GetMapping("/schedulers")
    public ResponseEntity<List<GetSchedulerResponse>> getAllSchedulers(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(schedulerService.findAll(name));
    }
}


