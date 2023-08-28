package com.luca.academyProj.businesscomponent.model;

import java.io.Serializable;
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
public class Docente implements Serializable{
	private static final long serialVersionUID = -8391110170943536353L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codDocente;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false)
	private String nome;
	
	@OneToMany(mappedBy = "docente", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Corso> corsi;

	public long getCodDocente() {
		return codDocente;
	}

	public void setCodDocente(long codDocente) {
		this.codDocente = codDocente;
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

	public Set<Corso> getCorsi() {
		return corsi;
	}

	public void setCorsi(Set<Corso> corsi) {
		this.corsi = corsi;
	}

	@Override
	public String toString() {
		return "Docente [codDocente=" + codDocente + ", cognome=" + cognome + ", nome=" + nome + ", corsi=" + corsi
				+ "]";
	}
	
	
}
