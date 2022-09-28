package com.godoy.dashdados.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.godoy.dashdados.domain.model.Imobiliaria;

@Repository
public interface ImobiliariaRepository extends JpaRepository<Imobiliaria, Long>{
	
	Imobiliaria findByEmail(String email);

}
