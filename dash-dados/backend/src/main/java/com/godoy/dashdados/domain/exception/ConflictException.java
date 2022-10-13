package com.godoy.dashdados.domain.exception;

public class ConflictException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public ConflictException(String msg) {
		super(msg);
	}

}
