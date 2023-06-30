package com.example.asm3.dao;

import com.example.asm3.entity.Clinic;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    @Query("select c from Clinic c where c.address like %?1%")
    List<Clinic> findByAddress(String address, Sort sort);

}
