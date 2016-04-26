package com.cfang.WeChat.exception;

public class ValidateRequestException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	public ValidateRequestException(String message){
		super(message);
	}

}
