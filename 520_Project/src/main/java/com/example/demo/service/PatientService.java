package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;

public interface PatientService {
	List<Patient> findByCondition(String condition);
    void assignDoctor(Patient patient, Doctor doctor);
	void addPatient(Patient patient);
	List<Patient> getAllPatients();

  

    Patient getPatientById(Long id);


    List<Patient> getPatientsByDoctor(Doctor doctor);
	
}
