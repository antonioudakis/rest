package org.ntua.tant.rest.exceptions;

public class BadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	public BadRequestException(String message){
		super(message);
	}
}