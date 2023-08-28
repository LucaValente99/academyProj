package com.luca.academyProj.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luca.academyProj.businesscomponent.model.Amministratore;
import com.luca.academyProj.repository.AdminRepository;
import com.luca.academyProj.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public Optional<Amministratore> findByUsername(String username) {
		return adminRepository.findByUsername(username);
	}

	@Override
	public Optional<Amministratore> findById(long id) {
		return adminRepository.findById(id);
	}

}
