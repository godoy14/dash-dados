package com.godoy.dashdados.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.godoy.dashdados.api.DTO.input.ResponsavelInputModel;
import com.godoy.dashdados.api.DTO.model.ResponsavelModel;
import com.godoy.dashdados.domain.service.ResponsavelNegocioService;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelNegocioController {
	
	@Autowired
	private ResponsavelNegocioService responsavelNegocioService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ResponsavelModel> listar(){
		return responsavelNegocioService.listar();
	}
	
	@GetMapping("/{responsavelId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponsavelModel detalhes(@PathVariable Long responsavelId) {
		return responsavelNegocioService.detalhes(responsavelId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponsavelModel adicionar(@RequestBody @Valid ResponsavelInputModel responsavelInputModel) {
		return responsavelNegocioService.salvar(responsavelInputModel);
	}
	
	@DeleteMapping("/{responsavelId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long responsavelId) {
		responsavelNegocioService.excluir(responsavelId);
	}

}
