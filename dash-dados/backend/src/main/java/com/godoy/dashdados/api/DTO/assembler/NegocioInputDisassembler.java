package com.godoy.dashdados.api.DTO.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godoy.dashdados.api.DTO.input.NegocioInputModel;
import com.godoy.dashdados.domain.model.Negocio;

@Component
public class NegocioInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Negocio toDomainObject(NegocioInputModel negocioInput) {
		return modelMapper.map(negocioInput, Negocio.class);
	}

}
