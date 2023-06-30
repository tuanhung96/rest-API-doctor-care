package com.example.asm3.service;

import com.example.asm3.entity.Doctor;

import java.util.List;


public interface DoctorService {

    List<Doctor> findBySpecializationId(Integer id);
}
