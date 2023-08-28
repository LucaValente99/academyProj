package com.luca.academyProj.businesscomponent.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Corsista implements Serializable{
	private static final long serialVersionUID = -1677833800036304353L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codCorsista;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String precedentiFormativi;
	
	@OneToMany(mappedBy = "corsista", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<CorsistaCorso> cc = new HashSet<CorsistaCorso>();

	public long getCodCorsista() {
		return codCorsista;
	}

	public void setCodCorsista(long codCorsista) {
		this.codCorsista = codCorsista;
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
	
	public Set<CorsistaCorso> getCc() {
		return cc;
	}

	public void setCc(Set<CorsistaCorso> cc) {
		this.cc = cc;
	}
	
	public String getPrecedentiFormativi() {
		return precedentiFormativi;
	}

	public void setPrecedentiFormativi(String precedentiFormativi) {
		this.precedentiFormativi = precedentiFormativi;
	}

	@Override
	public String toString() {
		return "Corsista [codCorsista=" + codCorsista + ", cognome=" + cognome + ", nome=" + nome
				+ ", precedentiFormativi=" + precedentiFormativi + ", cc=" + cc + "]";
	}
}
