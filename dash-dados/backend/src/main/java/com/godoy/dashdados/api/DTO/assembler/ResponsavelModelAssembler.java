package com.godoy.dashdados.api.DTO.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godoy.dashdados.api.DTO.model.ResponsavelModel;
import com.godoy.dashdados.domain.model.ResponsavelNegocio;

@Component
public class ResponsavelModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponsavelModel toModel(ResponsavelNegocio responsavel) {
		return modelMapper.map(responsavel, ResponsavelModel.class);
	}
	
	public List<ResponsavelModel> toCollectionModel(List<ResponsavelNegocio> responsaveis) {
		return responsaveis.stream()
				.map(responsavel -> toModel(responsavel))
				.collect(Collectors.toList());
	}

}
