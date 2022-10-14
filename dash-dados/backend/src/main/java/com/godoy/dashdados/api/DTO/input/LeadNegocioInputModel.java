package com.godoy.dashdados.api.DTO.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadNegocioInputModel {
	
	@NotNull
	private Long imobiliariaId;
	
	@NotNull
	private Long pipelineId;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String fonte;

	@NotNull
	private Long responsavelId;
	
	@NotNull
	private Long idBitrix;
	
	@NotBlank
	private String codImovel;
	
	@NotBlank
	private String dateIn;
	
	private String dateOut;
	
	@NotBlank
	private String tipo;

}
