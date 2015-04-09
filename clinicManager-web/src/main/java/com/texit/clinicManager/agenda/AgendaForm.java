package com.texit.clinicManager.agenda;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import com.texti.clinicManager.model.Patient;

public class AgendaForm {

	private List<Patient> pacientes;
	private List<PatientVO> dataEvents;

	public AgendaForm(List<Patient> pacientes) {
		if (pacientes != null) {
			this.pacientes = pacientes;
			this.dataEvents = new ArrayList<PatientVO>(pacientes.size());
			for (Patient patient : pacientes) {
				this.dataEvents.add(new PatientVO(patient.getId(), patient.getName()));
			}
		}
	}
	
	public List<Patient> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Patient> pacientes) {
		this.pacientes = pacientes;
	}

	public List<PatientVO> getDataEvents() {
		return dataEvents;
	}

	public void setDataEvents(List<PatientVO> dataEvents) {
		this.dataEvents = dataEvents;
	}

	final class PatientVO extends Patient {

		private static final long serialVersionUID = 1L;
		
		private String dataEvent;

		public PatientVO(Long id, String title) {
			super(id, title, null);
		}

		public String getDataEvent() {
			JsonObject eventJason = Json.createObjectBuilder()
					.add("id", getId()).add("title", getName()).build();

			return eventJason.toString();
		}
	}
}
