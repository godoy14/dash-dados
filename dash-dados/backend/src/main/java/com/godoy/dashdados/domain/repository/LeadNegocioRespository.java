package com.godoy.dashdados.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.godoy.dashdados.domain.model.LeadNegocio;

@Repository
public interface LeadNegocioRespository extends JpaRepository<LeadNegocio, Long>{
	
	List<LeadNegocio> findByPipelineId (Long pipelineId);
	
	List<LeadNegocio> findByImobiliariaId (Long imobiliariaId);
	
	LeadNegocio findByIdBitrix (Long idBitrix);
	
//	@Query("select *  negocio n WHERE n.id_bitrix = :idBitrix and n.imobiliaria_id = :imobiliariaId and n.dtype='LeadNegocio'")
	@Query("from LeadNegocio where idBitrix = :idBitrix and imobiliaria.id = :imobiliariaId")
	LeadNegocio consultarPorIdBitrixImobiliariaId(Long idBitrix, Long imobiliariaId);
	
}
