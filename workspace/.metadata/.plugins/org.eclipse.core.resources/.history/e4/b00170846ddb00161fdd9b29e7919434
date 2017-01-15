package org.ntua.tant.rest.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ntua.tant.rest.model.ErrorMessage;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException>{

	@Override
	public Response toResponse(BadRequestException ex) {

		ErrorMessage errorMessage = new ErrorMessage(400,ex.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
	}

}
