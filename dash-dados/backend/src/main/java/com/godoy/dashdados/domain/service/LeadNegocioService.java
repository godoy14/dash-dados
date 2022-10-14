package com.godoy.dashdados.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godoy.dashdados.api.DTO.assembler.LeadNegocioInputDisassembler;
import com.godoy.dashdados.api.DTO.assembler.LeadNegocioModelAssembler;
import com.godoy.dashdados.api.DTO.model.LeadNegocioModel;
import com.godoy.dashdados.domain.exception.NegocioException;
import com.godoy.dashdados.domain.model.LeadNegocio;
import com.godoy.dashdados.domain.repository.LeadNegocioRespository;

@Service
public class LeadNegocioService {
	
	@Autowired
	private LeadNegocioRespository leadNegocioRespository;
	
	@Autowired
	private LeadNegocioInputDisassembler leadNegocioInputDisassembler;
	
	@Autowired
	private LeadNegocioModelAssembler leadNegocioModelAssembler;
	
	public LeadNegocio buscarOuFalhar(Long leadId) {
		return leadNegocioRespository.findById(leadId).orElseThrow(() -> new NegocioException("Lead não encontrado."));
	}
	
	public List<LeadNegocioModel> listar(){
		return leadNegocioModelAssembler.toCollectionModel(leadNegocioRespository.findAll());
	}
	
	public List<LeadNegocioModel> listarPorPipeline(Long pipelineId) {
		return leadNegocioModelAssembler.toCollectionModel(leadNegocioRespository.findByPipelineId(pipelineId));
	}

}
