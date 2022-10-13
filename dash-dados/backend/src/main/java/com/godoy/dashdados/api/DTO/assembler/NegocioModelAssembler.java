package com.godoy.dashdados.api.DTO.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godoy.dashdados.api.DTO.model.NegocioModel;
import com.godoy.dashdados.domain.model.Negocio;

@Component
public class NegocioModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public NegocioModel toModel(Negocio negocio) {
		return modelMapper.map(negocio, NegocioModel.class);
	}
	
	public List<NegocioModel> toCollectionModel(List<Negocio> negocios) {
		return negocios.stream()
				.map(negocio -> toModel(negocio))
				.collect(Collectors.toList());
	}

}
