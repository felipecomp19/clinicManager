package com.texti.clinicManager.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.texti.clinicManager.model.Consulta;

@Repository
@Transactional(readOnly = true)
public class ConsultaRepository {

	private static final Logger logger = LoggerFactory.getLogger(ConsultaRepository.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Consulta save(Consulta consulta) {
		try{
			entityManager.persist(consulta);
		}catch(Exception e){
			logger.error("Erro ao salvar a consulta", e);
		}
		return consulta;
	}

	@Transactional
	public Consulta update(Consulta consulta) {
		entityManager.merge(consulta);
		return consulta;
	}

	@Transactional
	public void delete(Consulta consulta) {
		try {
			Consulta c = this.findById(consulta.getId());
			entityManager.remove(c);
		} catch (Exception e) {
			logger.error("Erro ao deletar", e);
		}
	}

	public Consulta findById(Long id) {
		try {
			return entityManager
					.createNamedQuery(Consulta.FIND_BY_ID, Consulta.class)
					.setParameter("id", id).getSingleResult();
		} catch (PersistenceException e) {
			logger.error("Erro ao buscar por id", e);
			return null;
		}
	}

	public List<Consulta> findAllByAccountId(Long accountId) {
		try {
			return entityManager
					.createNamedQuery(Consulta.FIND_BY_ACCOUNT_ID,
							Consulta.class).setParameter("id", accountId)
					.getResultList();
		} catch (PersistenceException e) {
			logger.error("Erro ao buscar todos por account_id", e);
			return null;
		}
	}
}
