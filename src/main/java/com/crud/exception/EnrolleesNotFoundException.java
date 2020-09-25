package com.crud.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnrolleesNotFoundException extends RuntimeException {

	public EnrolleesNotFoundException(String exception) {
		super(exception);
	}

}
