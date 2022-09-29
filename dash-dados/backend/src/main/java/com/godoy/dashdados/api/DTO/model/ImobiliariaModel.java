package com.godoy.dashdados.api.DTO.model;

import java.util.List;

import com.godoy.dashdados.domain.model.Pipeline;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImobiliariaModel {

	private Long id;
	
	private String urlSistema;
	
	private String name;
	
	private String password;
	
	private Boolean isAdmin;
	
	private List<Pipeline> pipe;

}
