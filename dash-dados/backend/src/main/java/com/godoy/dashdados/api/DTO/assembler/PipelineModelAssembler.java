package com.godoy.dashdados.api.DTO.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godoy.dashdados.api.DTO.model.PipelineModel;
import com.godoy.dashdados.domain.model.Pipeline;

@Component
public class PipelineModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public PipelineModel toModel(Pipeline pipeline) {
		return modelMapper.map(pipeline, PipelineModel.class);
	}
	
	public List<PipelineModel> toCollectionModel(List<Pipeline> pipelines) {
		return pipelines.stream()
				.map(pipeline -> toModel(pipeline))
				.collect(Collectors.toList());
	}
	
}
