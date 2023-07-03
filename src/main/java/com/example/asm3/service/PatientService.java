package com.example.asm3.service;

import com.example.asm3.entity.Patient;

public interface PatientService {
    Patient findByUserId(Integer id);

    void save(Patient patient);
}
