package com.example.asm3.service;

import com.example.asm3.dao.ScheduleRepository;
import com.example.asm3.entity.Patient;
import com.example.asm3.entity.Schedule;
import com.example.asm3.exception.PatientNotFoundException;
import com.example.asm3.exception.ScheduleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional
    public List<Integer> findByDoctorId(Integer id) {
        return scheduleRepository.findByDoctorId(id);
    }

    @Override
    @Transactional
    public List<Schedule> findScheduleByDoctorId(Integer id) {
        return scheduleRepository.findScheduleByDoctorId(id);
    }

    @Override
    @Transactional
    public Schedule findById(Integer id) {
        Optional<Schedule> result = scheduleRepository.findById(id);
        Schedule schedule = null;

        if (result.isPresent()) {
            schedule = result.get();
        } else {
            throw new ScheduleNotFoundException("Did not find schedule id - " + id);
        }
        return schedule;
    }
}
