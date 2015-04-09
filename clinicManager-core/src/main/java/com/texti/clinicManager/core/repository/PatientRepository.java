package com.texti.clinicManager.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.texti.clinicManager.model.Patient;

@Repository
@Transactional(readOnly = true)
public class PatientRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(PatientRepository.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Patient save(Patient newPatient){
		entityManager.persist(newPatient);
		return newPatient;
	}
	
	@Transactional
	public Patient update(Patient patient) {
		entityManager.merge(patient);
		return patient;
	}
	
	@Transactional
	public void delete(Patient patient) {
		Patient _p = this.findById(patient.getId());
		entityManager.remove(_p);
	}

	public Patient findById(Long id) {
		try {
			return entityManager.createNamedQuery(Patient.FIND_BY_ID, Patient.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (PersistenceException e) {
			logger.error("Erro ao buscar por id", e);
			return null;
		}
	}

	public List<Patient> findByAccountId(Long id) {
		try {
			return entityManager.createNamedQuery(Patient.FIND_BY_ACCOUNT_ID, Patient.class)
					.setParameter("id", id)
					.getResultList();
		} catch (PersistenceException e) {
			logger.error("Erro ao buscar todos por account_id", e);
			return null;
		}
	}
}
