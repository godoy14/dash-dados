package com.godoy.dashdados.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godoy.dashdados.api.DTO.assembler.ResponsavelInputDisassembler;
import com.godoy.dashdados.api.DTO.assembler.ResponsavelModelAssembler;
import com.godoy.dashdados.api.DTO.input.ResponsavelInputModel;
import com.godoy.dashdados.api.DTO.model.ResponsavelModel;
import com.godoy.dashdados.domain.exception.ConflictException;
import com.godoy.dashdados.domain.exception.ImobiliariaNaoEncontradaException;
import com.godoy.dashdados.domain.exception.NegocioException;
import com.godoy.dashdados.domain.model.ResponsavelNegocio;
import com.godoy.dashdados.domain.repository.ResponsavelNegocioRepository;

@Service
public class ResponsavelNegocioService {
	
	@Autowired
	private ResponsavelNegocioRepository responsavelNegocioRepository;
	
	@Autowired
	private ResponsavelInputDisassembler responsavelInputDisassembler;
	
	@Autowired
	private ResponsavelModelAssembler responsavelModelAssembler;
	
	public ResponsavelNegocio buscarOuFalhar(Long responsavelId) {
		return responsavelNegocioRepository.findById(responsavelId).orElseThrow(() -> new NegocioException("Responsavel não encontrado."));
	}
	
	public List<ResponsavelModel> listar(){
		return responsavelModelAssembler.toCollectionModel(responsavelNegocioRepository.findAll());
	}
	
	public ResponsavelModel detalhes(Long responsavelId) {
		return responsavelModelAssembler.toModel(buscarOuFalhar(responsavelId));
	}
	
	public ResponsavelModel findByEmail(String email) {
		ResponsavelNegocio responsavel = responsavelNegocioRepository.findByEmail(email);
		
		if (responsavel != null) {
			return responsavelModelAssembler.toModel(responsavel);
		}
		
		throw new NegocioException("Não existe responsável cadastrado com esse email");
	}
	
	@Transactional
	public ResponsavelModel salvar(ResponsavelInputModel responsavelInput) {
		
		ResponsavelNegocio existentResponsavel = responsavelNegocioRepository.findByEmail(responsavelInput.getEmail());
		
		if (existentResponsavel != null) {
			throw new ConflictException("There is already a patient registered with this email!");
		}
		
		ResponsavelNegocio responsavel = responsavelInputDisassembler.toDomainObject(responsavelInput);
		responsavel = responsavelNegocioRepository.save(responsavel);
		
		return responsavelModelAssembler.toModel(responsavel);
	}
	
	@Transactional
	public void excluir(Long responsavelId) {
		try {
			responsavelNegocioRepository.deleteById(responsavelId);
			responsavelNegocioRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ImobiliariaNaoEncontradaException(responsavelId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException("Responsavel em uso, não é possível realizar sua exclusão.");
		}
	}

}
