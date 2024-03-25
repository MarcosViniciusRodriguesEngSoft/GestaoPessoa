package com.elotech.gestaopessoaapi.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> badRequestExcpetionHandler(BadRequestException ex, WebRequest request) {
		final ExceptionResponse errorDetails = new ExceptionResponse(ex.getMessage(), getListCauses(ex, request));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	private List<String> getListCauses(Exception ex, WebRequest request) {
		final List<String> causes = new ArrayList<>();
		causes.add(ex.getLocalizedMessage());
		getCause(ex, causes);
		causes.add(request.getDescription(false));
		return causes;
	}

	private void getCause(Throwable throwable, List<String> list) {
		if (throwable.getMessage() != null) {
			list.add(throwable.getMessage());
		}

		if (list.size() < 5 && throwable.getCause() != null) {
			getCause(throwable.getCause(), list);
		}
	}
}