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
import com.iedu.domain.Membership;
import com.iedu.domain.MembershipBin;

public class MembershipClient {

	public static List<Membership> readMyMemberships(int userID) {
		Membership membership = null;

		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<Membership> memberships = null;

		try{
			InputStream in = new URL("http://"+Env.host_url+":"+Env.host_port+"/GroupNetWeb/" + "readMyMembership.do" + "?userID="+userID)
					.openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			MembershipBin userBin = gson.fromJson(reader,	MembershipBin.class);
			memberships = (ArrayList<Membership>) userBin.getMemberships();


		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return memberships;
	}

	public static List<Membership> readGroupMemberships(int groupID) {
		Membership membership = null;

		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<Membership> memberships = null;

		try{
			InputStream in = new URL("http://"+Env.host_url+":"+Env.host_port+"/GroupNetWeb/" + "readGroupMembership.do" + "?groupID="+groupID)
					.openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			MembershipBin userBin = gson.fromJson(reader,	MembershipBin.class);
			memberships = (ArrayList<Membership>) userBin.getMemberships();


		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return memberships;
	}


	public static int Groupjoin(String userid, String groupid) {

		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet("http://"+Env.host_url+":"+Env.host_port+"/GroupNetWeb/" + "addMembership.do"
				+ "?userID="+userid + "&groupID="+groupid);
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

	public static int Groupleave(String userid, String groupid) {

		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet("http://"+Env.host_url+":"+Env.host_port+"/GroupNetWeb/" + "deleteMembership.do" + "?userid="+userid + "&groupid="+groupid);

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
		MembershipClient.Groupjoin("777" , "80");

		//List<Membership> groupList = MembershipClient.readMyMemberships(123);
		//System.out.println("group size:"+groupList.size());

		//List<Membership> groupList = MembershipClient.readGroupMemberships();
		//System.out.println("group size:"+groupList.size());

		//GroupClient.Groupleave("123", "90");


	}
}