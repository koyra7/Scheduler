package com.example.scheduler.service;

import com.example.scheduler.dto.*;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional // dto로 여러건 요청
    public UpdateSchedulerResponse updateScheduler(Long schedulerId, UpdateSchedulerRequest request) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(
                () -> new IllegalArgumentException("없는 스케쥴입니다.")
        );

        if (scheduler.getPassword() != request.getPassword()) { // 비밀번호 불일치시 아래 출력
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        scheduler.updateScheduler(request.getTitle(), request.getName()); // 제목과 이름 변경
        return new UpdateSchedulerResponse( // 새로운 id,제목,내용,이름,날짜로 갱신
                scheduler.getId(),
                scheduler.getTitle(),
                scheduler.getContent(),
                scheduler.getName(),
                scheduler.getDate()
        );
    }

    @Transactional
    public void delete(Long schedulerId, int password) { // 번호랑 id 가져옴
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(
                () -> new IllegalArgumentException("없는 스케쥴입니다.")
        );
        if (scheduler.getPassword() != password) { // 비밀번호 불일치시 아래 출력
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
    }
        schedulerRepository.delete(scheduler); // 맞으면 데이터 삭제
    }
}
