package com.godoy.dashdados.api.DTO.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NegocioModel {
	
	private Long id;

	private Long imobiliariaId;

	private Long pipelineId;

	private String status;

	private String fonte;

	private Long responsavelId;

}
