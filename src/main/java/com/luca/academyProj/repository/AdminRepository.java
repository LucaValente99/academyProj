package com.luca.academyProj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luca.academyProj.businesscomponent.model.Amministratore;

@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<Amministratore, Long>{
	@Query(value="Select * from Amministratore where username = ?1", nativeQuery = true)
	Optional<Amministratore> findByUsername(String username);
}
