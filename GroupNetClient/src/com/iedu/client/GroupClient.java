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
import com.iedu.domain.Group;
import com.iedu.domain.GroupBin;

public class GroupClient {
	
	public static List<Group> readGroups(Group group) {
		//Group group = null;

		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<Group> groups = null;
		
		try{
			InputStream in = new URL("http://"+Env.host_url+":"+Env.host_port+"/GroupNetWeb/" + "readGroup.do"
									+ "?ownerId="+group.getOwnerId()+"&viewerId="+group.getViewerId()+"&status="+group.getStatus())
					.openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			GroupBin userBin = gson.fromJson(reader,	GroupBin.class);
			groups = (ArrayList<Group>) userBin.getGroups();

			
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return groups;
	}
	
	public static int addGroup(String name, String desc, String url, int ownerId) {
		
		HttpClient httpclient = new DefaultHttpClient();
		
		try {
			name = URLEncoder.encode(name, "UTF-8");
			desc = URLEncoder.encode(desc, "UTF-8");
			url = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		HttpGet httpget = new HttpGet("http://"+Env.host_url+":"+Env.host_port+"/GroupNetWeb/" + "addGroup.do"
				+ "?name="+name+"&description="+desc+"&url="+url+"&ownerId="+ownerId);
		//Similar to below. Check Env class
		//HttpGet httpget = new HttpGet("http://localhost:8080/GroupNetWeb/" + "addGroup.do"
		//		+ "?name="+name);
		
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
	
	public static int updateGroup(Group g) {
		
		HttpClient httpclient = new DefaultHttpClient();
		
		try {
			g.setName(URLEncoder.encode(g.getName(), "UTF-8"));
			g.setDescription(URLEncoder.encode(g.getDescription(), "UTF-8"));
			g.setUrl(URLEncoder.encode(g.getUrl(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		HttpGet httpget = new HttpGet("http://"+Env.host_url+":"+Env.host_port+"/GroupNetWeb/" + "updateGroup.do"
				+ "?id="+g.getId()+"&name="+g.getName()+"&description="+g.getDescription()+"&url="+g.getUrl()+"&ownerId="+g.getOwnerId()
				+ "&status="+g.getStatus());
		//Similar to below. Check Env class
		//HttpGet httpget = new HttpGet("http://localhost:8080/GroupNetWeb/" + "addGroup.do"
		//		+ "?name="+name);
		
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
	
	public static void deleteGroup(int groupId){
		Group g = new Group();
		g.setId(groupId);
		g.setStatus("deleted");
		updateGroup(g);
	}
	
	public static void reActivateGroup(int groupId){
		Group g = new Group();
		g.setId(groupId);
		g.setStatus("active");
		updateGroup(g);
	}
	
	public static void main(String[] argv){
		//GroupClient.addGroup("new_group_name123123", "deeddddd", "http://////.........", 10);
		
		Group g = new Group();
		g.setOwnerId(69);
		List<Group> groupList = GroupClient.readGroups(g);
		System.out.println("group :"+groupList);
		//System.out.println("group size:"+groupList.size());
		
		//deleteGroup(44);
		//reActivateGroup(44);

	}
}