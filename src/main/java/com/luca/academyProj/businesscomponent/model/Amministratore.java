package com.luca.academyProj.businesscomponent.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Amministratore implements Serializable{
	private static final long serialVersionUID = 4567219798599828677L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codAdmin;
	
	@Column(nullable = false)
	private String password;
	
	@Column(unique = true)
	private String username;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String role = "ADMIN";
	
	@Column(nullable = false)
	private boolean enabled = true;

	public long getCodAdmin() {
		return codAdmin;
	}

	public void setCodAdmin(long codAdmin) {
		this.codAdmin = codAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


}
