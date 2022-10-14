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

import com.godoy.dashdados.api.DTO.input.NegocioInputModel;
import com.godoy.dashdados.api.DTO.model.NegocioModel;
import com.godoy.dashdados.domain.service.NegocioService;

@RestController
@RequestMapping("/negocios")
public class NegocioController {
	
	@Autowired
	private NegocioService negocioService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<NegocioModel> listarTodos() {
		return negocioService.listar();
	}
	
	@GetMapping("/{negocioId}")
	@ResponseStatus(HttpStatus.OK)
	public NegocioModel detalhes(@PathVariable Long negocioId) {
		return negocioService.detalhes(negocioId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public NegocioModel adicionar(@RequestBody @Valid NegocioInputModel negocioInputModel) {
		return negocioService.salvar(negocioInputModel);
	}
	
	@DeleteMapping("/{negocioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long negocioId) {
		negocioService.excluir(negocioId);
	}

}
