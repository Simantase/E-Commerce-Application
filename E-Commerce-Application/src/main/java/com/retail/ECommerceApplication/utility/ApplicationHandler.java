package com.retail.ECommerceApplication.utility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.retail.ECommerceApplication.exception.EmailIsNotUniqueException;
import com.retail.ECommerceApplication.exception.IllegalArgumentException;
import com.retail.ECommerceApplication.exception.InvalidCredentialException;

import lombok.AllArgsConstructor;
@RestControllerAdvice
@AllArgsConstructor
public class ApplicationHandler {
	private ErrorStructure<String> errorStructure;

	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String message, String rootCause) {
		return new ResponseEntity<ErrorStructure<String>>(
				errorStructure.setErrorCode(status.value()).setErrorMessage(message).setRootCause(rootCause), status);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleEmailIsNotUnique(EmailIsNotUniqueException ex) {
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "User Already Exixt By Given Mail Id");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleIllegalArgument(IllegalArgumentException ex) {
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "Input Is Not Valid!!!");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleInvalidCredential(InvalidCredentialException ex) {
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "Input Is Not Valid!!!");
	}
}
