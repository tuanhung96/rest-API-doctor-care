package com.example.asm3.controller;

import com.example.asm3.service.ClinicService;
import com.example.asm3.service.DoctorService;
import com.example.asm3.service.PatientService;
import com.example.asm3.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private ScheduleService scheduleService;
    private DoctorService doctorService;
    private PatientService patientService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, DoctorService doctorService, PatientService patientService) {
        this.scheduleService = scheduleService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PostMapping("")
    public ResponseEntity<?> addSchedule(Principal principal) {

        return ResponseEntity.ok();
    }
}
