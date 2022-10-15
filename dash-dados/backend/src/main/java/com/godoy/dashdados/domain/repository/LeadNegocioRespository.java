package com.godoy.dashdados.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.godoy.dashdados.domain.model.LeadNegocio;

@Repository
public interface LeadNegocioRespository extends JpaRepository<LeadNegocio, Long>{
	
	List<LeadNegocio> findByPipelineId (Long pipelineId);
	
	LeadNegocio findByIdBitrix(Long idBitrix);
	
}
