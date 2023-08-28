package com.luca.academyProj.service;

import java.util.Optional;

import com.luca.academyProj.businesscomponent.model.Amministratore;

public interface AdminService {
	Optional<Amministratore> findByUsername(String username);
	Optional<Amministratore> findById(long id);
}
