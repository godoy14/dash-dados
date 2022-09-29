package com.godoy.dashdados.api.DTO.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godoy.dashdados.api.DTO.input.PipelineInputModel;
import com.godoy.dashdados.domain.model.Pipeline;

@Component
public class PipelineInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Pipeline toDomainObject(PipelineInputModel pipelineInput) {
		return modelMapper.map(pipelineInput, Pipeline.class);
	}

}
