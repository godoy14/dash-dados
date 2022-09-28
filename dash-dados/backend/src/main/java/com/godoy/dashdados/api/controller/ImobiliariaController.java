package com.godoy.dashdados.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.godoy.dashdados.api.DTO.input.ImobiliariaInputModel;
import com.godoy.dashdados.api.DTO.model.ImobiliariaModel;
import com.godoy.dashdados.domain.service.ImobiliariaService;

@RestController
@RequestMapping("/imobiliarias")
public class ImobiliariaController {
	
	@Autowired
	private ImobiliariaService imobiliariaService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ImobiliariaModel> listAll() {
		return imobiliariaService.listar();
	}
	
	public ImobiliariaModel adicionar(@RequestBody @Valid ImobiliariaInputModel imobiliariaInput) {
		return imobiliariaService.salvar(imobiliariaInput);
	}

}
