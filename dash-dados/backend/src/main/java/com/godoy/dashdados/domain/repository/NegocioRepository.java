package com.godoy.dashdados.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.godoy.dashdados.domain.model.Negocio;

@Repository
public interface NegocioRepository extends JpaRepository<Negocio, Long>{

}
