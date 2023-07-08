package com.example.asm3.service;

import com.example.asm3.dao.DoctorRepository;
import com.example.asm3.entity.Doctor;
import com.example.asm3.exception.DoctorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional
    public List<Doctor> findBySpecializationId(Integer id) {
        return doctorRepository.findBySpecializationId(id);
    }

    @Override
    @Transactional
    public Doctor findById(Integer id) {
        Optional<Doctor> result = doctorRepository.findById(id);
        Doctor doctor = null;

        if (result.isPresent()) {
            doctor = result.get();
        } else {
            throw new DoctorNotFoundException("Did not find doctor id - " + id);
        }
        return doctor;
    }

    @Override
    @Transactional
    public Doctor findByUserId(Integer id) {
        return doctorRepository.findByUserId(id);
    }
}
