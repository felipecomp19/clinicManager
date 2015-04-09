package com.texti.clinicManager.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.texti.clinicManager.core.repository.ConsultaRepository;
import com.texti.clinicManager.core.service.IConsultaService;
import com.texti.clinicManager.core.service.impl.ConsultaService;
import com.texti.clinicManager.model.Account;
import com.texti.clinicManager.model.Consulta;
import com.texti.clinicManager.model.Patient;

@RunWith(MockitoJUnitRunner.class)
public class ConsultaServiceTest {
	
	@InjectMocks
	private IConsultaService consultaService = new ConsultaService();
	
	@Mock
	private ConsultaRepository consultaRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void shouldCreateOnePatient(){
		Account account = new Account("felipe@gmail.com", "123456", "ROLE_USER");
		Patient paciente = new Patient(new Long(1), "Flavia", account);
		Consulta consulta = new Consulta(new Long(1), paciente, new Date(), new Date());
		
		
		consultaService.createConsulta(consulta);
		verify(consultaRepository, times(1)).save(any(Consulta.class));
	}
	
	@Test
	public void shouldReturnOndePatientById(){
		int id = 1;
		String name = "Flavia";
		Account account = new Account("felipe@gmail.com", "123456", "ROLE_USER");
		Patient paciente = new Patient(new Long(id), name, account);
		Consulta consulta = new Consulta(new Long(1), paciente, new Date(), new Date());
		
		when(consultaRepository.findById(new Long(id))).thenReturn(consulta);
		Consulta _consulta = consultaService.findById(new Long(id));
		Assert.assertEquals(_consulta.getPatient().getName(), name);
	}
	
	@Test
	public void shouldReturnTwoClientByAccountId(){
		Account account = new Account("felipe@gmail.com", "123456", "ROLE_USER");
		Patient p1 = new Patient(new Long(1), "Flavia", account);
		Patient p2 = new Patient(new Long(2), "Felipe", account);
		Consulta c1 = new Consulta(new Long(1), p1, new Date(), new Date());
		Consulta c2 = new Consulta(new Long(1), p2, new Date(), new Date());
		List<Consulta> consultas = new ArrayList<Consulta>();
		consultas.add(c1);
		consultas.add(c2);

		when(consultaRepository.findAllByAccountId(new Long(1))).thenReturn(consultas);
		List<Consulta> _consultas = consultaService.findAllByAccountId(new Long(1));
		Assert.assertEquals(_consultas.size(), 2);
	}
}
