package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;

@RestController
@RequestMapping("/api")
public class HospitalController {
	
	@Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @PostMapping("/assignDoctor/{condition}")
    public ResponseEntity<String> assignDoctor(@PathVariable String condition, @RequestBody Patient patient) {
        Doctor doctor = doctorService.findBySpecialization(getSpecializationForCondition(condition));
        if (doctor != null) {
            patientService.assignDoctor(patient, doctor);
            return ResponseEntity.ok("Doctor assigned successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found for the given condition");
        }
    }

	private String getSpecializationForCondition(String condition) {
		switch (condition.toLowerCase()) {
        case "heart":
            return "Cardiologist";
        case "kidney":
            return "Nephrologist";
        case "liver":
            return "Hepatologist";
        // Add more condition-specialization mappings as needed
        default:
            return "General Practitioner"; // Default specialization if condition doesn't match known cases
    }
	}

	@PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor newDoctor) {
        try {
            if (newDoctor == null) {
                return ResponseEntity.badRequest().body("Invalid request body");
            }

            doctorService.saveDoctor(newDoctor);

            return ResponseEntity.ok("Doctor added to the database successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding doctor");
        }
    }

    @GetMapping("/getDoctors")
    public ResponseEntity<List<Doctor>> getDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        try {
            patientService.addPatient(patient);
            return ResponseEntity.ok("Patient added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding patient");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
    
    @GetMapping("/generateReport/{patientId}")
    public ResponseEntity<String> generatePatientReport(@PathVariable Long patientId) {
        try {
            Patient patient = patientService.getPatientById(patientId);

            if (patient != null) {
                // Assuming you have a method to generate a report for a patient
                String patientReport = generateReport(patient);
                return ResponseEntity.ok(patientReport);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error generating patient report");
        }
    }

    private String generateReport(Patient patient) {
        // Logic to generate the patient report
        // You can format the report as needed, including patient details like name, age, etc.
        // For simplicity, let's return a basic string here
        return "Patient Report\n" +
                "Name: " + patient.getName().hashCode() + "\n" +
                "Gender" + patient.getGender()+ "\n" +
                "Blood Pressure" + patient.getBloodPressure()+ "\n" +
                "Blood Type" + patient.getBloodType()+ "\n" +
                "Age: " + HashUtils.hashString(String.valueOf(patient.getAge())) + "\n" +
                "Weight: " + patient.getWeight() + "\n" +
                // Add other patient details...
                "Condition: " + patient.getCondition();
    }

}

/*url to add doctor -(POST) http://localhost:8080/api/addDoctor
	
{
    "name": "Dr. Joseph",
    "specialization": "Hepatologist"
}

url to add doctor -(POST) http://localhost:8080/api/add
{
  "name": "John Doe",
  "weight": 70.5,
  "bloodPressure": "120/80",
  "height": 175.0,
  "age": 30,
  "gender": "Male",
  "bloodType": "O+",
  "condition": "none"
}
url to assign doctor -(POST) http://localhost:8080/api/assignDoctor/heart
{
  "name": "Rohit Sharma",
  "weight": 72.6,
  "bloodPressure": "120/80",
  "height": 185.0,
  "age": 52,
  "gender": "Male",
  "bloodType": "O+",
  "condition": "Kidney"
}
url to get report of a patient using patient id -(GET) http://localhost:8080/api/generateReport/2

*/