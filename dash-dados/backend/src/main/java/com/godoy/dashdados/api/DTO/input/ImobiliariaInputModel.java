package com.godoy.dashdados.api.DTO.input;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImobiliariaInputModel {
	
	@NotBlank
	private String urlSistema;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	private Boolean isAdmin;
	
	private List<PipelineIdInputModel> pipe;

}
