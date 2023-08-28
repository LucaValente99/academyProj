package com.luca.academyProj.service;

import java.util.List;
import java.util.Optional;

import com.luca.academyProj.businesscomponent.model.Corso;

public interface CorsoService {
	void saveCorso(Corso corso);
	void deleteCorso(Corso corso);
	Optional<Corso> findByName(String nomeCorso);
	Optional<Corso> findById(long id);
	List<Corso> corsoPiuFrequentato();
	List<Corso> findDataInizioUltimoCorso();	
	double findDurataMediaCorsi();
	long findNumeroCommenti();
	List<Corso> findCorsiDisponibili();
	List<Corso> findByCorsistaId(long id);
	List<Corso> findCorsiFuturi();
	void deleteCorsoById(long id);
}
