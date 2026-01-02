package com.jpa.controller;

import com.jpa.dto.*;
import com.jpa.entity.Schedule;
import com.jpa.repository.ScheduleRepository;
import com.jpa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleCotroller {


    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse result = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schdules/{scheduleID}")
    public ResponseEntity<GetOneResponse> getOne(@PathVariable Long scheduleID) {
        GetOneResponse result = scheduleService.getOne(scheduleID);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetOneResponse>> getAllSchedules() {
       List<GetOneResponse> result = scheduleService.getAll();
       return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/schedules/{scheduleID}")
    public ResponseEntity<UpdateResponse> update(@PathVariable Long scheduleID, @RequestBody UpdateRequest request) {
        UpdateResponse result = scheduleService.update(scheduleID, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/schedules/{scheduleID}")
    public ResponseEntity<Void> delete(@PathVariable Long scheduleID) {
        scheduleService.delete(scheduleID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
