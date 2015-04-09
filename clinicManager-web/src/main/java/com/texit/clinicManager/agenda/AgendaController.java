package com.texit.clinicManager.agenda;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.texit.clinicManager.consulta.ConsultaForm;
import com.texit.clinicManager.utils.BaseController;
import com.texti.clinicManager.core.service.IConsultaService;
import com.texti.clinicManager.core.service.IPatientService;
import com.texti.clinicManager.model.Consulta;
import com.texti.clinicManager.model.Patient;

@Controller
@Configuration
@RequestMapping(value = "/agenda")
public class AgendaController extends BaseController {

	private static String AGENDA = "agenda/full_calendar";

	@Autowired
	private IConsultaService consultaService;

	@Autowired
	private IPatientService patientService;

	@RequestMapping(value = "/")
	public String agenda(Model model, HttpServletRequest request) {
		List<Patient> patients = this.patientService.FindByAccountId(super.getLoggedUserAccount(request).getId());
		AgendaForm consultaForma = new AgendaForm(patients);
		model.addAttribute("consultaForm", consultaForma);
		return AGENDA;
	}

	/**
	 * receives json data sent by client --> map it to Person object
	 * 
	 * @param consulta {@link Consulta}
	 *           
	 * @return  {@link Consulta}
	 */
	@RequestMapping(value = "consulta/create", method = RequestMethod.POST)
	public @ResponseBody Consulta post(@RequestBody final ConsultaForm consultaForm, HttpServletRequest request) {
		Consulta consulta = consultaForm.createConsulta(super.getLoggedUserAccount(request));
		this.consultaService.createConsulta(consulta);
		return consulta;
	}
	
	@RequestMapping(value = "consulta/update", method = RequestMethod.POST)
	public @ResponseBody Consulta update(@RequestBody final ConsultaForm consultaForm, HttpServletRequest request) {
		Consulta consulta = consultaForm.createConsulta(super.getLoggedUserAccount(request));
		this.consultaService.updateConsulta(consulta);
		return consulta;
	}
	
	@RequestMapping(value = "consulta/list", method = RequestMethod.GET)
	public @ResponseBody List<Event> list(HttpServletRequest request) {
		//TODO filtrar por data (start, end). Na request já esta sendo enviado a data inicial (start) e a data final(end).
		List<Consulta> consultas = this.consultaService.findAllByAccountId(super.getLoggedUserAccount(request).getId());
		List<Event> events = new ArrayList<Event>(consultas.size());
		for (Consulta consulta : consultas) {
			Event e = new Event(consulta.getId(), consulta.getPatient().getId(), consulta.getPatient().getName(), consulta.getDataHoraDaConsulta(), consulta.getDataHoraFimDaConsulta());
			events.add(e);
		}
		return events;
	}
}
