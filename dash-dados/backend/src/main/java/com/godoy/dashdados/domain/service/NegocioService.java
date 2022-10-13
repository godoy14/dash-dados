package com.godoy.dashdados.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godoy.dashdados.api.DTO.assembler.NegocioInputDisassembler;
import com.godoy.dashdados.api.DTO.assembler.NegocioModelAssembler;
import com.godoy.dashdados.api.DTO.input.NegocioInputModel;
import com.godoy.dashdados.api.DTO.model.NegocioModel;
import com.godoy.dashdados.domain.exception.BadRequestException;
import com.godoy.dashdados.domain.exception.NegocioException;
import com.godoy.dashdados.domain.model.FonteNegocio;
import com.godoy.dashdados.domain.model.Imobiliaria;
import com.godoy.dashdados.domain.model.Negocio;
import com.godoy.dashdados.domain.model.Pipeline;
import com.godoy.dashdados.domain.model.StatusNegocio;
import com.godoy.dashdados.domain.repository.NegocioRepository;

@Service
public class NegocioService {
	
	@Autowired
	private NegocioRepository negocioRepository;
	
	@Autowired
	private ImobiliariaService imobiliariaService;
	
	@Autowired
	private PipelineService pipelineService;
	
	@Autowired
	private NegocioModelAssembler negocioModelAssembler;
	
	@Autowired
	private NegocioInputDisassembler negocioInputDisassembler;
	
	public List<NegocioModel> listar() {
		List<Negocio> lista = negocioRepository.findAll();
		return negocioModelAssembler.toCollectionModel(lista);
	}
	
	public Negocio buscarOuFalhar(Long negocioId) {
		return negocioRepository.findById(negocioId).orElseThrow(() -> new NegocioException("Negocio nao encontrado"));
	}
	
	public NegocioModel salvar(NegocioInputModel negocioInputModel) {
		Imobiliaria imobiliaria = imobiliariaService.buscarOuFalhar(negocioInputModel.getImobiliaria());
		Pipeline pipeline = pipelineService.buscarOuFalhar(negocioInputModel.getPipeline());
		
		try {
			StatusNegocio.valueOf(StatusNegocio.class, negocioInputModel.getStatus());
		} catch (IllegalArgumentException e) {
			throw new BadRequestException(
					String.format("%s não é um tipo de Status compatível, seguem as possiblidades: ABERTO," + 
							" GANHO," + 
							" DESISTIU", negocioInputModel.getStatus().toString()));
		}
		
		try {
			FonteNegocio.valueOf(FonteNegocio.class, negocioInputModel.getFonte());
		} catch (IllegalArgumentException e) {
			throw new BadRequestException(
					String.format("%s não é um tipo de Pipeline compatível, seguem as possiblidades: SITE," + 
							" PORTAL," + 
							" TELEFONE," + 
							" EMAIL," + 
							" PRESENCIAL", negocioInputModel.getFonte().toString()));
		}
		
		Negocio negocioObj = negocioInputDisassembler.toDomainObject(negocioInputModel);
		negocioObj.setImobiliaria(imobiliaria);
		negocioObj.setPipeline(pipeline);
		negocioObj = negocioRepository.save(negocioObj);
		
		return negocioModelAssembler.toModel(negocioObj);
	}

}
