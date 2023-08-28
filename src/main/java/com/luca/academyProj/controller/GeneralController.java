package com.luca.academyProj.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
		
	@GetMapping(value= {"", "/"})
	public String homePage(HttpSession session, Authentication auth) {
		return "redirect:/admin/";
	}
}
