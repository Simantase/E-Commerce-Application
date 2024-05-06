package com.retail.ECommerceApplication.exception;

import lombok.Getter;
@Getter
public class UserNameIsNotUniqueException extends RuntimeException{
	private String message;

}
