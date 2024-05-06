package com.retail.ECommerceApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OtpIsNotValidException extends RuntimeException{
	private String message;
}
