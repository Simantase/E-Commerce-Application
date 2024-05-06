package com.retail.ECommerceApplication.utility;
import org.springframework.stereotype.Component;

import lombok.Getter;
@Getter
@Component
public class ResponseStructure<T> {
	private int statusCode;
	private String statusMessage;
	private T statusData;
	public ResponseStructure<T> setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public ResponseStructure<T> setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
		return this;
	}
	public ResponseStructure<T> setStatusData(T statusData) {
		this.statusData = statusData;
		return this;
	}

}
