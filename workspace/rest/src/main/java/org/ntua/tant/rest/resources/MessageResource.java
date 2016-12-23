package org.ntua.tant.rest.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.ntua.tant.rest.DBService.*;
import org.ntua.tant.rest.model.*;

@Path("/messages")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class MessageResource {
	
	DB db = new DB();
	
	@GET
	public List<Message> getMessages(@QueryParam("year") int year,
			                         @QueryParam("code") int code){
		
		
		
		
		if(year > 0) {
			return db.getMessagesForYear(year);
		}
		if(code > 0){
			return db.getMessagesForResponseCode(code);
		}
		return db.getMessages();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addMessage(Message message){
		return db.insertMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		//NOT IMPLEMENTED YET
		return null;
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteMessage(@PathParam("messageId") int id){
		return db.deleteMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	public List <Message> getMessage(@PathParam("messageId") int Id) {
		return db.getMessageWithMessageId(Id);
	}

}