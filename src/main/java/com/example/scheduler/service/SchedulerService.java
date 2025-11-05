package com.example.scheduler.service;

import com.example.scheduler.dto.CreateSchedulerRequest;
import com.example.scheduler.dto.CreateSchedulerResponse;
import com.example.scheduler.dto.GetSchedulerResponse;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.scheduler.entity.Scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    @Transactional
    public CreateSchedulerResponse createScheduler(CreateSchedulerRequest request) {
        Scheduler scheduler = new Scheduler(request.getTitle(), request.getContent(), request.getName(), request.getPassword());
        Scheduler savedScheduler = schedulerRepository.save(scheduler);

        return new CreateSchedulerResponse(
                savedScheduler.getId(),
                savedScheduler.getTitle(),
                savedScheduler.getContent(),
                savedScheduler.getName(),
                savedScheduler.getDate()
        );
    }

    @Transactional(readOnly = true)
    public GetSchedulerResponse findOf(Long schedulerId) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(
                () -> new IllegalArgumentException("없는 스케쥴입니다.")
        );
        return new GetSchedulerResponse(
                scheduler.getId(),
                scheduler.getTitle(),
                scheduler.getContent(),
                scheduler.getName(),
                scheduler.getDate()
        );
    }

    @Transactional(readOnly = true)
    public List<GetSchedulerResponse> findAll(String name) {
        List<Scheduler> schedulers = schedulerRepository.findAll();

            return schedulers.stream().
                    filter(scheduler -> name == null || scheduler.getName().equals(name) || name.isEmpty())
                    .sorted(Comparator.comparing(Scheduler::getDate).reversed())
                    .map(
                            scheduler -> new GetSchedulerResponse(
                                    scheduler.getId(),
                                    scheduler.getTitle(),
                                    scheduler.getContent(),
                                    scheduler.getName(),
                                    scheduler.getDate()))
                    .toList();


    }
}
