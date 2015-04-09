package com.texti.clinicManager.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.texti.clinicManager.core.repository.ConsultaRepository;
import com.texti.clinicManager.core.service.IConsultaService;
import com.texti.clinicManager.model.Consulta;

@Component
public class ConsultaService implements IConsultaService {

	@Autowired
	private ConsultaRepository consultaRepo;
	
	@Override
	public Consulta createConsulta(Consulta consulta) {
		return this.consultaRepo.save(consulta);
	}

	@Override
	public Consulta findById(Long id) {
		return consultaRepo.findById(id);
	}

	@Override
	public List<Consulta> findAllByAccountId(Long accountId) {
		return consultaRepo.findAllByAccountId(accountId);
	}

	@Override
	public Consulta updateConsulta(Consulta consulta) {
		return consultaRepo.update(consulta);
	}

	@Override
	public void deleteConsulta(Consulta consulta) {
		this.consultaRepo.delete(consulta);
	}
}
