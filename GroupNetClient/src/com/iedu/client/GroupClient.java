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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
//
//import edu.iedu.flashcard.dao.domain.User;
//import edu.iedu.flashcard.dao.domain.UserBin;
//import edu.iedu.flashcard.var.Env;

public class GroupClient {
	
	public static int addGroup(String name) {
		
		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet("http://localhost:8080/GroupNetWeb/" + "addGroup.do"
				+ "?name="+name);
		
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
	
	public static void main(String[] argv){
		GroupClient.addGroup("new_group_name");
	}
}