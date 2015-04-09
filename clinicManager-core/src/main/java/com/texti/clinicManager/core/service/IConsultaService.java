package com.texti.clinicManager.core.service;

import java.util.List;

import com.texti.clinicManager.model.Consulta;

public interface IConsultaService {

	Consulta createConsulta(Consulta consulta);

	Consulta updateConsulta(Consulta createConsulta);
	
	void deleteConsulta(Consulta consulta);
	
	Consulta findById(Long long1);

	List<Consulta> findAllByAccountId(Long accountId);
}
