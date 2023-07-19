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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    // Patient set schedule with doctor
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
        } else { // if patient existed, set Description
            patient.setDescription(scheduleRequest.getDescription());
        }
        patientService.save(patient);

        // creat Schedule and save to database
        Doctor doctor = doctorService.findById(doctorId);

        Schedule schedule = new Schedule(scheduleRequest.getDate(), scheduleRequest.getTime(),
                scheduleRequest.getDescription(), 0, doctor, patient,
                doctor.getClinic(), doctor.getSpecialization());
        scheduleService.save(schedule);

        return ResponseEntity.ok(schedule);
    }

    @GetMapping()
    public ResponseEntity<?> getSchedule(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Doctor doctor = doctorService.findByUserId(user.getId());
        List<Schedule> scheduleList = scheduleService.findScheduleByDoctorId(doctor.getId());
        return ResponseEntity.ok(scheduleList);
    }

    @GetMapping("/accept/{scheduleId}")
    public ResponseEntity<?> acceptSchedule(@PathVariable Integer scheduleId, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Doctor doctor = doctorService.findByUserId(user.getId());
        Schedule schedule = scheduleService.findById(scheduleId);
        if(schedule.getDoctor().getId()!=doctor.getId()) return new ResponseEntity<>("Bạn không có lịch khám này!", HttpStatus.BAD_REQUEST);
        schedule.setStatus(1);
        scheduleService.save(schedule);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/cancel/{scheduleId}")
    public ResponseEntity<?> cancelSchedule(@PathVariable Integer scheduleId, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Doctor doctor = doctorService.findByUserId(user.getId());
        Schedule schedule = scheduleService.findById(scheduleId);
        if(schedule.getDoctor().getId()!=doctor.getId()) return new ResponseEntity<>("Bạn không có lịch khám này!", HttpStatus.BAD_REQUEST);
        schedule.setStatus(2);
        scheduleService.save(schedule);
        return ResponseEntity.ok(schedule);
    }

//    @PutMapping()
//    public ResponseEntity<?> updateSchedule(@RequestBody Schedule schedule) {
//        scheduleService.save(schedule);
//        return ResponseEntity.ok(schedule);
//    }

    @GetMapping("patients/{patientId}")
    public ResponseEntity<?> getScheduleOfPatient(@PathVariable Integer patientId) {
        List<Schedule> schedules = scheduleService.findByPatientId(patientId);
        if(schedules.isEmpty()) return new ResponseEntity<>("Not found any schedule with patientId " + patientId, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("doctors/{doctorId}")
    public ResponseEntity<?> getScheduleOfDoctor(@PathVariable Integer doctorId) {
        List<Schedule> schedules = scheduleService.findScheduleByDoctorId(doctorId);
        if(schedules.isEmpty()) return new ResponseEntity<>("Not found any schedule with doctorId " + doctorId, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(schedules);
    }

}
