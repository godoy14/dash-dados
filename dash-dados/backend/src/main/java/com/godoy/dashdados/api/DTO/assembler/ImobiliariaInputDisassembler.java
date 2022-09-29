package com.godoy.dashdados.api.DTO.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godoy.dashdados.api.DTO.input.ImobiliariaInputModel;
import com.godoy.dashdados.domain.model.Imobiliaria;

@Component
public class ImobiliariaInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Imobiliaria toDomainObject(ImobiliariaInputModel imobiliariaInput) {
		return modelMapper.map(imobiliariaInput, Imobiliaria.class);
	}

}
