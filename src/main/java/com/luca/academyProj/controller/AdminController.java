package com.luca.academyProj.controller;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luca.academyProj.businesscomponent.model.Amministratore;
import com.luca.academyProj.businesscomponent.model.Corsista;
import com.luca.academyProj.businesscomponent.model.CorsistaCorso;
import com.luca.academyProj.businesscomponent.model.Corso;
import com.luca.academyProj.businesscomponent.model.Docente;
import com.luca.academyProj.service.impl.AdminServiceImpl;
import com.luca.academyProj.service.impl.CorsistaCorsoServiceImpl;
import com.luca.academyProj.service.impl.CorsistaServiceImpl;
import com.luca.academyProj.service.impl.CorsoServiceImpl;
import com.luca.academyProj.service.impl.DocenteServiceImpl;

@Controller
@Scope("session")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	CorsistaCorsoServiceImpl corsistaCorsoService;
	
	@Autowired
	CorsistaServiceImpl corsistaService;
	
	@Autowired
	DocenteServiceImpl docenteService;
	
	@Autowired
	CorsoServiceImpl corsoService;
	
	@GetMapping(value= {"", "/"})
	public ModelAndView homePage(HttpSession session, Authentication auth, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("home");
		
		if(session.getAttribute("admin") == null) {
			System.out.println("PRIMO ACCESSO");
			Amministratore admin = (Amministratore) adminService.findByUsername(auth.getName()).get();
			session.setAttribute("admin", admin);
			Cookie adminCookie = new Cookie("admin", Long.toString(admin.getCodAdmin())); 
	        adminCookie.setMaxAge(3600); 
	        response.addCookie(adminCookie);
		} 	
		
		List<CorsistaCorso> cc = corsistaCorsoService.getAll();
		mv.addObject("admin_log", session.getAttribute("admin"));
		mv.addObject("corsistaCorsoList", cc);
		
		return mv;
	}
	
	@GetMapping("/loginAdmin")
	public ModelAndView loginAdmin(HttpSession session, Authentication auth, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("loginAdmin");
		//CONTROLLO SU SESSIONE E COOKIE
		if(session.getAttribute("admin") == null) {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if ("admin".equals(cookie.getName())) { 
						System.out.println("ECCOMI AL SECONDO ACCESSO");
	                    session.setAttribute("admin", adminService.findById(Long.parseLong((cookie.getValue()))).get());
	                    return new ModelAndView("redirect:/admin/");
	                }
				}
			} 
		}	
		return mv;
	}
	
	@GetMapping("/registraCorsista")
	public ModelAndView registraCorsista(HttpSession session) {
		ModelAndView mv = new ModelAndView("registraCorsista");
		mv.addObject("corsista", new Corsista());
		mv.addObject("admin_log", session.getAttribute("admin"));
		return mv;
	}
	
	@PostMapping("/inserimentoCorsista")
	public String inserimentoCorsista(HttpSession session, @RequestParam String nome, 
									  @RequestParam String cognome, @RequestParam String nomeCorso,
									  @RequestParam Date dataInizio, @RequestParam Date dataFine,
									  @RequestParam String commenti, @RequestParam String precedentiFormativi,
									  @RequestParam String aulaCorso, @RequestParam Long codDocente) {	
		
		Docente docente = null;
		if(docenteService.findById(codDocente).isPresent())
			 docente = docenteService.findById(codDocente).get();
		else
			return "redirect:/admin/registraCorsista/";
				
		Corso corso = null;
		/*
		 * Verifico che se il corso fosse già presente, corrispondano anche le date, altrimenti
		 * nonostante il nome comune si tratterebbe di un corso nuovo
		 */
		
		if(corsoService.findByName(nomeCorso).isPresent()) {
			Corso temp = corsoService.findByName(nomeCorso).get();
			if(temp.getDataInizioCorso().compareTo(dataInizio) == 0) {
				corso = temp;
			}
			
			/*
			 * se i posti disponibili sono > 0 procedo con l'iscrizione di un nuovo corsista
			 */
			if(corso.getPostiDisponibili() > 0)
				corso.setPostiDisponibili(corso.getPostiDisponibili()-1);
			else
				return "redirect:/admin/registraCorsista/";
		}
		else {
			corso = new Corso();
			corso.setNomeCorso(nomeCorso);
			corso.setDataInizioCorso(dataInizio);
			corso.setDataFineCorso(dataFine);
			corso.setAulaCorso(aulaCorso);
			corso.setCommentoCorso(commenti);
			corso.setDocente(docente); 
			corso.setPostiDisponibili(14);
			corsoService.saveCorso(corso);
		}
		
		Corsista corsista = new Corsista();
		corsista.setNome(nome);
		corsista.setCognome(cognome);
		corsista.setPrecedentiFormativi(precedentiFormativi);
		
		corsistaService.saveCorsista(corsista);
		
		CorsistaCorso corsistaCorso = new CorsistaCorso();
		corsistaCorso.setCorsista(corsista);
		corsistaCorso.setCorso(corso);
		
		corsistaCorsoService.saveCorsistaCorso(corsistaCorso);
		
		return "redirect:/admin/";
	}
	
	@GetMapping("/visualizzaStatistiche")
	public ModelAndView visualizzaStatistiche(HttpSession session) {
		ModelAndView mv = new ModelAndView("visualizzaStatistiche");
				
		mv.addObject("corsistiTotali", corsistaService.count());
		mv.addObject("corsoPiuFrequentato", corsoService.corsoPiuFrequentato());
		mv.addObject("dataUltimoCorso", corsoService.findDataInizioUltimoCorso());
		mv.addObject("durataMediaCorsi", new DecimalFormat("0.00").format(corsoService.findDurataMediaCorsi()));
		mv.addObject("numeroCommenti", corsoService.findNumeroCommenti());
		mv.addObject("docentiCheInsegnanoPiuDiUnCorso", docenteService.findDocenteCheInsergnaPiuDiUnCorso());		
		mv.addObject("corsiDisponibili", corsoService.findCorsiDisponibili());
		mv.addObject("admin_log", session.getAttribute("admin"));
				
		return mv;
		
	}
	
	@GetMapping("/infoCorsista/{id}")
	public ModelAndView infoCorsista(HttpSession session, @PathVariable long id) {
		Corsista corsista = corsistaService.findById(id).get();
		
		List<Corso> corsi = corsoService.findByCorsistaId(id);
				
		ModelAndView mv = new ModelAndView("/infoCorsista");
		mv.addObject("corsista", corsista);
		mv.addObject("corsi", corsi);
		mv.addObject("admin_log", session.getAttribute("admin"));

		return mv;
	}
	
	@GetMapping("/visualizzaCorsi")
	public ModelAndView visualizzaCorsi(HttpSession session) {		
		List<Corso> corsi = corsoService.findCorsiFuturi();
		ModelAndView mv = new ModelAndView("/visualizzaCorsi");
		mv.addObject("corsi", corsi);
		mv.addObject("admin_log", session.getAttribute("admin"));
		
		return mv;
	}
	
	@GetMapping("/eliminaCorso/{id}")
	public String eliminaCorso(@PathVariable long id) {
		Corso corso = corsoService.findById(id).get();
		corsoService.deleteCorso(corso);
		return "redirect:/admin/";
	}	
	
	@GetMapping("/iscriviCorsista")
	public ModelAndView iscriviCorsista(HttpSession session) {
		
		List<Corsista> corsisti = corsistaService.getAll();
		List<Corso> corsi = corsoService.findCorsiFuturi();
		
		ModelAndView mv = new ModelAndView("/iscriviCorsista");
		mv.addObject("corsisti", corsisti);
		mv.addObject("corsi", corsi);
		mv.addObject("admin_log", session.getAttribute("admin"));
		
		return mv;
	}
	
	@PostMapping("/iscriviCorsista")
	public String iscriviCorsistaACorso(@RequestParam long idCorsista, @RequestParam long idCorso) {
		
		if(corsistaService.findById(idCorsista).isPresent() && corsoService.findById(idCorso).isPresent()) {
			Corsista corsista = corsistaService.findById(idCorsista).get();
			Corso corso = corsoService.findById(idCorso).get();
			
			CorsistaCorso corsistaCorso = new CorsistaCorso();
			corsistaCorso.setCorsista(corsista);
			corsistaCorso.setCorso(corso);
			
			corsistaCorsoService.saveCorsistaCorso(corsistaCorso);
			return "redirect:/admin";
		}
		
		return "redirect:/admin/iscriviCorsista";		
	}
		
	@GetMapping("/logout")
	public ModelAndView logoutAdmin(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.invalidate();
				
		// RIMOZIONE COOCKIE
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            cookie.setMaxAge(0); //SCADENZA A 0
	            response.addCookie(cookie);
	        }
	    }
		return new ModelAndView("redirect:/admin/logoutAdmin");
	}
	
}
