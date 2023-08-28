package com.luca.academyProj.service;

import java.util.List;

import com.luca.academyProj.businesscomponent.model.CorsistaCorso;

public interface CorsistaCorsoService {
	List<CorsistaCorso> getAll();
	void saveCorsistaCorso(CorsistaCorso corsistaCorso);
}
