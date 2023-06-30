package com.example.asm3.controller;

import com.example.asm3.entity.Doctor;
import com.example.asm3.entity.Specialization;
import com.example.asm3.service.DoctorService;
import com.example.asm3.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private DoctorService doctorService;
    private SpecializationService specializationService;

    @Autowired
    public DoctorController(DoctorService doctorService, SpecializationService specializationService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
    }

    @GetMapping("/specializations/{specializationName}")
    public ResponseEntity<?> findDoctorBySpecialization(@PathVariable String specializationName) {
        List<Specialization> specializations = specializationService.findByName(specializationName);
        List<Doctor> doctors = new ArrayList<>();
        for (Specialization specialization: specializations) {
            System.out.println(specialization.getId());
            List<Doctor> doctorList = doctorService.findBySpecializationId(specialization.getId());
            doctors.addAll(doctorList);
        }

        return ResponseEntity.ok(doctors);
    }
}
