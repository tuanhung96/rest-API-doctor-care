package com.example.asm3.dao;

import com.example.asm3.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("select p from Patient p where p.user.id = ?1")
    Patient findByUserId(Integer id);
}
