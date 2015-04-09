package com.texti.clinicManager.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.texti.clinicManager.core.repository.PatientRepository;
import com.texti.clinicManager.core.service.IPatientService;
import com.texti.clinicManager.core.service.impl.PatientService;
import com.texti.clinicManager.model.Account;
import com.texti.clinicManager.model.Patient;

@RunWith(MockitoJUnitRunner.class)
public class PacienteServiceTest {
	
	@InjectMocks
	private IPatientService pacienteService = new PatientService();
	
	@Mock
	private PatientRepository patientRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void shouldCreateOnePatient(){
		Account account = new Account("felipe@gmail.com", "123456", "ROLE_USER");
		Patient newPaciente = new Patient(new Long(1), "Flavia", account);
		
		
		pacienteService.createPatient(newPaciente);
		verify(patientRepository, times(1)).save(any(Patient.class));
	}
	
	@Test
	public void shouldReturnOndePatientById(){
		int id = 1;
		String name = "Flavia";
		Account account = new Account("felipe@gmail.com", "123456", "ROLE_USER");
		Patient newPaciente = new Patient(new Long(id), name, account);
		when(patientRepository.findById(new Long(id))).thenReturn(newPaciente);
		Patient _patient = pacienteService.findById(new Long(id));
		Assert.assertEquals(_patient.getName(), name);
	}
	
	@Test
	public void shouldReturnTwoClientByAccountId(){
		Account account = new Account("felipe@gmail.com", "123456", "ROLE_USER");
		Patient p1 = new Patient(new Long(1), "Flavia", account);
		Patient p2 = new Patient(new Long(2), "Felipe", account);
		List<Patient> patients = new ArrayList<Patient>();
		patients.add(p1);
		patients.add(p2);
		when(patientRepository.findByAccountId(new Long(1))).thenReturn(patients);
		
		List<Patient> _patients = pacienteService.FindByAccountId(new Long(1));
		Assert.assertEquals(_patients.size(), 2);
	}
}
