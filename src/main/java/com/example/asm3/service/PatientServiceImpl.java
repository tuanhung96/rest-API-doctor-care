package com.example.asm3.service;

import com.example.asm3.dao.PatientRepository;
import com.example.asm3.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientServiceImpl implements PatientService{
    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    @Transactional
    public Patient findByUserId(Integer id) {
        return patientRepository.findByUserId(id);
    }

    @Override
    @Transactional
    public void save(Patient patient) {
        patientRepository.save(patient);
    }
}
