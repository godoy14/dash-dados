package com.godoy.dashdados.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.godoy.dashdados.api.DTO.input.LeadNegocioInputModel;
import com.godoy.dashdados.api.DTO.input.PipelineInputModel;
import com.godoy.dashdados.api.DTO.model.LeadNegocioModel;
import com.godoy.dashdados.domain.model.Negocio;
import com.godoy.dashdados.domain.model.Pipeline;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(PipelineInputModel.class, Pipeline.class).addMappings(mapper -> mapper.skip(Pipeline::setId));

		modelMapper.createTypeMap(LeadNegocioInputModel.class, Negocio.class).addMappings(mapper -> mapper.skip(Negocio::setId));
		
		modelMapper.createTypeMap(LeadNegocioModel.class, Negocio.class).addMappings(mapper -> mapper.skip(Negocio::setId));
		
		return modelMapper;
	}

}
