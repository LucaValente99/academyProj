package com.luca.academyProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luca.academyProj.businesscomponent.model.CorsistaCorso;

@Repository("corsistaCorsoRepository")
public interface CorsistaCorsoRepository extends JpaRepository<CorsistaCorso, Long>{
	
}
