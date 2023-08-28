package com.luca.academyProj.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
public class LeafConfig {

	/*
	 * Mi permette di controllare i criteri di validazione 
	 * impostati nel file di conf di spring security direttamente dalla pagina html
	 * 
	 * Questo funziona insieme alla dipendenza thymeleaf-extras-springsecurity5 inserita nel pom
	 */
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
}
