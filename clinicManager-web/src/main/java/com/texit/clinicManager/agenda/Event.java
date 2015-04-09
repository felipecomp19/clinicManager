package com.texit.clinicManager.agenda;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable{
	
	private static final long serialVersionUID = -8511421579557747349L;

	public Long id;
	public Long consultaId;
	public Long patientId;
    public String title;
    public Date start;
    public Date end;
    
    public Event(){ }
    
	public Event(Long consultaId, Long patientId, String title,
			Date start, Date end) {
		super();
		this.id = consultaId;
		this.consultaId = consultaId;
		this.patientId = patientId;
		this.title = title;
		this.start = start;
		this.end = end;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getConsultaId() {
		return consultaId;
	}
	public void setConsultaId(Long consultaId) {
		this.consultaId = consultaId;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
}
