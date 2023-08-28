package com.luca.academyProj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luca.academyProj.businesscomponent.model.Corso;

@Repository("corsoRepository")
public interface CorsoRepository extends JpaRepository<Corso, Long>{
	/*
	@Query(value="select distinct(nome_corso) from corso c, CorsistaCorso cc"
			+ "where c.cod_corso = cc.id_corso"
			+ "group by c.nome"
			+ "order by count(cc.id_corso) desc"
			+ "limit 1", nativeQuery=true)
	*/
	@Query(value="select * "
			+ "from corso c "
			+ "where not exists ( "
			+ "select cc.id_corso "
			+ "from corsista_corso cc "
			+ "where c.cod_corso <> cc.id_corso "
			+ "group by cc.id_corso "
			+ "having count(cc.id_corso) > ( "
			+ "select count(cc2.id_corso) "
			+ "from corsista_corso cc2 "
			+ "where cc2.id_corso = c.cod_corso))", nativeQuery = true)
	List<Corso> corsoPiuFrequentato();
	
	@Query(value="select * from corso where nome_corso = ?1", nativeQuery = true)
	Optional<Corso> findByName(String nomeCorso);
	
	@Query(value="select * from corso c "
			+ "where not exists("
			+ "select * from corso co "
			+ "where co.data_inizio_corso > c.data_inizio_corso)", nativeQuery = true)
	List<Corso> findDataInizioUltimoCorso();	
	
	//la funzione coalesce mi permette di restituire 0 anziche null dal momento in cui non vi fossero corsi in tabella
	@Query(value = "select coalesce(avg(datediff(data_fine_corso, data_inizio_corso)), 0) as durata_media_in_giorni "
			+ "from corso", nativeQuery = true)
	double findDurataMediaCorsi();
	
	@Query(value = "select count(*) from corso "
			+ "where commento_corso NOT LIKE ''", nativeQuery = true)
	long findNumeroCommenti();
	
	@Query(value = "select * from corso "
			+ "where posti_disponibili > 0", nativeQuery = true)
	List<Corso> findCorsiDisponibili();
	
	@Query(value = "select * from corso c "
			+ "where c.cod_corso IN ( "
			+ "select co.id_corso from corsista_corso co "
			+ "where co.id_corsista = ?1)", nativeQuery = true)
	List<Corso> findByCorsistaId(long id);
	
	@Query(value = "select * from corso where data_inizio_corso > curdate()", nativeQuery = true)
	List<Corso> findCorsiFuturi();
	
	@Query(value = "delete from corso where cod_corso = ?1", nativeQuery = true)
	void deleteCorsoById(long id);
}
