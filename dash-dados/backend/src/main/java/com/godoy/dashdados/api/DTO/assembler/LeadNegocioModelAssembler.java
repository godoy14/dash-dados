package com.godoy.dashdados.api.DTO.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godoy.dashdados.api.DTO.input.LeadNegocioInputModel;
import com.godoy.dashdados.api.DTO.model.LeadNegocioModel;
import com.godoy.dashdados.domain.model.LeadNegocio;

@Component
public class LeadNegocioModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public LeadNegocioModel toModel(LeadNegocio lead) {
		return modelMapper.map(lead, LeadNegocioModel.class);
	}
	
	public LeadNegocioInputModel toInputModel(LeadNegocioModel leadModel) {
		return modelMapper.map(leadModel, LeadNegocioInputModel.class);
	}
	
	public List<LeadNegocioModel> toCollectionModel(List<LeadNegocio> leads) {
		return leads.stream()
				.map(lead -> toModel(lead))
				.collect(Collectors.toList());
	}

}
