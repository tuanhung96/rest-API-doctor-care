package com.example.asm3.service;

import com.example.asm3.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Integer> getTop5SpecializationId();

    List<Integer> getTop5ClinicId();

    void save(Schedule schedule);
}
