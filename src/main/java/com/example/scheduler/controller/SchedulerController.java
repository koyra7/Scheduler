package com.example.scheduler.controller;

import com.example.scheduler.dto.*;
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

    @PutMapping("/schedulers/{schedulerId}")
    public ResponseEntity<UpdateSchedulerResponse> updateScheduler(
            @PathVariable Long schedulerId,
            @RequestBody UpdateSchedulerRequest request
            ) {
        return ResponseEntity.ok(schedulerService.updateScheduler(schedulerId, request));
    }

    @DeleteMapping("/schedulers/{schedulerId}")
    public ResponseEntity<Void> deleteScheduler(
            @PathVariable Long schedulerId
    ) {
        schedulerService.delete(schedulerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


