package com.example.asm3.service;

import com.example.asm3.entity.Clinic;

import java.util.List;

public interface ClinicService {
    Clinic findById(Integer id);

    List<Clinic> findByAddress(String address);
}
