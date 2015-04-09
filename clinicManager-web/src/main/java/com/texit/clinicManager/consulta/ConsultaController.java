package com.texit.clinicManager.consulta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.texit.clinicManager.support.web.MessageHelper;
import com.texit.clinicManager.utils.BaseController;
import com.texti.clinicManager.core.service.IConsultaService;
import com.texti.clinicManager.core.service.IPatientService;
import com.texti.clinicManager.model.Consulta;
import com.texti.clinicManager.model.Patient;

@Controller
@Configuration
@RequestMapping(value = "consulta")
public class ConsultaController extends BaseController {
	
	private static String LIST_ALL = "consulta/list";
	private static String CREATE = "consulta/create";
	private static String EDIT = "consulta/edit";
	
	@Autowired
	private IConsultaService consultaService;
	
	@Autowired 
	private IPatientService patientService;

	@RequestMapping(value = "list")
	public String listAllPatients(Model model, HttpServletRequest request) {
		List<Consulta> consultas = this.consultaService.findAllByAccountId(super.getLoggedUserAccount(request).getId());
		model.addAttribute("consultasList", consultas);
		return LIST_ALL;
	}
	
	@RequestMapping(value = "create")
	public String create(Model model, HttpServletRequest request) {
		List<Patient> patientList = this.patientService.FindByAccountId(super.getLoggedUserAccount(request).getId());
		model.addAttribute("patientList", patientList);
		model.addAttribute(new ConsultaForm());
		return CREATE;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String createConsulta(@Valid @ModelAttribute ConsultaForm consultaForm, Errors errors, RedirectAttributes ra, HttpServletRequest request) {
		if (errors.hasErrors()) {
			return CREATE;
		}

		consultaService.createConsulta(consultaForm.createConsulta(super.getLoggedUserAccount(request)));

		// see /WEB-INF/i18n/messages.properties and
		MessageHelper.addSuccessAttribute(ra, "msg.createConsulta.success");
		return "redirect:/" + LIST_ALL;
	}
	
	@RequestMapping(value="edit")
	public String edit(@RequestParam Long id, HttpServletRequest request, Model model){
		Consulta _c = this.consultaService.findById(id);
		List<Patient> patientList = this.patientService.FindByAccountId(super.getLoggedUserAccount(request).getId());
		model.addAttribute("patientList", patientList);
		model.addAttribute(new ConsultaForm(_c));
		return EDIT;
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String updateConsulta(@Valid @ModelAttribute ConsultaForm consultaForm, Errors errors, RedirectAttributes ra, HttpServletRequest request) {
		if (errors.hasErrors()) {
			return EDIT;
		}

		consultaService.updateConsulta(consultaForm.createConsulta(super.getLoggedUserAccount(request)));

		// see /WEB-INF/i18n/messages.properties and
		MessageHelper.addSuccessAttribute(ra, "msg.updateData.success");
		return "redirect:/" + LIST_ALL;
	}
	
	@RequestMapping(value="delete")
	public String delete(@RequestParam Long id, RedirectAttributes ra){
		this.consultaService.deleteConsulta(new Consulta(id, null, null, null));
		
		// see /WEB-INF/i18n/messages.properties and
		MessageHelper.addSuccessAttribute(ra, "msg.updateData.success");
		return "redirect:/" + LIST_ALL;
	}
}
