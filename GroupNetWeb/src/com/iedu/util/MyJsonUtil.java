package com.iedu.util;
import java.util.List;

public class MyJsonUtil{

	public static String toString(Object data, String id) {
		String json = "";
		// TODO Auto-generated method stub
		json += "{";
		json += "\""+id+"\":";
		json += data.toString();
		json += "}";
		return json;
	}
}