package com.luca.academyProj.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.academyProj.businesscomponent.model.Docente;
import com.luca.academyProj.service.DocenteService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="")
public class DocenteRestController {
	@Autowired
	DocenteService docenteService;
	
	@GetMapping("/docenteConPiuDiUnCorso")
	public List<Docente> getDocentiConPiuDiUnCorso(){
		return docenteService.findDocenteCheInsergnaPiuDiUnCorso();
	}
}
