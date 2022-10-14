package com.godoy.dashdados.api.DTO.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godoy.dashdados.api.DTO.input.ResponsavelInputModel;
import com.godoy.dashdados.domain.model.ResponsavelNegocio;

@Component
public class ResponsavelInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponsavelNegocio toDomainObject(ResponsavelInputModel responsavelInputModel) {
		return modelMapper.map(responsavelInputModel, ResponsavelNegocio.class);
	}


}
