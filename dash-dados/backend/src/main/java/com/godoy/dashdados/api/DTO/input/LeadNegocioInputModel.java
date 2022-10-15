package com.godoy.dashdados.api.DTO.input;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadNegocioInputModel {
	
	@NotNull
	private Long imobiliariaCod;
	
	@NotNull
	private Long pipelineCod;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String fonte;

	@NotNull
	private Long responsavelCod;
	
	@NotNull
	private Long idBitrix;
	
	@NotBlank
	private String codImovel;
	
	@NotNull
	private LocalDateTime dateIn;
	
	private LocalDateTime dateOut;
	
	@NotBlank
	private String tipo;

}
