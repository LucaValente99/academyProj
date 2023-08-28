package com.luca.academyProj.security;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.luca.academyProj.repository.AdminRepository;

@Configuration
public class SecurityConf {

	private AdminRepository adminRepository;

	public SecurityConf(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.antMatchers("/")
		.permitAll()
		.antMatchers("/admin/**")
		.hasRole("ADMIN")
		.and()
		.formLogin()
		.loginPage("/admin/loginAdmin")
		.permitAll()
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logoutAdmin"))
		.logoutSuccessUrl("/admin/");

		return http.build();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(new AdminDetailsService(adminRepository));
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
    CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("failedLoginAttempts"); // Nome della cache
    }
}
