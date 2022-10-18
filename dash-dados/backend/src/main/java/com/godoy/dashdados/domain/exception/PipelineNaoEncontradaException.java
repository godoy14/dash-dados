package com.godoy.dashdados.domain.exception;

public class PipelineNaoEncontradaException extends NegocioException{

	private static final long serialVersionUID = 1L;
	
	public PipelineNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public PipelineNaoEncontradaException(Long pipelineId) {
		this(String.format("Não foi encontrada Pipeline com o código %d", pipelineId));
	}
	
}
