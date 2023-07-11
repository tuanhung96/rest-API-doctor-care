package com.example.asm3.service;

import com.example.asm3.entity.Doctor;
import com.example.asm3.entity.Patient;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;


public interface DoctorService {

    List<Doctor> findBySpecializationId(Integer id);

    Doctor findById(Integer doctorId);

    Doctor findByUserId(Integer id);

    void save(Doctor doctor);

    void sendEmailToPatient(Doctor doctor, Patient patient) throws MessagingException, UnsupportedEncodingException;
}
