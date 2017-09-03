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
import com.iedu.domain.User;

public class UserClient {
	
	

	
	public static int clientTest() {
		
		HttpClient httpclient = HttpClientBuilder.create().build(); 
		
		HttpGet httpget = new HttpGet("http://"+Env.host_url+":8080/GroupNetWeb/" + "addUser.do"
				+ "?name=clientTestUser&age=10&password=password123");
		
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
	
	public static int clientTest2() {
		
		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet("http://"+Env.host_url+":"+Env.host_port+"/GroupNetWeb/" + "addUser.do"
				+ "?name=clientTestUser&age=10&password=password123");
		//Similar to below. Check Env class
		//HttpGet httpget = new HttpGet("http://localhost:8080/GroupNetWeb/" + "readGroup.do"
		//		);
		
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
	
	
	public static String signup(User user){
		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet("http://"+Env.host_url+":8080/GroupNetWeb/" + "addUser.do"
				+ "?name=" + user.getName() + "&age=" + user.getAge() + "&password=" + user.getPassword());
		
		System.out.println(httpget.getURI());
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String tmp = "";
				String line = "";	
				while ((tmp = rd.readLine()) != null) {
					line+=tmp;
					System.out.println("aaa"+line);
				}
				
				//int errorCode = Integer.parseInt(line);
				return line;
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
		return null;
	}	
	
	public static int login(User user){
		
		try {
			user.setName(URLEncoder.encode(user.getName(), "UTF-8"));
			user.setEmail(URLEncoder.encode(user.getEmail(), "UTF-8"));
			user.setPassword(URLEncoder.encode(user.getPassword(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet("http://"+Env.host_url+":8080/GroupNetWeb/" + "login.do"
				+ "?name=" + user.getName() + "&password=" + user.getPassword());
		
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
					int returnCode = Integer.parseInt(line);
					return returnCode;
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
		return -100;
	}	
	
	public static int userNameLookup(User user){
		
		try {
			user.setName(URLEncoder.encode(user.getName(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet("http://"+Env.host_url+":8080/GroupNetWeb/" + "nameLookUp.do"
				+ "?name=" + user.getName());
		
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
					//System.out.println(line);
					int returnCode = Integer.parseInt(line);
					return returnCode;
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
		return -100;
	}	
	
	public static void main(String[] argv){
//		User user = new User();
//		user.setName("bob");
//		user.setAge(1);
//		user.setPassword("6547");
//		String errorCode = UserClient.signup(user);
//		//int errorCode = UserClient.login(user);
//		System.out.println("errorcode : "+errorCode);
		User user = new User();
		user.setName("sam");
		System.out.println(UserClient.userNameLookup(user));
		
	}
}