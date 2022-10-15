package com.godoy.dashdados.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godoy.dashdados.api.DTO.assembler.ImobiliariaInputDisassembler;
import com.godoy.dashdados.api.DTO.assembler.ImobiliariaModelAssembler;
import com.godoy.dashdados.api.DTO.input.ImobiliariaInputModel;
import com.godoy.dashdados.api.DTO.model.ImobiliariaModel;
import com.godoy.dashdados.domain.exception.ImobiliariaNaoEncontradaException;
import com.godoy.dashdados.domain.exception.NegocioException;
import com.godoy.dashdados.domain.model.Imobiliaria;
import com.godoy.dashdados.domain.repository.ImobiliariaRepository;

@Service
public class ImobiliariaService {
	
	@Autowired
	private ImobiliariaRepository imobiliariaRepository;
	
	@Autowired
	private ImobiliariaModelAssembler imobiliariaModelAssembler;
	
	@Autowired
	private ImobiliariaInputDisassembler imobiliariaInputDisassembler;
	
	public Imobiliaria buscarOuFalhar(Long imobiliariaId) {
		return imobiliariaRepository.findById(imobiliariaId).orElseThrow(() -> new ImobiliariaNaoEncontradaException(imobiliariaId));
	}
	
	public List<ImobiliariaModel> listar() {
		List<Imobiliaria> lista = imobiliariaRepository.findAll();
		return imobiliariaModelAssembler.toCollectionModel(lista);
	}
	
	public ImobiliariaModel detalhes(Long imobiliariaId) {
		Imobiliaria imobiliariaDetail = buscarOuFalhar(imobiliariaId);
		return imobiliariaModelAssembler.toModel(imobiliariaDetail);
	}
	
	@Transactional
	public ImobiliariaModel salvar(ImobiliariaInputModel imobiliariaInput) {
		Imobiliaria existImobiliaria = imobiliariaRepository.findByEmail(imobiliariaInput.getEmail());
		
		if (existImobiliaria != null) {
			throw new NegocioException("Já existe Imobiliária cadastrada com esse Email");
		}
		
		Imobiliaria imobiliariaObj = imobiliariaInputDisassembler.toDomainObject(imobiliariaInput);
		
		imobiliariaObj = imobiliariaRepository.save(imobiliariaObj);
		
		return imobiliariaModelAssembler.toModel(imobiliariaObj);
	}
	
	@Transactional
	public void excluir(Long imobiliariaId) {
		try {
			imobiliariaRepository.deleteById(imobiliariaId);
			imobiliariaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ImobiliariaNaoEncontradaException(imobiliariaId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException("Imobiliaria em uso, não é possível realizar sua exclusão.");
		}
	}

}
