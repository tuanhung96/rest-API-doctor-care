package com.example.asm3.dao;

import com.example.asm3.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(value = "SELECT top(5) specialization_id FROM schedule GROUP BY specialization_id ORDER BY count(*) desc", nativeQuery = true)
    List<Integer> getTop5SpecializationId();

    @Query(value = "SELECT top(5) clinic_id FROM schedule GROUP BY clinic_id ORDER BY count(*) desc", nativeQuery = true)
    List<Integer> getTop5ClinicId();

    @Query(value = "SELECT distinct patient_id FROM schedule WHERE doctor_id = ?1", nativeQuery = true)
    List<Integer> findByDoctorId(Integer id);

    @Query("SELECT s FROM Schedule s WHERE s.doctor.id = ?1")
    List<Schedule> findScheduleByDoctorId(Integer id);
}
