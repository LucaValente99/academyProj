package com.luca.academyProj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luca.academyProj.businesscomponent.model.Corso;
import com.luca.academyProj.repository.CorsoRepository;
import com.luca.academyProj.service.CorsoService;

@Service
public class CorsoServiceImpl implements CorsoService{

	@Autowired
	CorsoRepository corsoRepository;
	
	@Override
	public void saveCorso(Corso corso) {
		corsoRepository.save(corso);
	}
	
	@Override
	public void deleteCorso(Corso corso) {
		corsoRepository.delete(corso);
	}

	@Override
	public Optional<Corso> findByName(String nomeCorso) {
		return corsoRepository.findByName(nomeCorso);
	}

	@Override
	public List<Corso> corsoPiuFrequentato() {
		return corsoRepository.corsoPiuFrequentato();
	}

	@Override
	public List<Corso> findDataInizioUltimoCorso() {
		return corsoRepository.findDataInizioUltimoCorso();
	}

	@Override
	public double findDurataMediaCorsi() {
		return corsoRepository.findDurataMediaCorsi();
	}

	@Override
	public long findNumeroCommenti() {
		return corsoRepository.findNumeroCommenti();
	}

	@Override
	public List<Corso> findCorsiDisponibili() {
		return corsoRepository.findCorsiDisponibili();
	}

	@Override
	public List<Corso> findByCorsistaId(long id) {
		return corsoRepository.findByCorsistaId(id);
	}

	@Override
	public List<Corso> findCorsiFuturi() {
		return corsoRepository.findCorsiFuturi();
	}

	@Override
	public Optional<Corso> findById(long id) {
		return corsoRepository.findById(id);
	}

	@Override
	public void deleteCorsoById(long id) {
		corsoRepository.deleteCorsoById(id);
	}

}
