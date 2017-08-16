package groupnet.iedu.com.groupnetandroid.Connections;

import android.content.Context;
import android.os.AsyncTask;

import com.iedu.client.MediaClient;
import com.iedu.client.UserClient;
import com.iedu.domain.Signal;
import com.iedu.domain.User;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import groupnet.iedu.com.groupnetandroid.LoginPageActivity;
import groupnet.iedu.com.groupnetandroid.samples.imageLoad.ImageLoadActivity;

/**
 * Created by mhlee on 7/21/17.
 */

public class ConnectionFileUpload extends AsyncTask {

    private ImageLoadActivity context;
    int returnCode;

    public ConnectionFileUpload(Context context){
        this.context = (ImageLoadActivity)context;
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {

        try {

            File file = (File)arg0[0];
            System.out.println(">>>>>"+file);
            MediaClient.fileUpload("http://52.34.169.106:8080/GroupNetWeb/mediaUpload2.do", file, "text...");

//            CloseableHttpClient httpclient = null;
//            httpclient = HttpClientBuilder.create().build();
//            System.out.println("httpclient: "+httpclient);
//            httpclient.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    public static void fileUpload(String url, File file, String text) {
//        System.out.println("11file...MediClient:"+file+", "+text);
//        HttpClient httpclient = null;
//        try {
//            //Enter your host and port number...
//            HttpPost post = new HttpPost(url);
//            post.addHeader("Content-Type","multipart/mixed; boundary=\"---Content Boundary\"");
//            //path of local file and correct for rest of the files also
//
//            String message = "This is a multipart post";
//            // Create Multipart instance
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            // Add image file to upload
////			builder.addBinaryBody("imgFile", imgFile,
////					ContentType.DEFAULT_BINARY, imgFile.getName());
////			builder.setBoundary("---Content Boundary");
////
//            // Add pdf file to upload
//            builder.addBinaryBody("file", file,
//                    ContentType.DEFAULT_BINARY, file.getName());
//            builder.setBoundary("---Content Boundary");
//
//            // Add json file to upload
////			builder.addBinaryBody("jsonFile", jsonFile,
////					ContentType.APPLICATION_JSON, jsonFile.getName());
////			builder.setBoundary("---Content Boundary");
////			// Add zip file upload
////			builder.addBinaryBody("zipFile", zipFile,
////					ContentType.create("application/zip"), "SoftwareData.rar");
//            builder.setBoundary("---Content Boundary");
//            builder.addTextBody("textMessage", message, ContentType.TEXT_PLAIN);
//            builder.setBoundary("---Content Boundary");
//
//            httpclient = HttpClientBuilder.create().build();
//            HttpEntity entity = builder.build();
//            post.setEntity(entity);
//            // execute the post request
//            HttpResponse response = httpclient.execute(post);
//            // Read the response HTML
//            if (response != null) {
//                HttpEntity responseEntity = response.getEntity();
//                if (responseEntity != null) {
//                    // Read the response string if required
//                    InputStream responseStream = responseEntity.getContent() ;
//                    if (responseStream != null){
//                        BufferedReader br = new BufferedReader (new InputStreamReader(responseStream)) ;
//                        String responseLine = br.readLine() ;
//                        String tempResponseString = "" ;
//                        while (responseLine != null){
//                            tempResponseString = tempResponseString + responseLine + System.getProperty("line.separator") ;
//                            responseLine = br.readLine() ;
//                        }
//                        br.close() ;
//                        if (tempResponseString.length() > 0){
//                            System.out.println(tempResponseString);
//                        }
//                    }
//                    responseStream.close();
//                }
//            }
//        } catch (FileNotFoundException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (ClientProtocolException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            //httpclient.getConnectionManager().shutdown();
//        }
//        return;
//    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        context.uploadFinished();

    }
}