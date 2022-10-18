package com.godoy.dashdados.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godoy.dashdados.api.DTO.assembler.PipelineInputDisassembler;
import com.godoy.dashdados.api.DTO.assembler.PipelineModelAssembler;
import com.godoy.dashdados.api.DTO.input.PipelineInputModel;
import com.godoy.dashdados.api.DTO.model.PipelineModel;
import com.godoy.dashdados.domain.exception.BadRequestException;
import com.godoy.dashdados.domain.exception.ConflictException;
import com.godoy.dashdados.domain.exception.NegocioException;
import com.godoy.dashdados.domain.exception.PipelineNaoEncontradaException;
import com.godoy.dashdados.domain.model.Imobiliaria;
import com.godoy.dashdados.domain.model.Pipeline;
import com.godoy.dashdados.domain.model.TiposPipelines;
import com.godoy.dashdados.domain.repository.PipelineRepository;

@Service
public class PipelineService {
	
	@Autowired
	private PipelineRepository pipelineRepository;
	
	@Autowired
	private PipelineModelAssembler pipelineModelAssembler;
	
	@Autowired
	private PipelineInputDisassembler pipelineInputDisassembler;
	
	@Autowired
	@org.springframework.context.annotation.Lazy
	private ImobiliariaService imobiliariaService;
	
	public Pipeline buscarOuFalhar(Long pipelineId) {
		return pipelineRepository.findById(pipelineId).orElseThrow(() -> new NegocioException("Pipeline nao encontrado"));
	}
	
	public List<PipelineModel> listar() {
		List<Pipeline> lista = pipelineRepository.findAll();
		return pipelineModelAssembler.toCollectionModel(lista);
	}
	
	public PipelineModel detalhes(Long pipelineId) {
		Pipeline pipe = buscarOuFalhar(pipelineId);
		return pipelineModelAssembler.toModel(pipe);
	}
	
	@Transactional
	public PipelineModel salvar(PipelineInputModel pipelineInputModel) {
		
		Imobiliaria imobiliaria = imobiliariaService.buscarOuFalhar(pipelineInputModel.getImobiliaria());
		
		try {
			TiposPipelines.valueOf(TiposPipelines.class, pipelineInputModel.getTipo());
		} catch (IllegalArgumentException e) {
			throw new BadRequestException(
					String.format("%s não é um tipo de Pipeline compatível, seguem as possiblidades: LEADS," + 
							" CHAMADOS," + 
							" MANUTENCAO," + 
							" LOCACAO," + 
							" VENDA", pipelineInputModel.getTipo().toString()));
		}
		
		List<String> listPipes = new ArrayList<String>();
		imobiliaria.getPipe().forEach(item -> listPipes.add(item.getTipo().toString()));
		
		if (listPipes.contains(pipelineInputModel.getTipo())) {
			throw new ConflictException("Esse tipo de pipeline já foi adicionado a esta imobiliária");
		}
		
		Pipeline pipelineObj = pipelineInputDisassembler.toDomainObject(pipelineInputModel);
		pipelineObj.setImobiliaria(imobiliaria);
		pipelineObj = pipelineRepository.save(pipelineObj);
		
		return pipelineModelAssembler.toModel(pipelineObj);
		
	}
	
	@Transactional
	public void excluir(Long pipelineId) {
		try {
			pipelineRepository.deleteById(pipelineId);
			pipelineRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PipelineNaoEncontradaException(pipelineId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException("Pipeline em uso, não é possível realizar sua exclusão.");
		}
	}

}
