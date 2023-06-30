package com.example.asm3.service;

import com.example.asm3.dao.DoctorRepository;
import com.example.asm3.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
