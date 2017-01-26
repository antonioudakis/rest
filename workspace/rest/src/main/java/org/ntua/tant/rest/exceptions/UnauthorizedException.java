package org.ntua.tant.rest.exceptions;

public class UnauthorizedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	public UnauthorizedException(String message){
		super(message);
	}
}