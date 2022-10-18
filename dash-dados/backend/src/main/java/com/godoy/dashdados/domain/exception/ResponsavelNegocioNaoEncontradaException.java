package com.godoy.dashdados.domain.exception;

public class ResponsavelNegocioNaoEncontradaException extends NegocioException{

	private static final long serialVersionUID = 1L;
	
	public ResponsavelNegocioNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public ResponsavelNegocioNaoEncontradaException(Long responsavelId) {
		this(String.format("Não foi encontrada responsável com o código %d", responsavelId));
	}
	
}
