package com.luca.academyProj.service;

import java.util.List;
import java.util.Optional;

import com.luca.academyProj.businesscomponent.model.Corsista;

public interface CorsistaService {
	Optional<Corsista> findById(long id); 
	void saveCorsista(Corsista corsista);
	long count();
	List<Corsista> getAll();
}
