package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorService doctorService;

    @Override
    public List<Patient> findByCondition(String condition) {
        return patientRepository.findByCondition(condition);
    }

    @Override
    public void assignDoctor(Patient patient, Doctor doctor) {
        patient.setDoctor(doctor);
        patientRepository.save(patient);
    }
    public void addPatient(Patient patient) {
        // Assuming you have logic to determine the condition and get the corresponding doctor specialization
        String condition = patient.getCondition();
        String specialization = getSpecializationForCondition(condition);

        // Get a doctor based on specialization
        Doctor doctor = doctorService.findBySpecialization(specialization);

        // Set the doctor for the patient
        patient.setDoctor(doctor);

        // Save the patient
        patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    private String getSpecializationForCondition(String condition) {
        // TODO: Add your logic to map conditions to doctor specializations
        // For example, if condition is "heart", return "Cardiologist"
        // This method needs to be implemented based on your business logic
        return null;
    }

	@Override
	public Patient getPatientById(Long id) {
		 return patientRepository.findById(id).orElse(null);
	}

	@Override
	public List<Patient> getPatientsByDoctor(Doctor doctor) {
		return patientRepository.findByDoctor(doctor);
	}

	
}
