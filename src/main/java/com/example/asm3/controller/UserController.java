package com.example.asm3.controller;

import com.example.asm3.entity.Doctor;
import com.example.asm3.entity.Patient;
import com.example.asm3.entity.User;
import com.example.asm3.service.DoctorService;
import com.example.asm3.service.PatientService;
import com.example.asm3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private DoctorService doctorService;
    private PatientService patientService;

    @Autowired
    public UserController(UserService userService, DoctorService doctorService, PatientService patientService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> get(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/disable/doctors/{doctorId}")
    public ResponseEntity<?> disableDoctor(@PathVariable Integer doctorId) {
        Doctor doctor = doctorService.findById(doctorId);
        User user = userService.findById(doctor.getUser().getId());
        user.setEnabled(false);
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/enable/doctors/{doctorId}")
    public ResponseEntity<?> enableDoctor(@PathVariable Integer doctorId) {
        Doctor doctor = doctorService.findById(doctorId);
        User user = userService.findById(doctor.getUser().getId());
        user.setEnabled(true);
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/disable/patients/{patientId}")
    public ResponseEntity<?> disablePatient(@PathVariable Integer patientId) {
        Patient patient = patientService.findById(patientId);
        User user = userService.findById(patient.getUser().getId());
        user.setEnabled(false);
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/enable/patients/{patientId}")
    public ResponseEntity<?> enablePatient(@PathVariable Integer patientId) {
        Patient patient = patientService.findById(patientId);
        User user = userService.findById(patient.getUser().getId());
        user.setEnabled(true);
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }
}
