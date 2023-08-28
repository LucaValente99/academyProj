package com.luca.academyProj.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.luca.academyProj.businesscomponent.model.Amministratore;
import com.luca.academyProj.repository.AdminRepository;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
private AdminRepository adminRepository;
	
	public CustomAuthenticationSuccessHandler(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	 @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("admin".equals(cookie.getName())) {
                    Long adminId = Long.parseLong(cookie.getValue());
                    Amministratore admin = adminRepository.findById(adminId).orElse(null);
                    if (admin != null) {
                    	 Collection<GrantedAuthority> authorities = new ArrayList<>();
                         authorities.add(new SimpleGrantedAuthority("ADMIN")); // Aggiungi i ruoli dell'utente qui

                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin, null, authorities));
                    }
                }
            }
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
