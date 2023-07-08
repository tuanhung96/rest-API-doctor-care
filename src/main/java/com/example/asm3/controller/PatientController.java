package com.example.asm3.controller;

import com.example.asm3.entity.Doctor;
import com.example.asm3.entity.Patient;
import com.example.asm3.entity.User;
import com.example.asm3.service.DoctorService;
import com.example.asm3.service.PatientService;
import com.example.asm3.service.ScheduleService;
import com.example.asm3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private PatientService patientService;
    private UserService userService;
    private DoctorService doctorService;
    private ScheduleService scheduleService;

    @Autowired
    public PatientController(PatientService patientService, UserService userService, DoctorService doctorService, ScheduleService scheduleService) {
        this.patientService = patientService;
        this.userService = userService;
        this.doctorService = doctorService;
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<?> getPatients(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Doctor doctor = doctorService.findByUserId(user.getId());
        List<Integer> patientIdList = scheduleService.findByDoctorId(doctor.getId());
        List<Patient> patientList = new ArrayList<>();
        for (Integer patientId: patientIdList) {
            Patient patient = patientService.findById(patientId);
            patientList.add(patient);
        }
        return ResponseEntity.ok(patientList);
    }
}
