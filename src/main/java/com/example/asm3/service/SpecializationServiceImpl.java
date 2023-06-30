package com.example.asm3.service;

import com.example.asm3.dao.SpecializationRepository;
import com.example.asm3.entity.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SpecializationServiceImpl implements SpecializationService{

    private SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationServiceImpl(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    @Transactional
    public Specialization findById(Integer id) {
        Optional<Specialization> result = specializationRepository.findById(id);
        Specialization specialization = null;

        if (result.isPresent()) {
            specialization = result.get();
        } else {
            throw new RuntimeException("Did not find company id - " + id);
        }
        return specialization;
    }
}
