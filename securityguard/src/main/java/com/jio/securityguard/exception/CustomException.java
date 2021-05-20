package com.jio.securityguard.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String msg;

	public CustomException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
}
