package com.godoy.dashdados.api.DTO.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godoy.dashdados.api.DTO.input.LeadNegocioInputModel;
import com.godoy.dashdados.domain.model.LeadNegocio;

@Component
public class LeadNegocioInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public LeadNegocio toDomainObject(LeadNegocioInputModel leadNegocioInputModel) {
		return modelMapper.map(leadNegocioInputModel, LeadNegocio.class);
	}

}
