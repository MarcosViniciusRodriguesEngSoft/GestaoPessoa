package com.elotech.gestaopessoaapi.exception;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExceptionResponse extends Exception {
	private static final long serialVersionUID = 1L;
	private final Date timestamp;
	private final String message;
	private final List<String> details;

	public ExceptionResponse(String message, List<String> details) {
		super(message, null, true, false);
		this.message = message;
		this.details = details;
		timestamp = new Date();
	}

	public ExceptionResponse(Date timestamp, String message, List<String> details) {
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}
}
