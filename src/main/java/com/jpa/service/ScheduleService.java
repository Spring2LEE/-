package com.jpa.service;

import com.jpa.dto.*;
import com.jpa.entity.Schedule;
import com.jpa.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    //저장
    @Transactional
    public CreateScheduleResponse save (@RequestBody CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getName(),
                savedSchedule.getPassword()
        );
    }
    //단 건 조회
    @Transactional
    public  GetOneResponse getOne(long scheduleID) {
        Schedule schedule = scheduleRepository.findById(scheduleID).orElseThrow(
                () -> new IllegalStateException("일정을 찾을 수 없습니다")
        );
        return new GetOneResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getPassword()
        );
    }
    //다 건 조회
    @Transactional
    public List<GetOneResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<GetOneResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetOneResponse dto = new GetOneResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getPassword()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public UpdateResponse update(Long scheduleID, UpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleID).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다")
        );
        schedule.updateSchedule(
                request.getTitle(),
                request.getName()
        );
        return new UpdateResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getPassword()
        );

    }
    @Transactional
    public void delete(Long scheduleID) {
        boolean exists = scheduleRepository.existsById(scheduleID);

        // 일정이 없는 경우
        if(!exists) {
            throw new IllegalStateException("일정이 없습니다");
        }

        //일정이 있는 경우
        scheduleRepository.deleteById(scheduleID);
    }

}
