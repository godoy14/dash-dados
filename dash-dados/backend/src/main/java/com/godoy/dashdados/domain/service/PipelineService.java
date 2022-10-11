package com.godoy.dashdados.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godoy.dashdados.api.DTO.assembler.PipelineInputDisassembler;
import com.godoy.dashdados.api.DTO.assembler.PipelineModelAssembler;
import com.godoy.dashdados.api.DTO.input.PipelineInputModel;
import com.godoy.dashdados.api.DTO.model.PipelineModel;
import com.godoy.dashdados.domain.exception.NegocioException;
import com.godoy.dashdados.domain.model.Imobiliaria;
import com.godoy.dashdados.domain.model.Pipeline;
import com.godoy.dashdados.domain.model.TiposPipelines;
import com.godoy.dashdados.domain.repository.ImobiliariaRepository;
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
	
	@Autowired
	private ImobiliariaRepository imobiliariaRepository;
	
	public List<PipelineModel> listar() {
		List<Pipeline> lista = pipelineRepository.findAll();
		return pipelineModelAssembler.toCollectionModel(lista);
	}
	
//	public PipelineModel salvar(PipelineInputModel pipelineInputModel) {
//		Pipeline pipelineObj = pipelineInputDisassembler.toDomainObject(pipelineInputModel);
//		pipelineObj.setImobiliaria(imobiliariaService.buscarOuFalhar(pipelineInputModel.getImobiliaria()));
//		pipelineObj = pipelineRepository.save(pipelineObj);
//		
//		return pipelineModelAssembler.toModel(pipelineObj);
//	}
	
	public PipelineModel salvar(PipelineInputModel pipelineInputModel) {
		
		Imobiliaria imobiliaria = imobiliariaService.buscarOuFalhar(pipelineInputModel.getImobiliaria());

		if (TiposPipelines.valueOf(TiposPipelines.class, pipelineInputModel.getTipo()).equals(null)) {
			throw new NegocioException("Não existe esse tipo de Pipeline!");
		}
		
		List<String> listPipes = new ArrayList<>();
		imobiliaria.getPipe().forEach(item -> listPipes.add(item.getTipo().toString()));
		
		if (listPipes.contains(pipelineInputModel.getTipo())) {
			throw new NegocioException("Esse tipo de pipeline já foi adicionado a esta imobiliária");
		}
		
		Pipeline pipelineObj = pipelineInputDisassembler.toDomainObject(pipelineInputModel);
		pipelineObj.setImobiliaria(imobiliaria);
		pipelineObj = pipelineRepository.save(pipelineObj);
				
		imobiliaria.getPipe().add(pipelineObj);
		
		imobiliariaRepository.save(imobiliaria);
		
		return pipelineModelAssembler.toModel(pipelineObj);
		
	}

}
