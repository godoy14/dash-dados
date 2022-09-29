package com.godoy.dashdados.api.DTO.model;

import java.util.List;

import com.godoy.dashdados.domain.model.Imobiliaria;
import com.godoy.dashdados.domain.model.Negocio;
import com.godoy.dashdados.domain.model.TiposPipelines;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PipelineModel {
	
	private Long id;
	
	private TiposPipelines tipo;
	
	private Long idSistema;
	
	private Imobiliaria imobiliaria;
	
	private List<Negocio> negocios;

}
