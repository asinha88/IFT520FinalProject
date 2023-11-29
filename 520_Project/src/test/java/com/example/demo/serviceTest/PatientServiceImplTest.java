package com.example.demo.serviceTest;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {
	@Mock
    private PatientRepository patientRepository;

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    void testFindByCondition() {
        // Arrange
        String condition = "someCondition";
        List<Patient> expectedPatients = new ArrayList<>();
        when(patientRepository.findByCondition(condition)).thenReturn(expectedPatients);

        // Act
        List<Patient> actualPatients = patientService.findByCondition(condition);

        // Assert
        assertEquals(expectedPatients, actualPatients);
    }

    @Test
    void testAssignDoctor() {
        // Arrange
        Patient patient = new Patient();
        Doctor doctor = new Doctor();

        // Act
        patientService.assignDoctor(patient, doctor);

        // Assert
        verify(patientRepository, times(1)).save(patient);
        assertEquals(doctor, patient.getDoctor());
    }



    @Test
    void testGetAllPatients() {
        // Arrange
        List<Patient> expectedPatients = new ArrayList<>();
        when(patientRepository.findAll()).thenReturn(expectedPatients);

        // Act
        List<Patient> actualPatients = patientService.getAllPatients();

        // Assert
        assertEquals(expectedPatients, actualPatients);
    }

    @Test
    void testGetPatientById() {
        // Arrange
        Long patientId = 1L;
        Patient expectedPatient = new Patient();
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(expectedPatient));

        // Act
        Patient actualPatient = patientService.getPatientById(patientId);

        // Assert
        assertEquals(expectedPatient, actualPatient);
    }

    @Test
    void testGetPatientsByDoctor() {
        // Arrange
        Doctor doctor = new Doctor();
        List<Patient> expectedPatients = new ArrayList<>();
        when(patientRepository.findByDoctor(doctor)).thenReturn(expectedPatients);

        // Act
        List<Patient> actualPatients = patientService.getPatientsByDoctor(doctor);

        // Assert
        assertEquals(expectedPatients, actualPatients);
    }
}
