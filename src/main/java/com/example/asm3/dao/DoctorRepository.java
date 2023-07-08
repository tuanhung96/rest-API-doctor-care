package com.example.asm3.dao;

import com.example.asm3.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query("select d from Doctor d where d.specialization.id = ?1")
    List<Doctor> findBySpecializationId(Integer id);

    @Query("select d from Doctor d where d.user.id = ?1")
    Doctor findByUserId(Integer id);
}
