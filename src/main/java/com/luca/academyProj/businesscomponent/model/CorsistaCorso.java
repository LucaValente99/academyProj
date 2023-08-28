package com.luca.academyProj.businesscomponent.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class CorsistaCorso implements Serializable{
	private static final long serialVersionUID = 757322297765182403L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCorsistaCorso;
	
	@ManyToOne
	@JoinColumn(name = "id_corso", nullable = false) 
	private Corso corso;
	
	@ManyToOne
	@JoinColumn(name = "id_corsista", nullable = false)
	private Corsista corsista;

	public long getIdCorsistaCorso() {
		return idCorsistaCorso;
	}

	public void setIdCorsistaCorso(long idCorsistaCorso) {
		this.idCorsistaCorso = idCorsistaCorso;
	}

	public Corso getCorso() {
		return corso;
	}

	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	public Corsista getCorsista() {
		return corsista;
	}

	public void setCorsista(Corsista corsista) {
		this.corsista = corsista;
	}

	@Override
	public String toString() {
		return "CorsistaCorso [idCorsistaCorso=" + idCorsistaCorso + ", corso=" + corso + ", corsista=" + corsista
				+ "]";
	}
}
