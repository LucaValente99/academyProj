package com.luca.academyProj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luca.academyProj.businesscomponent.model.Docente;

@Repository("docenteRepository")
public interface DocenteRepository extends JpaRepository<Docente, Long>{
	@Query(value="select * from docente d "
			+ "where d.cod_docente IN (select c.id_docente from corso c, corso co "
			+ "where c.id_docente = co.id_docente AND c.nome_corso <> co.nome_corso)", nativeQuery = true)
	List<Docente> findDocenteCheInsergnaPiuDiUnCorso();
}
