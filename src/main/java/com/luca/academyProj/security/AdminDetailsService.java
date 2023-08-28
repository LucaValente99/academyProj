package com.luca.academyProj.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.luca.academyProj.businesscomponent.model.Amministratore;
import com.luca.academyProj.repository.AdminRepository;

public class AdminDetailsService implements UserDetailsService {

	private AdminRepository adminRepository;
	
	public AdminDetailsService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Amministratore admin = adminRepository.findByUsername(username).isPresent() ? adminRepository.findByUsername(username).get() : null;
			if(admin != null) {
					
				return User.withUsername(admin.getUsername())
						.accountLocked(!admin.isEnabled())
						.password(admin.getPassword())
						.roles(admin.getRole())
						.build(); 
			}
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		
		throw new UsernameNotFoundException(username);
	}

}
