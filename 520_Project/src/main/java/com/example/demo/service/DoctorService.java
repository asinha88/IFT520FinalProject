package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Doctor;

public interface DoctorService {
	Doctor findBySpecialization(String specialization);
	void saveDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();
}
