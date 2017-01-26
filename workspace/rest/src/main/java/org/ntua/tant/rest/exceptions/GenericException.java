package org.ntua.tant.rest.exceptions;

public class GenericException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	public GenericException(String message){
		super(message);
	}
}