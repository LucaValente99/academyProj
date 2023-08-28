package com.luca.academyProj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luca.academyProj.businesscomponent.model.CorsistaCorso;
import com.luca.academyProj.repository.CorsistaCorsoRepository;
import com.luca.academyProj.service.CorsistaCorsoService;

@Service
public class CorsistaCorsoServiceImpl implements CorsistaCorsoService{

	@Autowired
	CorsistaCorsoRepository corsistaCorsoRepository;
	
	@Override
	public List<CorsistaCorso> getAll() {
		return corsistaCorsoRepository.findAll();
	}

	@Override
	public void saveCorsistaCorso(CorsistaCorso corsistaCorso) {
		corsistaCorsoRepository.save(corsistaCorso);
	}
}
