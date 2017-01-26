package org.ntua.tant.rest.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.glassfish.jersey.internal.util.Base64;

public class restclient {
	
	private String method;
	private String fileName;
    private String userName;
    private String password;
    private String url;
    private static final String Coding = "UTF-8";
	private static final String ErrorInParameters = "Name: restclient\n\nDescription: Calls a ReST Service\n\nThe following parameters are needed:\n\n-m   ReST Method to call. The implemented methods are GET, POST, PUT and DELETE.\n-f   Input XML file for POST and PUT methods. For GET method output file is optional.\n     When a file is not defined in GET method the responded XML file is printed on the screen.\n     For Delete method file is not needed.\n-URL Service URL\n\nThe following parameters are optional and they are needed only if ReST service requires Basic Authentication:\n\n-u Username\n-p Password\n";
	

	private  void parseParameters(String[] args){
		for (int i=0;i < args.length;i++){
			if ((args[i].equals("-m")) && (i<args.length-1)){
				method=args[++i].toUpperCase();
			} else if ((args[i].equals("-f")) && (i<args.length-1)){
				fileName=args[++i];
			} else if ((args[i].equals("-u")) && (i<args.length-1)){
				userName=args[++i];
			} else if ((args[i].equals("-p")) && (i<args.length-1)){
				password=args[++i];
			} else if ((args[i].equals("-URL")) && (i<args.length-1)){
				url=args[++i];
			} else if (args[i].equals("-h")) {
				System.out.println(ErrorInParameters);
				System.exit(0);
		    }
		}
		if ((method == null)||(url == null)||(((method.equals("POST"))||(method.equals("PUT")))&&(fileName == null))) {
			System.out.println(ErrorInParameters);
			System.exit(-1);
		}
	}
	
	private void getResponseFromGETRequest(String url, String fileName, String username, String password) {
	    try {
	    	URL object = new URL(url);
	        HttpURLConnection connection = (HttpURLConnection) object.openConnection();
	        connection.setReadTimeout(60 * 1000);
	        connection.setConnectTimeout(60 * 1000);
	        connection.setDoInput(true);
	        connection.setRequestMethod("GET");
	        connection.setRequestProperty("Content-Type", "application/XML");
	        if ((username!=null)&&(password!=null)) {
	        	String authorization=username+":"+password;
	            String encodedAuth="Basic "+Base64.encodeAsString(authorization);
	            connection.setRequestProperty("Authorization", encodedAuth);
	        }
	        int responseCode = connection.getResponseCode();
	        if (responseCode != 200) {
	        	System.out.println("Response Code: "+responseCode+" "+connection.getResponseMessage());
			    System.exit(-1);
	        } else {
	            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String line;
            	if (fileName != null) {
            		PrintWriter writer = new PrintWriter(fileName, Coding);
            		while ((line = rd.readLine()) != null) {
    	            	writer.println(line);
    	            }
            		writer.close();
            		System.out.println("Response Code: 200 OK. XML response has been written in a file called "+fileName);
            	} else {
            		while ((line = rd.readLine()) != null) {
    	            	System.out.println(line);;
            		}
	            }
	            rd.close();
            }
	    } catch (Exception e) {
            e.getMessage();
        }
	}
	
	public void getResponseFromPOSTRequest(String url, String fileName, String username, String password) {
	    String fileData = new String();
	    try {
	    	StringBuilder postData = new StringBuilder();
	        FileReader fr = new FileReader(fileName);
	    	BufferedReader br = new BufferedReader(fr);
    		String sCurrentLine;
	    	while ((sCurrentLine = br.readLine()) != null) {
	    		fileData+=sCurrentLine; 
	    	}
	    	br.close();
	    	postData.append(fileData);
	    	byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	        URL object = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) object.openConnection();
            connection.setReadTimeout(60 * 1000);
            connection.setConnectTimeout(60 * 1000);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/XML");
            if ((username!=null)&&(password!=null)) {
	        	String authorization=username+":"+password;
	            String encodedAuth="Basic "+Base64.encodeAsString(authorization);
	            connection.setRequestProperty("Authorization", encodedAuth);
	        }
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
	        	System.out.println("Response Code: "+responseCode+" "+connection.getResponseMessage());
			    System.exit(-1);
	        } else {
	          	System.out.println("Response Code: "+responseCode+" "+connection.getResponseMessage());
	        }            
	    } catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	}
	
	public void getResponseFromPUTRequest(String url, String fileName, String username, String password) {
	    String fileData = new String();
	    try {
	    	StringBuilder postData = new StringBuilder();
	        FileReader fr = new FileReader(fileName);
	    	BufferedReader br = new BufferedReader(fr);
    		String sCurrentLine;
	    	while ((sCurrentLine = br.readLine()) != null) {
	    		fileData+=sCurrentLine; 
	    	}
	    	br.close();
	    	postData.append(fileData);
	    	byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	        URL object = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) object.openConnection();
            connection.setReadTimeout(60 * 1000);
            connection.setConnectTimeout(60 * 1000);
            connection.setDoInput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/XML");
            if ((username!=null)&&(password!=null)) {
	        	String authorization=username+":"+password;
	            String encodedAuth="Basic "+Base64.encodeAsString(authorization);
	            connection.setRequestProperty("Authorization", encodedAuth);
	        }
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
	        	System.out.println("Response Code: "+responseCode+" "+connection.getResponseMessage());
			    System.exit(-1);
	        } else {
	          	System.out.println("Response Code: "+responseCode+" "+connection.getResponseMessage());
	        }            
	    } catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	}
	
	public void getResponseFromDELETERequest(String url, String username, String password) {
	    try {
	        URL object = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) object.openConnection();
            connection.setReadTimeout(60 * 1000);
            connection.setConnectTimeout(60 * 1000);
            connection.setDoInput(true);
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/XML");
            if ((username!=null)&&(password!=null)) {
	        	String authorization=username+":"+password;
	            String encodedAuth="Basic "+Base64.encodeAsString(authorization);
	            connection.setRequestProperty("Authorization", encodedAuth);
	        }
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
	        	System.out.println("Response Code: "+responseCode+" "+connection.getResponseMessage());
			    System.exit(-1);
	        } else {
	          	System.out.println("Response Code: "+responseCode+" "+connection.getResponseMessage());
	        }            
	    } catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	}
	
	public static void main(String[] args) {
		restclient ReST = new restclient();
		ReST.parseParameters(args);
        if (ReST.method.equals("GET")){
        	ReST.getResponseFromGETRequest(ReST.url, ReST.fileName, ReST.userName, ReST.password);	
        } else if(ReST.method.equals("POST")) {
        	ReST.getResponseFromPOSTRequest(ReST.url, ReST.fileName, ReST.userName, ReST.password);	
        } else if(ReST.method.equals("PUT")) {
        	ReST.getResponseFromPUTRequest(ReST.url, ReST.fileName, ReST.userName, ReST.password);	
        } else if(ReST.method.equals("DELETE")) {
        	ReST.getResponseFromDELETERequest(ReST.url, ReST.userName, ReST.password);	
        } else {
        	System.out.println(ErrorInParameters);
        }
	}
}
