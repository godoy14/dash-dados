package com.godoy.dashdados.domain.exception;

public class ImobiliariaNaoEncontradaException extends NegocioException{

	private static final long serialVersionUID = 1L;
	
	public ImobiliariaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public ImobiliariaNaoEncontradaException(Long imobiliariaId) {
		this(String.format("Não foi encontrada imobiliária com o código %d", imobiliariaId));
	}
	
}
