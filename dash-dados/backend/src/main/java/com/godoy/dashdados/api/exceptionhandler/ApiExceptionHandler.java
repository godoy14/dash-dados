package com.godoy.dashdados.api.exceptionhandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.godoy.dashdados.domain.exception.BadRequestException;
import com.godoy.dashdados.domain.exception.ConflictException;
import com.godoy.dashdados.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		
		var status = HttpStatus.NOT_FOUND;
		
		var exceptionHandlerObj = new ExceptionHandlerObj();
		exceptionHandlerObj.setStatus(status.value());
		exceptionHandlerObj.setDateTime(OffsetDateTime.now());
		exceptionHandlerObj.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, exceptionHandlerObj, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<Object> handleNegocio(ConflictException ex, WebRequest request) {
		
		var status = HttpStatus.CONFLICT;
		
		var exceptionHandlerObj = new ExceptionHandlerObj();
		exceptionHandlerObj.setStatus(status.value());
		exceptionHandlerObj.setDateTime(OffsetDateTime.now());
		exceptionHandlerObj.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, exceptionHandlerObj, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleNegocio(BadRequestException ex, WebRequest request) {
		
		var status = HttpStatus.BAD_REQUEST;
		
		var exceptionHandlerObj = new ExceptionHandlerObj();
		exceptionHandlerObj.setStatus(status.value());
		exceptionHandlerObj.setDateTime(OffsetDateTime.now());
		exceptionHandlerObj.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, exceptionHandlerObj, new HttpHeaders(), status, request);
	}

}
