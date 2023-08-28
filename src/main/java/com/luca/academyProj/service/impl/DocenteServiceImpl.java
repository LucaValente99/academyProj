package com.luca.academyProj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luca.academyProj.businesscomponent.model.Docente;
import com.luca.academyProj.repository.DocenteRepository;
import com.luca.academyProj.service.DocenteService;

@Service
public class DocenteServiceImpl implements DocenteService{

	@Autowired
	DocenteRepository docenteRepository;
	
	@Override
	public Optional<Docente> findById(long codDocente) {
		return docenteRepository.findById(codDocente);
	}

	@Override
	public List<Docente> findDocenteCheInsergnaPiuDiUnCorso() {
		return docenteRepository.findDocenteCheInsergnaPiuDiUnCorso();
	}
	
}
