package com.godoy.dashdados.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godoy.dashdados.api.DTO.assembler.PipelineInputDisassembler;
import com.godoy.dashdados.api.DTO.assembler.PipelineModelAssembler;
import com.godoy.dashdados.api.DTO.input.PipelineInputModel;
import com.godoy.dashdados.api.DTO.model.PipelineModel;
import com.godoy.dashdados.domain.model.Pipeline;
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
	private ImobiliariaService imobiliariaService;
	
	public List<PipelineModel> listar() {
		List<Pipeline> lista = pipelineRepository.findAll();
		return pipelineModelAssembler.toCollectionModel(lista);
	}
	
	public PipelineModel salvar(PipelineInputModel pipelineInputModel) {
		Pipeline pipelineObj = pipelineInputDisassembler.toDomainObject(pipelineInputModel);
		pipelineObj.setImobiliaria(imobiliariaService.buscarOuFalhar(pipelineInputModel.getImobiliaria()));
		pipelineObj = pipelineRepository.save(pipelineObj);
		
		return pipelineModelAssembler.toModel(pipelineObj);
	}

}
