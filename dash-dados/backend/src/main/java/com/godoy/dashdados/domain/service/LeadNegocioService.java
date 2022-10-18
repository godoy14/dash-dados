package com.godoy.dashdados.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godoy.dashdados.api.DTO.assembler.LeadNegocioInputDisassembler;
import com.godoy.dashdados.api.DTO.assembler.LeadNegocioModelAssembler;
import com.godoy.dashdados.api.DTO.input.LeadNegocioInputModel;
import com.godoy.dashdados.api.DTO.model.LeadNegocioModel;
import com.godoy.dashdados.domain.exception.BadRequestException;
import com.godoy.dashdados.domain.exception.NegocioException;
import com.godoy.dashdados.domain.exception.NegocioNaoEncontradaException;
import com.godoy.dashdados.domain.model.FonteNegocio;
import com.godoy.dashdados.domain.model.Imobiliaria;
import com.godoy.dashdados.domain.model.LeadNegocio;
import com.godoy.dashdados.domain.model.Pipeline;
import com.godoy.dashdados.domain.model.ResponsavelNegocio;
import com.godoy.dashdados.domain.model.StatusNegocio;
import com.godoy.dashdados.domain.model.TiposNegocios;
import com.godoy.dashdados.domain.repository.LeadNegocioRespository;

@Service
public class LeadNegocioService {
	
	@Autowired
	private LeadNegocioRespository leadNegocioRespository;
	
	@Autowired
	private ImobiliariaService imobiliariaService;
	
	@Autowired
	private PipelineService pipelineService;
	
	@Autowired
	private ResponsavelNegocioService responsavelService;
	
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
	
	@Transactional
	public LeadNegocioModel salvar(LeadNegocioInputModel leadNegocioInputModel) {
		
		Imobiliaria imobiliaria = imobiliariaService.buscarOuFalhar(leadNegocioInputModel.getImobiliariaCod());
		Pipeline pipeline = pipelineService.buscarOuFalhar(leadNegocioInputModel.getPipelineCod());
		ResponsavelNegocio responsavel = responsavelService.buscarOuFalhar(leadNegocioInputModel.getResponsavelCod());
		
		try {
			FonteNegocio.valueOf(FonteNegocio.class, leadNegocioInputModel.getFonte());
		} catch (IllegalArgumentException e) {
			throw new BadRequestException(
					String.format("%s não é uma Fonte compatível." + 
							" Favor verificar a listagem de Fontes no endpoint /negocios/leads/listaFonte,"
							, leadNegocioInputModel.getFonte().toString()));
		}
		
		try {
			StatusNegocio.valueOf(StatusNegocio.class, leadNegocioInputModel.getStatus());
		} catch (IllegalArgumentException e) {
			throw new BadRequestException(
					String.format("%s não é um Status compatível." + 
							" Favor verificar a listagem de Status no endpoint /negocios/leads/listaStatus,"
							, leadNegocioInputModel.getStatus().toString()));
		}
		
		try {
			TiposNegocios.valueOf(TiposNegocios.class, leadNegocioInputModel.getTipo());
		} catch (IllegalArgumentException e) {
			throw new BadRequestException(
					String.format("%s não é um Tipo de Lead compatível." + 
							" Favor verificar a listagem de Tipos no endpoint /negocios/leads/listaTiposLead,"
							, leadNegocioInputModel.getTipo()).toString());
		}
		
		LeadNegocio existentLead = leadNegocioRespository.findByIdBitrix(leadNegocioInputModel.getIdBitrix());
		if (existentLead != null) {
			throw new NegocioException("Esse negócio Bitrix já foi adicionado");
		}
		
		LeadNegocio leadNegocioObj = leadNegocioInputDisassembler.toDomainObject(leadNegocioInputModel);
		leadNegocioObj.setImobiliaria(imobiliaria);
		leadNegocioObj.setPipeline(pipeline);
		leadNegocioObj.setResponsavel(responsavel);
		
		leadNegocioObj = leadNegocioRespository.save(leadNegocioObj);
		
		return leadNegocioModelAssembler.toModel(leadNegocioObj);
	}
	
	@Transactional
	public void excluir(Long leadNegocioId) {
		try {
			leadNegocioRespository.deleteById(leadNegocioId);
			leadNegocioRespository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioNaoEncontradaException(leadNegocioId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException("Lead em uso, não é possível realizar sua exclusão.");
		}
	}

}
