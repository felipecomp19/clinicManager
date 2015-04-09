package com.texit.clinicManager.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.texti.clinicManager.core.repository.AccountRepository;
import com.texti.clinicManager.model.Account;

public class BaseController {
	
	private static String ACCOUNT = "account";
	
	@Autowired
	AccountRepository accountRepository;
	
	public Account getLoggedUserAccount(HttpServletRequest request){
		Account ac = (Account) request.getSession().getAttribute(ACCOUNT);
		
		if(ac == null){
			String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
			
			ac = accountRepository.findByEmail(accountEmail);
			request.getSession().setAttribute(ACCOUNT, ac);
		}
		
		return ac;
	}
}
