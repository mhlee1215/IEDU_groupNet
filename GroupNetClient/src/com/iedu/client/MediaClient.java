package com.iedu.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


public class MediaClient {
	
	public static String fileUpload(String url, File file, String text) {
		String responseLine = "";
		System.out.println("11file...MediClient:"+file+", "+text);
		HttpClient httpclient = null;
		try {
			//Enter your host and port number...
			HttpPost post = new HttpPost(url);
			post.addHeader("Content-Type","multipart/mixed; boundary=\"---Content Boundary\"");
			//path of local file and correct for rest of the files also
			
			String message = "This is a multipart post";
			// Create Multipart instance
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			// Add image file to upload
//			builder.addBinaryBody("imgFile", imgFile,
//					ContentType.DEFAULT_BINARY, imgFile.getName());
//			builder.setBoundary("---Content Boundary");
//			
			// Add pdf file to upload
			builder.addBinaryBody("file", file,
					ContentType.DEFAULT_BINARY, file.getName());
			builder.setBoundary("---Content Boundary");
			
			// Add json file to upload
//			builder.addBinaryBody("jsonFile", jsonFile,
//					ContentType.APPLICATION_JSON, jsonFile.getName());
//			builder.setBoundary("---Content Boundary");
//			// Add zip file upload
//			builder.addBinaryBody("zipFile", zipFile,
//					ContentType.create("application/zip"), "SoftwareData.rar");
			builder.setBoundary("---Content Boundary");
			builder.addTextBody("textMessage", message, ContentType.TEXT_PLAIN);
			builder.setBoundary("---Content Boundary");

			httpclient = HttpClientBuilder.create().build();
			HttpEntity entity = builder.build();
			post.setEntity(entity);
			// execute the post request
			HttpResponse response = httpclient.execute(post);
			// Read the response HTML
			
			if (response != null) {
				HttpEntity responseEntity = response.getEntity();
				if (responseEntity != null) {
					// Read the response string if required
					InputStream responseStream = responseEntity.getContent() ;
                    if (responseStream != null){
                        BufferedReader br = new BufferedReader (new InputStreamReader (responseStream)) ;
                        responseLine = br.readLine() ;
//                        String tempResponseString = "" ;
//                        while (responseLine != null){
//                            tempResponseString = tempResponseString + responseLine + System.getProperty("line.separator") ;
//                            responseLine = br.readLine() ;
//                        }
//                        br.close() ;
//                        if (tempResponseString.length() > 0){
//                            System.out.println(tempResponseString);
//                        }
                    }
                    responseStream.close();
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//httpclient.getConnectionManager().shutdown();
		}
		return responseLine;
	}	
	
	public static void main(String[] args) throws Exception {
//	    HttpClient httpclient = new DefaultHttpClient();
//	    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

	    //HttpPost httppost = new HttpPost("http://localhost:8080/GroupNetWeb/mediaUpload.do");
	    File file = new File("/home/mhlee/test.pdf");
	    System.out.println(file);
	    String id = MediaClient.fileUpload("http://"+Env.host_url+":8080/GroupNetWeb/mediaUpload2.do", file, "text...");
	    System.out.println("id="+id);
//	    MultipartEntity mpEntity = new MultipartEntity();
//	    ContentBody cbFile = new FileBody(file, "multipart/form-data");
//	    mpEntity.addPart("file", cbFile);
//
//
//	    httppost.setEntity(mpEntity);
//	    System.out.println("executing request " + httppost.getRequestLine());
//	    HttpResponse response = httpclient.execute(httppost);
//	    HttpEntity resEntity = response.getEntity();
//
//	    System.out.println(response.getStatusLine());
//	    if (resEntity != null) {
//	      System.out.println(EntityUtils.toString(resEntity));
//	    }
//	    if (resEntity != null) {
//	      resEntity.consumeContent();
//	    }
//
//	    httpclient.getConnectionManager().shutdown();
	  }

}
