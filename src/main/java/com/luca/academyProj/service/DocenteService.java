package com.luca.academyProj.service;

import java.util.List;
import java.util.Optional;

import com.luca.academyProj.businesscomponent.model.Docente;

public interface DocenteService {
	Optional<Docente> findById(long codDocente);
	List<Docente> findDocenteCheInsergnaPiuDiUnCorso();
}
