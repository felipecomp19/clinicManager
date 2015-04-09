package com.texit.clinicManager.consulta;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.texti.clinicManager.model.Account;
import com.texti.clinicManager.model.Consulta;
import com.texti.clinicManager.model.Patient;

public class ConsultaForm  implements Serializable{
	
	private static final long serialVersionUID = 2317738316317985327L;

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	
	private Long id;
	
	@NotNull(message = ConsultaForm.NOT_BLANK_MESSAGE)
	private Long patientId;
	
	@NotNull(message = ConsultaForm.NOT_BLANK_MESSAGE) 
	private Date dataDaConsulta;
	
	private String start;
	private String end;
	
	private Date dataFimDaConsulta;
	
	private Account account;
	
	public ConsultaForm(){ }

	public ConsultaForm(Consulta c) {
		this.setId(c.getId());
		this.setPatientId(c.getPatient().getId());
		this.setDataDaConsulta(c.getDataHoraDaConsulta());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	
	public Date getDataDaConsulta() {
		return dataDaConsulta;
	}

	public void setDataDaConsulta(Date dataDaConsulta) {
		this.dataDaConsulta = dataDaConsulta;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Date getDataFimDaConsulta() {
		return dataFimDaConsulta;
	}

	public void setDataFimDaConsulta(Date dataFimDaConsulta) {
		this.dataFimDaConsulta = dataFimDaConsulta;
	}
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Consulta createConsulta(Account loggedUserAccount) {
		Patient patient = new Patient(patientId, null, null);
		Consulta c = new Consulta(this.id, patient, dataDaConsulta, dataFimDaConsulta);
		c.setAccount(loggedUserAccount);
		return c;
	}
}
