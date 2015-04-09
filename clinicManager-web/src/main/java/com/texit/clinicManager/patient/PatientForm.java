package com.texit.clinicManager.patient;

import org.hibernate.validator.constraints.NotBlank;

import com.texti.clinicManager.model.Account;
import com.texti.clinicManager.model.Patient;

public class PatientForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	
	private Long id;
	
	@NotBlank(message = PatientForm.NOT_BLANK_MESSAGE)
	private String name;
	
	private Account account;
	
	public PatientForm(){ }
	
	public PatientForm(Account account){
		this.account = account;
	}

	public PatientForm(Patient p) {
		this.setId(p.getId());
		this.setName(p.getName());
		this.setAccount(p.getAccount());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Patient createPatient(Account loggedUserAccount) {
		return new Patient(this.id, this.name, loggedUserAccount);
	}
}
