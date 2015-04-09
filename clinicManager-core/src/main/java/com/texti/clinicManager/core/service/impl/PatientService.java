package com.texti.clinicManager.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.texti.clinicManager.core.repository.PatientRepository;
import com.texti.clinicManager.core.service.IPatientService;
import com.texti.clinicManager.model.Patient;

@Component
public class PatientService implements IPatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public Patient createPatient(Patient newPatient) {
		return patientRepository.save(newPatient);
	}
	
	@Override
	public Patient updatePatient(Patient patient) {
		return patientRepository.update(patient);
	}
	
	@Override
	public void deletePatient(Patient patient) {
		patientRepository.delete(patient);
	}
	

	@Override
	public Patient findById(Long id) {
		return patientRepository.findById(id);
	}

	@Override
	public List<Patient> FindByAccountId(Long accountId) {
		return patientRepository.findByAccountId(accountId);
	}
}
