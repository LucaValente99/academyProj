package com.luca.academyProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luca.academyProj.businesscomponent.model.Corsista;

@Repository("corsistaRepository")
public interface CorsistaRepository extends JpaRepository<Corsista, Long>{
	@Query(value="select count(*) from corsista", nativeQuery=true)
	long count();
}
