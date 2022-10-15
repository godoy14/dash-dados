package com.godoy.dashdados.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.godoy.dashdados.api.DTO.input.LeadNegocioInputModel;
import com.godoy.dashdados.api.DTO.model.LeadNegocioModel;
import com.godoy.dashdados.domain.service.LeadNegocioService;

@RestController
@RequestMapping("/negocios/leads")
public class LeadNegocioController {
	
	@Autowired
	private LeadNegocioService leadNegocioService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<LeadNegocioModel> listar() {
		return leadNegocioService.listar();
	}
	
	@GetMapping("/{pipelineId}")
	@ResponseStatus(HttpStatus.OK)
	public List<LeadNegocioModel> listarPorPipeline(@PathVariable Long pipelineId) {
		return leadNegocioService.listarPorPipeline(pipelineId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LeadNegocioModel adicionar(@RequestBody @Valid LeadNegocioInputModel leadNegocioInputModel) {
		return leadNegocioService.salvar(leadNegocioInputModel);
	}

}
