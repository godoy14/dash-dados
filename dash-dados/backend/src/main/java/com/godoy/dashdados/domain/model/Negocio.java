package com.godoy.dashdados.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Inheritance
public class Negocio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@NotNull
	@ManyToOne
	private Imobiliaria imobiliaria;
	
	@JsonIgnore
	@NotNull
	@OneToOne
	private Pipeline pipeline;
	
	@Enumerated(EnumType.STRING)
	private StatusNegocio status;
	
	@Enumerated(EnumType.STRING)
	private FonteNegocio fonte;
	
	@ManyToOne
	private ResponsavelNegocio responsavel;

}
