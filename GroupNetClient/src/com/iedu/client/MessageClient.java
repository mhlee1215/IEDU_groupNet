package com.iedu.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.iedu.domain.Group;
//
//import edu.iedu.flashcard.dao.domain.User;
//import edu.iedu.flashcard.dao.domain.UserBin;
//import edu.iedu.flashcard.var.Env;
import com.iedu.domain.GroupBin;
import com.iedu.domain.Message;
import com.iedu.domain.MessageBin;
import com.iedu.domain.User;

public class MessageClient {
	
	public static List<Message> showMyMessage(int recieveID){
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<Message> messages = null;
		
		try{
			InputStream in = new URL("http://"+Env.host_url+":"+Env.host_port+"/GroupNetWeb/" + "showMessage.do"
									+"?receiveID="+recieveID)
					.openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			MessageBin messageBin = gson.fromJson(reader,	MessageBin.class);
			messages = (ArrayList<Message>) messageBin.getMessages();

			
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		
		return messages;
	}	

	public static List<Message> showMessage(int receiveID, int sendID){
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<Message> messages = null;
		
		try{
			InputStream in = new URL("http://"+Env.host_url+":"+Env.host_port+"/GroupNetWeb/" + "showMessage.do"
									+"?sendID="+sendID+"&receiveID="+receiveID)
					.openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			MessageBin messageBin = gson.fromJson(reader,	GroupBin.class);
			messages = (ArrayList<Message>) messageBin.getMessages();

			
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		
		return messages;
	}	
	
	public static int send(Message message){
		
//		try {
//			message.setName(URLEncoder.encode(message.getName(), "UTF-8"));
//			message.setEmail(URLEncoder.encode(user.getEmail(), "UTF-8"));
//			message.setPassword(URLEncoder.encode(user.getPassword(), "UTF-8"));
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		} 
  
		
		HttpClient httpclient = new DefaultHttpClient();

	 	HttpGet httpget = new HttpGet("http://"+Env.host_url+":8080/GroupNetWeb/" + "send.do"
				+ "?name=" + message.getId() + "&password=" + message.getSendID());
		
		System.out.println(httpget.getURI());
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String line = "";	
				while ((line = rd.readLine()) != null) {
					System.out.println(line);
					int errorCode = Integer.parseInt(line);
					return errorCode;
				}
				
				
			}
			
			httpget.abort();
			httpclient.getConnectionManager().shutdown();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return 0;
	}	
	
	public static void main(String[] argv){
		System.out.println(MessageClient.showMyMessage(10));
	}
}