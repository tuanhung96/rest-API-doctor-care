package com.example.asm3.service;

import com.example.asm3.entity.Specialization;

import java.util.List;

public interface SpecializationService {
    Specialization findById(Integer id);

    List<Specialization> findByName(String specializationName);
}
