package com.example.asm3.service;

import com.example.asm3.dao.PatientRepository;
import com.example.asm3.entity.Doctor;
import com.example.asm3.entity.Patient;
import com.example.asm3.exception.DoctorNotFoundException;
import com.example.asm3.exception.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    @Transactional
    public Patient findById(Integer id) {
        Optional<Patient> result = patientRepository.findById(id);
        Patient patient = null;

        if (result.isPresent()) {
            patient = result.get();
        } else {
            throw new PatientNotFoundException("Did not find patient id - " + id);
        }
        return patient;
    }
}
