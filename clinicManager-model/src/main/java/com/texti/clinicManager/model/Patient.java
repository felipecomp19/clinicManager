package com.texti.clinicManager.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_patient")
@NamedQueries({
	@NamedQuery(name = Patient.FIND_BY_ID, query = "SELECT p FROM Patient p WHERE p.id = :id"),
	@NamedQuery(name = Patient.FIND_BY_ACCOUNT_ID, query = "SELECT p FROM Patient p JOIN p.account a where p.account.id = :id")
})
public class Patient extends BaseModel implements java.io.Serializable {
	
	public static final String FIND_BY_ID = "Patient.findById";
	public static final String FIND_BY_ACCOUNT_ID = "Patient.findByAccountId";
	
	private String name;
	
	public Patient(){ }
	
	public Patient(Long id, String name, Account account) {
		super(id);
		this.setName(name);
		this.setAccount(account);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
