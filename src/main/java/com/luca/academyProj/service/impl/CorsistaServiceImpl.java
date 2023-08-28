package com.luca.academyProj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luca.academyProj.businesscomponent.model.Corsista;
import com.luca.academyProj.repository.CorsistaRepository;
import com.luca.academyProj.service.CorsistaService;

@Service
public class CorsistaServiceImpl implements CorsistaService{

	@Autowired
	CorsistaRepository corsistaRepository;
	
	@Override
	public void saveCorsista(Corsista corsista) {
		corsistaRepository.save(corsista);
	}

	@Override
	public long count() {
		return corsistaRepository.count();
	}

	@Override
	public Optional<Corsista> findById(long id) {
		return corsistaRepository.findById(id);
	}

	@Override
	public List<Corsista> getAll() {
		return corsistaRepository.findAll();
	}

}
