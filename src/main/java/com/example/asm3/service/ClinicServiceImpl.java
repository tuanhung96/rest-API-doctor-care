package com.example.asm3.service;

import com.example.asm3.dao.ClinicRepository;
import com.example.asm3.entity.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService{
    private ClinicRepository clinicRepository;

    @Autowired
    public ClinicServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public Clinic findById(Integer id) {
        Optional<Clinic> result = clinicRepository.findById(id);
        Clinic clinic = null;

        if (result.isPresent()) {
            clinic = result.get();
        } else {
            throw new RuntimeException("Did not find clinic id - " + id);
        }
        return clinic;
    }
}
