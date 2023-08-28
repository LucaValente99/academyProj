package com.luca.academyProj.businesscomponent.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Corso implements Serializable{
	private static final long serialVersionUID = 8364421078796083472L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codCorso;
	
	@Column(nullable = false, unique=true)
	private String nomeCorso;
	
	@Column(nullable = false)
	private Date dataInizioCorso;
	
	@Column(nullable = false)
	private Date dataFineCorso;
	
	@Column(nullable = false)
	private int costoCorso;
	
	@Column(nullable = false)
	private String commentoCorso;
	
	@Column(nullable = false)
	private String aulaCorso;
	
	@Column
	private int postiDisponibili = 15;
	
	@OneToMany(mappedBy = "corso", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<CorsistaCorso> cc = new HashSet<CorsistaCorso>();
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_docente", nullable = false)
	private Docente docente;

	public long getCodCorso() {
		return codCorso;
	}

	public void setCodCorso(long codCorso) {
		this.codCorso = codCorso;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public Date getDataInizioCorso() {
		return dataInizioCorso;
	}

	public void setDataInizioCorso(Date dataInizioCorso) {
		this.dataInizioCorso = dataInizioCorso;
	}

	public Date getDataFineCorso() {
		return dataFineCorso;
	}

	public void setDataFineCorso(Date dataFineCorso) {
		this.dataFineCorso = dataFineCorso;
	}

	public int getCostoCorso() {
		return costoCorso;
	}

	public void setCostoCorso(int costoCorso) {
		this.costoCorso = costoCorso;
	}

	public String getCommentoCorso() {
		return commentoCorso;
	}

	public void setCommentoCorso(String commentoCorso) {
		this.commentoCorso = commentoCorso;
	}

	public String getAulaCorso() {
		return aulaCorso;
	}

	public void setAulaCorso(String aulaCorso) {
		this.aulaCorso = aulaCorso;
	}
	
	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Set<CorsistaCorso> getCc() {
		return cc;
	}

	public void setCc(Set<CorsistaCorso> cc) {
		this.cc = cc;
	}
	
	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}

	@Override
	public String toString() {
		return "Corso [codCorso=" + codCorso + ", nomeCorso=" + nomeCorso + ", dataInizioCorso=" + dataInizioCorso
				+ ", dataFineCorso=" + dataFineCorso + ", costoCorso=" + costoCorso + ", commentoCorso=" + commentoCorso
				+ ", aulaCorso=" + aulaCorso + ", postiDisponibili=" + postiDisponibili + ", cc=" + cc + ", docente="
				+ docente + "]";
	}	

}
