package org.ntua.tant.rest.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ntua.tant.rest.model.ErrorMessage;

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException>{

	@Override
	public Response toResponse(UnauthorizedException ex) {

		ErrorMessage errorMessage = new ErrorMessage(Status.UNAUTHORIZED.getStatusCode(),ex.getMessage());
		return Response.status(Status.UNAUTHORIZED).entity(errorMessage).build();
	}

}