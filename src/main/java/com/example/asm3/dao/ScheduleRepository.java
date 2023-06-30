package com.example.asm3.dao;

import com.example.asm3.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(value = "SELECT top(5) specialization_id FROM schedule GROUP BY specialization_id ORDER BY count(*) desc", nativeQuery = true)
    List<Integer> getTop5SpecializationId();
}
