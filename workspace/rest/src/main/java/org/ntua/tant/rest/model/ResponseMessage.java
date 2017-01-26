package org.ntua.tant.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMessage {
	private int code;
	private String responseMessage;
	
	public ResponseMessage() {
	}
	
	public ResponseMessage(int code, String responseMessage) {
		super();
		this.code = code;
		this.responseMessage = responseMessage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}



	
}