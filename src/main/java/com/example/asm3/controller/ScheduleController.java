package com.example.asm3.controller;

import com.example.asm3.entity.Doctor;
import com.example.asm3.entity.Patient;
import com.example.asm3.entity.Schedule;
import com.example.asm3.entity.User;
import com.example.asm3.model.ScheduleRequest;
import com.example.asm3.service.DoctorService;
import com.example.asm3.service.PatientService;
import com.example.asm3.service.ScheduleService;
import com.example.asm3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private ScheduleService scheduleService;
    private DoctorService doctorService;
    private PatientService patientService;
    private UserService userService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, DoctorService doctorService,
                              PatientService patientService, UserService userService) {
        this.scheduleService = scheduleService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.userService = userService;
    }

    @PostMapping("/doctors/{doctorId}")
    public ResponseEntity<?> addSchedule(Principal principal, @PathVariable Integer doctorId,
                                         @RequestBody ScheduleRequest scheduleRequest) {
        User user = userService.findByEmail(principal.getName());

        // find Patient
        Patient patient = patientService.findByUserId(user.getId());

        // if Patient does not exist, creat Patient
        if (patient == null) {
            patient = new Patient(user.getName(), user.getGender(), user.getDateOfBirth(),
                    scheduleRequest.getDescription(), user);
        } else {
            patient.setDescription(scheduleRequest.getDescription());
        }
        patientService.save(patient);

        // creat Schedule
        Doctor doctor = doctorService.findById(doctorId);
        Schedule schedule = new Schedule(scheduleRequest.getDate(), scheduleRequest.getTime(),
                scheduleRequest.getDescription(), doctor, patient,
                doctor.getClinic(), doctor.getSpecialization());

        return ResponseEntity.ok(schedule);
    }
}
