package com.example.demo.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.service.DoctorServiceImpl;

public class DoctorServiceImplTest {
		@Mock
	    private DoctorRepository doctorRepository;

	    @InjectMocks
	    private DoctorServiceImpl doctorService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    void testFindBySpecialization() {
	        // Arrange
	        String specialization = "Cardiologist";
	        Doctor expectedDoctor = new Doctor();
	        expectedDoctor.setSpecialization(specialization);

	        when(doctorRepository.findBySpecialization(specialization)).thenReturn(expectedDoctor);

	        // Act
	        Doctor actualDoctor = doctorService.findBySpecialization(specialization);

	        // Assert
	        assertEquals(expectedDoctor, actualDoctor);
	        verify(doctorRepository, times(1)).findBySpecialization(specialization);
	    }

	    @Test
	    void testSaveDoctor() {
	        // Arrange
	        Doctor doctorToSave = new Doctor();

	        // Act
	        doctorService.saveDoctor(doctorToSave);

	        // Assert
	        verify(doctorRepository, times(1)).save(doctorToSave);
	    }

	    @Test
	    void testGetAllDoctors() {
	        // Arrange
	        List<Doctor> expectedDoctors = new ArrayList<>();
	        expectedDoctors.add(new Doctor());
	        expectedDoctors.add(new Doctor());

	        when(doctorRepository.findAll()).thenReturn(expectedDoctors);

	        // Act
	        List<Doctor> actualDoctors = doctorService.getAllDoctors();

	        // Assert
	        assertEquals(expectedDoctors, actualDoctors);
	        verify(doctorRepository, times(1)).findAll();
	    }
}
