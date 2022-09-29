package com.godoy.dashdados.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Pipeline {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TiposPipelines tipo;
	
	@NotNull
	private Long idSistema;
	
	@JsonIgnore
	@ManyToOne
	private Imobiliaria imobiliaria;
	
	@OneToMany(mappedBy = "pipeline")
	private List<Negocio> negocios;

}
