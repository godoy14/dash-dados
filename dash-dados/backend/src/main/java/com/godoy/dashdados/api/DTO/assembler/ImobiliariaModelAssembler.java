package com.godoy.dashdados.api.DTO.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godoy.dashdados.api.DTO.model.ImobiliariaModel;
import com.godoy.dashdados.domain.model.Imobiliaria;

@Component
public class ImobiliariaModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ImobiliariaModel toModel(Imobiliaria imobiliaria) {
		return modelMapper.map(imobiliaria, ImobiliariaModel.class);
	}
	
	public List<ImobiliariaModel> toCollectionModel(List<Imobiliaria> imobiliarias) {
		return imobiliarias.stream()
				.map(imobiliaria -> toModel(imobiliaria))
				.collect(Collectors.toList());
	}

}
