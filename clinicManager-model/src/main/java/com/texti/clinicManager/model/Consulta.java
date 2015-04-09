package com.texti.clinicManager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_consulta")
@NamedQueries({
	@NamedQuery(name = Consulta.FIND_BY_ID, query = "SELECT c FROM Consulta c WHERE c.id = :id"),
	@NamedQuery(name = Consulta.FIND_BY_ACCOUNT_ID, query = "SELECT c FROM Consulta c JOIN c.account a where c.account.id = :id")
})
public class Consulta extends BaseModel implements Serializable{
	
	public static final String FIND_BY_ID = "Consulta.findById";
	public static final String FIND_BY_ACCOUNT_ID = "Consulta.findByAccountId";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	private Date dataHoraDaConsulta;
	
	private Date dataHoraFimDaConsulta;
	
	public Consulta(){ }

	public Consulta(Long id, Patient paciente, Date dateInicio, Date dateFim) {
		super(id);
		this.setPatient(paciente);
		this.setAccount(paciente == null ? null : paciente.getAccount());
		this.setDataHoraDaConsulta(dateInicio);
		this.setDataHoraFimDaConsulta(dateFim);
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDataHoraDaConsulta() {
		return dataHoraDaConsulta;
	}

	public void setDataHoraDaConsulta(Date dataHoraDaConsulta) {
		this.dataHoraDaConsulta = dataHoraDaConsulta;
	}

	public Date getDataHoraFimDaConsulta() {
		return dataHoraFimDaConsulta;
	}

	public void setDataHoraFimDaConsulta(Date dataHoraFimDaConsulta) {
		this.dataHoraFimDaConsulta = dataHoraFimDaConsulta;
	}
}
