package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor findBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }
    @Override
    public void saveDoctor(Doctor doctor) {
        // Add any business logic or validation before saving
        doctorRepository.save(doctor);
    }
    public Doctor getDoctorBySpecialization(String specialization) {
        // Assuming you have a method in your repository to find a doctor by specialization
        return doctorRepository.findBySpecialization(specialization);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
