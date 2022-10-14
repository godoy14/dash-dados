package com.godoy.dashdados.api.DTO.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadNegocioModel {
	
	private Long id;
	
	private Long imobiliariaId;
	
	private Long pipelineId;
	
	private String status;
	
	private String fonte;

	private Long responsavelId;
	
	private Long idBitrix;
	
	private String codImovel;
	
	private String dateIn;
	
	private String dateOut;
	
	private String tipo;

}
