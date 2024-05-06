package com.retail.ECommerceApplication.utility;
import org.springframework.stereotype.Component;

import lombok.Getter;
@Getter
@Component
public class ErrorStructure<T> {
	private int errorCode;
	private String errorMessage;
	private T rootCause;;
	public ErrorStructure<T> setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}
	public ErrorStructure<T> setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
	public ErrorStructure<T> setRootCause(T rootCause) {
		this.rootCause = rootCause;
		return this;
	}
	
}
