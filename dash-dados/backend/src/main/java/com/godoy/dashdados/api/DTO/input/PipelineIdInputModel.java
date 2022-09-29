package com.godoy.dashdados.api.DTO.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PipelineIdInputModel {
	
	@NotNull
	private Long id;

}
