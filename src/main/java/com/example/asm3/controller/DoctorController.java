package com.example.asm3.controller;

import com.example.asm3.entity.Doctor;
import com.example.asm3.entity.Patient;
import com.example.asm3.entity.Specialization;
import com.example.asm3.entity.User;
import com.example.asm3.service.DoctorService;
import com.example.asm3.service.PatientService;
import com.example.asm3.service.SpecializationService;
import com.example.asm3.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private DoctorService doctorService;
    private SpecializationService specializationService;
    private UserService userService;
    private PatientService patientService;

    @Autowired
    public DoctorController(DoctorService doctorService, SpecializationService specializationService, UserService userService, PatientService patientService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
        this.userService = userService;
        this.patientService = patientService;
    }

    // get doctors with specialization's name
    @GetMapping("/specializations/{specializationName}")
    public ResponseEntity<?> findDoctorBySpecialization(@PathVariable String specializationName) {
        List<Specialization> specializations = specializationService.findByName(specializationName);
        List<Doctor> doctors = new ArrayList<>();
        for (Specialization specialization: specializations) {
            List<Doctor> doctorList = doctorService.findBySpecializationId(specialization.getId());
            doctors.addAll(doctorList);
        }

        return ResponseEntity.ok(doctors);
    }

    // doctor send email to patient
    @GetMapping("/email/patients/{patientId}")
    public ResponseEntity<?> sendEmailToPatient(@PathVariable Integer patientId, Principal principal) throws MessagingException, UnsupportedEncodingException {
        User user = userService.findByEmail(principal.getName());
        Doctor doctor = doctorService.findByUserId(user.getId());
        Patient patient = patientService.findById(patientId);
        doctorService.sendEmailToPatient(doctor, patient);
        return ResponseEntity.ok("Sent email successfully!");
    }
}
