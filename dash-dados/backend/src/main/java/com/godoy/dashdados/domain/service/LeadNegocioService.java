package com.godoy.dashdados.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godoy.dashdados.api.DTO.assembler.LeadNegocioInputDisassembler;
import com.godoy.dashdados.api.DTO.assembler.LeadNegocioModelAssembler;
import com.godoy.dashdados.api.DTO.input.LeadNegocioInputModel;
import com.godoy.dashdados.api.DTO.model.LeadNegocioModel;
import com.godoy.dashdados.core.validation.EntitiesValidationObj;
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
		
//		Imobiliaria imobiliaria = imobiliariaService.buscarOuFalhar(leadNegocioInputModel.getImobiliariaCod());
//		Pipeline pipeline = pipelineService.buscarOuFalhar(leadNegocioInputModel.getPipelineCod());
//		ResponsavelNegocio responsavel = responsavelService.buscarOuFalhar(leadNegocioInputModel.getResponsavelCod());
//		
//		try {
//			FonteNegocio.valueOf(FonteNegocio.class, leadNegocioInputModel.getFonte());
//		} catch (IllegalArgumentException e) {
//			throw new BadRequestException(
//					String.format("%s não é uma Fonte compatível." + 
//							" Favor verificar a listagem de Fontes no endpoint /negocios/leads/listaFonte,"
//							, leadNegocioInputModel.getFonte().toString()));
//		}
//		
//		try {
//			StatusNegocio.valueOf(StatusNegocio.class, leadNegocioInputModel.getStatus());
//		} catch (IllegalArgumentException e) {
//			throw new BadRequestException(
//					String.format("%s não é um Status compatível." + 
//							" Favor verificar a listagem de Status no endpoint /negocios/leads/listaStatus,"
//							, leadNegocioInputModel.getStatus().toString()));
//		}
//		
//		try {
//			TiposNegocios.valueOf(TiposNegocios.class, leadNegocioInputModel.getTipo());
//		} catch (IllegalArgumentException e) {
//			throw new BadRequestException(
//					String.format("%s não é um Tipo de Lead compatível." + 
//							" Favor verificar a listagem de Tipos no endpoint /negocios/leads/listaTiposLead,"
//							, leadNegocioInputModel.getTipo()).toString());
//		}
		
		EntitiesValidationObj obj = inputValidation(leadNegocioInputModel);
		
		LeadNegocio existentLead = leadNegocioRespository.findByIdBitrix(leadNegocioInputModel.getIdBitrix());
		if (existentLead != null) {
			throw new NegocioException("Esse negócio Bitrix já foi adicionado");
		}
		
		LeadNegocio leadNegocioObj = leadNegocioInputDisassembler.toDomainObject(leadNegocioInputModel);
		leadNegocioObj.setImobiliaria(obj.getImobiliria());
		leadNegocioObj.setPipeline(obj.getPipeline());
		leadNegocioObj.setResponsavel(obj.getResponsavel());
		
		leadNegocioObj = leadNegocioRespository.save(leadNegocioObj);
		
		return leadNegocioModelAssembler.toModel(leadNegocioObj);
	}
	
	@Transactional
	public LeadNegocioModel atualizar(Long leadNegocioId, LeadNegocioInputModel leadNegocioInputModel) {
		
		LeadNegocio leadAtual = buscarOuFalhar(leadNegocioId);
		
		BeanUtils.copyProperties(leadNegocioInputModel, leadAtual, "id");
		
		inputValidation(leadNegocioInputModel);
		
		leadAtual.setStatus(StatusNegocio.valueOf(leadNegocioInputModel.getStatus()));
		
		leadAtual= leadNegocioRespository.save(leadAtual);
		
		LeadNegocioModel leadAtualModel = leadNegocioModelAssembler.toModel(leadAtual);
		
		// LeadNegocioModel leadNegocioSalvo = salvar(leadNegocioModelAssembler.toInputModel(leadAtualModel));
		
		return leadAtualModel;
	}
	
	public EntitiesValidationObj inputValidation(LeadNegocioInputModel leadNegocioInputModel) {
		
		EntitiesValidationObj obj = new EntitiesValidationObj();
		
		Imobiliaria imobiliaria = imobiliariaService.buscarOuFalhar(leadNegocioInputModel.getImobiliariaCod());
		Pipeline pipeline = pipelineService.buscarOuFalhar(leadNegocioInputModel.getPipelineCod());
		ResponsavelNegocio responsavel = responsavelService.buscarOuFalhar(leadNegocioInputModel.getResponsavelCod());
		
		obj.setImobiliria(imobiliaria);
		obj.setPipeline(pipeline);
		obj.setResponsavel(responsavel);
		
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
		
		return obj;
		
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
