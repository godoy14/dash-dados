package com.godoy.dashdados.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<ImobiliariaModel> listar() {
		List<Imobiliaria> list = imobiliariaRepository.findAll();
		return list.stream().map(x -> new ImobiliariaModel(x)).collect(Collectors.toList());
	}
	
	public Imobiliaria buscarOuFalhar(Long imobiliariaId) {
		return imobiliariaRepository.findById(imobiliariaId).orElseThrow(() -> new ImobiliariaNaoEncontradaException(imobiliariaId));
	}
	
	public ImobiliariaModel salvar(ImobiliariaInputModel imobiliariaInput) {
		Imobiliaria existImobiliaria = imobiliariaRepository.findByEmail(imobiliariaInput.getEmail());
		
		if (existImobiliaria != null) {
			throw new NegocioException("Já existe Imobiliária cadastrada com esse Email");
		}
		
		Imobiliaria imobiliariaObj = new Imobiliaria();
		imobiliariaObj.setName(imobiliariaInput.getName());
		imobiliariaObj.setEmail(imobiliariaInput.getEmail());
		imobiliariaObj.setIsAdmin(imobiliariaInput.getIsAdmin());
		imobiliariaObj.setPassword(imobiliariaInput.getPassword());
		imobiliariaObj.setPipe(imobiliariaInput.getPipe());
		imobiliariaObj.setUrlSistema(imobiliariaInput.getUrlSistema());
		
		imobiliariaObj = imobiliariaRepository.save(imobiliariaObj);
		
		return new ImobiliariaModel(imobiliariaObj);
	}

}
