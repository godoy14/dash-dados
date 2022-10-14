package com.godoy.dashdados.api.DTO.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsavelInputModel {
	
	@NotNull
	private Long idSistema;

	@NotBlank
	private String name;

	@NotBlank
	private String email;

}
