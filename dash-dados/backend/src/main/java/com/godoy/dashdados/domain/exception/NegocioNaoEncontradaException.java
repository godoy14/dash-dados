package com.godoy.dashdados.domain.exception;

public class NegocioNaoEncontradaException extends NegocioException{

	private static final long serialVersionUID = 1L;
	
	public NegocioNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public NegocioNaoEncontradaException(Long negocioId) {
		this(String.format("Não foi encontrada Negócio com o código %d", negocioId));
	}
	
}
