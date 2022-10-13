package com.godoy.dashdados.api.exceptionhandler;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class ExceptionHandlerObj {
	
	private Integer status;
	private OffsetDateTime dateTime;
	private String title;

}
