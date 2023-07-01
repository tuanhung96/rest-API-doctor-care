package com.example.asm3.controller;

import com.example.asm3.service.ScheduleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private ScheduleService scheduleService;


}
