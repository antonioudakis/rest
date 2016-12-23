package org.ntua.tant.rest.DBService;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ntua.tant.rest.model.Message;

public class DB {
		private Connection connection;
		private List<Message> messages = new ArrayList<>();
		private static String ConnectionURL = "jdbc:postgresql://localhost:5432/rest";
		private static String forNamePath = "org.postgresql.Driver";
		private static String username = "postgres";
		private static String password = "tant";
		
		public DB(){
			this.connection = null;
			connect();
			setMessages(this.selectMessages(null));
		}
		
		public void connect() {
			try {
				if (connection == null) {
					Class.forName(forNamePath);
					connection = DriverManager.getConnection(ConnectionURL,username,password);
				}
			} catch (Exception e) {
		        System.out.println(e.getMessage());
		        connection = null;
			}
		}
		
		public void disconnect() {
			
			try {
				connection.close();
				connection = null;
			} catch (Exception e) {
		        System.err.println(e.getMessage()); 
			}
		}
		
		public List<Message> selectMessages(String where){
		    Statement stmt = null;
			try {
				connect();
				connection.setAutoCommit(false);
				stmt = connection.createStatement();
				List<Message> messages = new ArrayList<>();
				ResultSet rs = null;
				if (where == null)
					rs = stmt.executeQuery( "SELECT * FROM \"messages\"" );
				else
					rs = stmt.executeQuery( "SELECT * FROM \"messages\" "+ where );
			    while ( rs.next() ) {
			       	int id = rs.getInt("id");
			       	int userid = rs.getInt("userid");
			       	Date requestDate = rs.getDate("requestDate");
			       	int responseCode = rs.getInt("responseCode");
			       	String  responseMessage = rs.getString("responseMessage");
			        String  message = rs.getString("message");
			        Message messageRec = new Message(id, userid, requestDate,responseCode, responseMessage,message);
			        messages.add(messageRec);
			    }
			    rs.close();
			    stmt.close();
			    disconnect();
			    return messages;
			} catch ( Exception e ) {
				System.err.println( e.getMessage() );
				return null;
			}
		}
		
		public String insertMessages(List<Message> messages){
		    Statement stmt = null;
			try {
				connect();
				connection.setAutoCommit(false);
				int i = 0;
				for(Message message : messages){
					stmt = connection.createStatement();
					stmt.executeUpdate("INSERT INTO \"messages\" "+message.getMessageVariables()+" values "+message.getMessageValues()+";");
					stmt.close();
					connection.commit();
					i++;
				}
				disconnect();
			    return i+" ROWS INSERTED";
			} catch ( Exception e ) {
				System.err.println( e.getMessage() );
				return "0 ROWS INSERTED";
			}
		}
		
		public String insertMessage(Message message){
		    Statement stmt = null;
			try {
				connect();
				connection.setAutoCommit(false);
				stmt = connection.createStatement();
				stmt.executeUpdate("INSERT INTO \"messages\" "+message.getMessageVariables()+" values "+message.getMessageValues()+";");
				stmt.close();
				connection.commit();
				disconnect();
			    return "1 ROW INSERTED";
			} catch ( Exception e ) {
				System.err.println( e.getMessage() );
				return "Failed to insert";
			}
		}
		
		public String deleteMessage(int id){
		    Statement stmt = null;
			try {
				connect();
				connection.setAutoCommit(false);
				stmt = connection.createStatement();
				stmt.executeUpdate("DELETE FROM \"messages\" WHERE id = "+id+";");
				stmt.close();
				connection.commit();
				disconnect();
			    return "1 ROW DELETED";
			} catch ( Exception e ) {
				System.err.println( e.getMessage() );
				return "Failed to Delete";
			}
		}
		
		public Connection getConnection() {
			return this.connection;
		}
		
		public void setConnection(Connection connection) {
			this.connection = connection;
		}

		public List<Message> getMessages() {
			return messages;
		}

		public void setMessages(List<Message> messages) {
			this.messages = messages;
		}
		
		public List<Message> getMessagesForYear(int year){
			return selectMessages("where extract(year from \"requestDate\") = "+year);
		}
		
		public List<Message> getMessagesForResponseCode(int code){
			return selectMessages("where \"responseCode\" = "+code);
		}
		
		public List<Message> getMessageWithMessageId(int id){
			return selectMessages("where \"id\" = "+id);
		}
}
