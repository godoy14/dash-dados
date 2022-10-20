package com.godoy.dashdados.core.validation;

import com.godoy.dashdados.domain.model.Imobiliaria;
import com.godoy.dashdados.domain.model.Pipeline;
import com.godoy.dashdados.domain.model.ResponsavelNegocio;

import lombok.Data;

@Data
public class EntitiesValidationObj {
	private Imobiliaria imobiliria;
	private Pipeline pipeline;
	private ResponsavelNegocio responsavel;
}
