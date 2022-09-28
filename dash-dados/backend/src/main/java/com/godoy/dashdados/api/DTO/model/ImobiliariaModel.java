package com.godoy.dashdados.api.DTO.model;

import java.util.List;

import com.godoy.dashdados.domain.model.Imobiliaria;
import com.godoy.dashdados.domain.model.Pipeline;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImobiliariaModel {
	
	public ImobiliariaModel(Imobiliaria imobiliaria) {
		super();
		this.id = imobiliaria.getId();
		this.isAdmin = imobiliaria.getIsAdmin();
		this.name = imobiliaria.getName();
		this.password = imobiliaria.getPassword();
		this.pipe = imobiliaria.getPipe();
		this.urlSistema = imobiliaria.getUrlSistema();
	}

	private Long id;
	
	private String urlSistema;
	
	private String name;
	
	private String password;
	
	private Boolean isAdmin;
	
	private List<Pipeline> pipe;

}
