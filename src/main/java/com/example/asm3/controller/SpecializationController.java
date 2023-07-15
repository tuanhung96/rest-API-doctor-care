package com.example.asm3.controller;

import com.example.asm3.entity.Specialization;
import com.example.asm3.service.ScheduleService;
import com.example.asm3.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/specializations")
public class SpecializationController {

    private SpecializationService specializationService;
    private ScheduleService scheduleService;

    @Autowired
    public SpecializationController(SpecializationService specializationService, ScheduleService scheduleService) {
        this.specializationService = specializationService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/top5")
    public ResponseEntity<?> getTop5() {
        List<Specialization> specializations = getTop5Specialization();

        return ResponseEntity.ok(specializations);
    }

    public List<Specialization> getTop5Specialization() {
        List<Integer> top5SpecializationId = scheduleService.getTop5SpecializationId();
        List<Specialization> specializations = new ArrayList<>();
        for (int i=0; i<top5SpecializationId.size();i++) {
            Specialization specialization = specializationService.findById(top5SpecializationId.get(i));
            specializations.add(specialization);
        }
        return specializations;
    }
}
