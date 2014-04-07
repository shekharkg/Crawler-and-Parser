package com.dataextractor.controller;


import com.dataextractor.constants.ProductConstants;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by SKG on 30-Mar-14.
 */
public class ProductJsonString {
    //JSONObject jsonObject ;
    public String POST(String url, int start){
      InputStream inputStream = null;
      String result = "";

      try {

        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        // 2. make POST request to the given URL
        HttpPost httpPost = new HttpPost(url);

        String json = "";



        // 4. convert JSONObject to JSON to String

        json = ProductConstants.jsonPost;
        json = json.replace(":0", ": "+start);

        // ** Alternative way to convert Person object to JSON string usin Jackson Lib
        // ObjectMapper mapper = new ObjectMapper();
        // json = mapper.writeValueAsString(person);

        // 5. set json to StringEntity
        StringEntity se = new StringEntity(json);

        // 6. set httpPost Entity
        httpPost.setEntity(se);

        // 7. Set some headers to inform server about the type of the content
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        // 8. Execute POST request to the given URL
        HttpResponse httpResponse = httpclient.execute(httpPost);
        System.out.println("httpResponse" + httpResponse);
        // 9. receive response as inputStream
        inputStream = httpResponse.getEntity().getContent();

        // 10. convert inputstream to string
        if(inputStream != null)
          result = convertInputStreamToString(inputStream);
        else
          result = "Did not work!";

      } catch (Exception e) {
        String msg = (e.getMessage()==null)?"Login failed!":e.getMessage();
        System.out.println("Login Error1 :" + url + msg);
      }

      // 11. return result
      return result;
    }


   public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
          e.printStackTrace();
        }

        return result;
    }

  private static String convertInputStreamToString(InputStream inputStream) throws IOException {
    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
    String line = "";
    String result = "";
    while((line = bufferedReader.readLine()) != null)
      result += line;

    inputStream.close();
    return result;

  }

}
