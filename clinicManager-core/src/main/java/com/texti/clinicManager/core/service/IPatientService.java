package com.texti.clinicManager.core.service;

import java.util.List;

import com.texti.clinicManager.model.Consulta;
import com.texti.clinicManager.model.Patient;

public interface IPatientService {

	Patient createPatient(Patient newPaciente);
	
	Patient updatePatient(Patient patient);
	
	void deletePatient(Patient patient);

	Patient findById(Long id);

	List<Patient> FindByAccountId(Long accountId);

}
