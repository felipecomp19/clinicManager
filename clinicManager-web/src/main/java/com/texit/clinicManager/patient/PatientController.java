package com.texit.clinicManager.patient;

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
import com.texti.clinicManager.core.service.IPatientService;
import com.texti.clinicManager.model.Patient;

@Controller
@Configuration
@RequestMapping(value = "patient")
public class PatientController extends BaseController {

	private static String LIST_ALL = "patient/list";
	private static String CREATE = "patient/create";
	private static String EDIT = "patient/edit";

	@Autowired
	private IPatientService patientService;

	@RequestMapping(value = "list")
	public String listAllPatients(Model model, HttpServletRequest request) {
		List<Patient> patients = this.patientService.FindByAccountId(super.getLoggedUserAccount(request).getId());
		model.addAttribute("patientsList", patients);
		return LIST_ALL;
	}

	@RequestMapping(value = "create")
	public String create(Model model) {
		model.addAttribute(new PatientForm());
		return CREATE;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String createPatient(@Valid @ModelAttribute PatientForm patientForm,
			Errors errors, RedirectAttributes ra, HttpServletRequest request) {
		if (errors.hasErrors()) {
			return CREATE;
		}

		patientService.createPatient(patientForm.createPatient(super.getLoggedUserAccount(request)));

		// see /WEB-INF/i18n/messages.properties and
		MessageHelper.addSuccessAttribute(ra, "msg.createPatient.success");
		return "redirect:/" + LIST_ALL;
	}
	
	@RequestMapping(value="edit")
	public String edit(@RequestParam Long id, HttpServletRequest request, Model model){
		Patient _p = this.patientService.findById(id);
		model.addAttribute(new PatientForm(_p));
		return EDIT;
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String updatePatient(@Valid @ModelAttribute PatientForm patientForm, Errors errors, RedirectAttributes ra, HttpServletRequest request) {
		if (errors.hasErrors()) {
			return EDIT;
		}

		patientService.updatePatient(patientForm.createPatient(super.getLoggedUserAccount(request)));

		// see /WEB-INF/i18n/messages.properties and
		MessageHelper.addSuccessAttribute(ra, "msg.updateData.success");
		return "redirect:/" + LIST_ALL;
	}
	
	@RequestMapping(value="delete")
	public String delete(@RequestParam Long id, RedirectAttributes ra){
		this.patientService.deletePatient(new Patient(id, null, null));
		
		// see /WEB-INF/i18n/messages.properties and
		MessageHelper.addSuccessAttribute(ra, "msg.updateData.success");
		return "redirect:/" + LIST_ALL;
	}
}
