package com.example.asm3.service;

import com.example.asm3.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Integer> getTop5SpecializationId();

    List<Integer> getTop5ClinicId();

    void save(Schedule schedule);

    List<Integer> findByDoctorId(Integer id);

    List<Schedule> findScheduleByDoctorId(Integer id);

    Schedule findById(Integer scheduleId);

    List<Schedule> findByPatientId(Integer patientId);
}
