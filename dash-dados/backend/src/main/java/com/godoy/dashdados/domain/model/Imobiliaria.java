package com.godoy.dashdados.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Imobiliaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "urlSistema", nullable = false)
	private String urlSistema;
	
	@NotBlank
	@Column(name = "imobiliaria_nome", nullable = false)
	private String name;
	
	@NotBlank
	@Email
	@Column(name = "imobiliaria_email", nullable = false)
	private String email;
	
	@NotBlank
	@Column(name = "imobiliaria_password", nullable = false)
	private String password;
	
	@Column(name = "isAdmin", nullable = false)
	private Boolean isAdmin = false;
	
	@OneToMany(mappedBy = "imobiliaria")
	private List<Pipeline> pipe;

}
