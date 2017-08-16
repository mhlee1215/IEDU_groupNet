package com.iedu.util;

public class MyJsonUtil{

	public static String toString(Object data, String id) {
		return toString(data, id, true);
	}
	
	public static String toString(Object data, String id, boolean hasBrace) {
		String json = "";
		// TODO Auto-generated method stub
		if(hasBrace)
			json += "{";
		json += "\""+id+"\":";
		json += data.toString();
		if(hasBrace)
			json += "}";
		return json;
	}
}