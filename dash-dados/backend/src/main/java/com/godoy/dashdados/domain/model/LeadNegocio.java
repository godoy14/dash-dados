package com.godoy.dashdados.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class LeadNegocio extends Negocio {
	
	@NotNull
	private Long idBitrix;
	
	private String codImovel;
	
	private LocalDateTime dateIn;
	
	private LocalDateTime dateOut;
	
	@Enumerated(EnumType.STRING)
	private TiposNegocios tipo;

}
