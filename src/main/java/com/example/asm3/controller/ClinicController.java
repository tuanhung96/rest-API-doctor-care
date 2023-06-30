package com.example.asm3.controller;

import com.example.asm3.entity.Clinic;
import com.example.asm3.service.ClinicService;
import com.example.asm3.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clinics")
public class ClinicController {
    private ClinicService clinicService;
    private ScheduleService scheduleService;

    @Autowired
    public ClinicController(ClinicService clinicService, ScheduleService scheduleService) {
        this.clinicService = clinicService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/top5")
    public ResponseEntity<?> getTop5() {
        List<Clinic> clinics = getTop5Clinic();

        return ResponseEntity.ok(clinics);
    }

    public List<Clinic> getTop5Clinic() {
        List<Integer> top5ClinicId = scheduleService.getTop5ClinicId();
        List<Clinic> clinics = new ArrayList<>();
        for (int i=0; i<top5ClinicId.size();i++) {
            Clinic clinic = clinicService.findById(top5ClinicId.get(i));
            clinics.add(clinic);
        }
        return clinics;
    }

    @GetMapping("")
    public ResponseEntity<?> searchByAddress(@RequestParam("address") String address) {
        List<Clinic> clinics = clinicService.findByAddress(address);

        return ResponseEntity.ok(clinics);
    }

}
