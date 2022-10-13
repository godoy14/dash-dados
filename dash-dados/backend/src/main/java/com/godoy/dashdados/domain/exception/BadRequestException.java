package com.godoy.dashdados.domain.exception;

public class BadRequestException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public BadRequestException(String msg) {
		super(msg);
	}

}
