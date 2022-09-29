package com.godoy.dashdados.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.godoy.dashdados.api.DTO.input.PipelineInputModel;
import com.godoy.dashdados.api.DTO.model.PipelineModel;
import com.godoy.dashdados.domain.service.PipelineService;

@RestController
@RequestMapping("/pipelines")
public class PipelineController {
	
	@Autowired
	private PipelineService pipelineService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PipelineModel> listAll() {
		return pipelineService.listar();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PipelineModel adicionar(@RequestBody @Valid PipelineInputModel pipelineInputModel) {
		return pipelineService.salvar(pipelineInputModel);
	}

}
