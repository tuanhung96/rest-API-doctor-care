package com.example.asm3.service;

import com.example.asm3.dao.ScheduleRepository;
import com.example.asm3.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    @Override
    @Transactional
    public List<Integer> getTop5SpecializationId() {
        return scheduleRepository.getTop5SpecializationId();
    }

    @Override
    @Transactional
    public List<Integer> getTop5ClinicId() {
        return scheduleRepository.getTop5ClinicId();
    }

    @Override
    @Transactional
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }
}
