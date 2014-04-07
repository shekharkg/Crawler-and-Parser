package com.dataextractor.controller;

import com.dataextractor.constants.ProductConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SKG on 07-Apr-14.
 */
public class SolrProductDetailsParser {
  public static void main(String[] args) throws IOException {
    int start = 0;
    List<String> idList = new ArrayList<String>();
    List<String> imageList = new ArrayList<String>();

    for(int z = 0; z<411; z++){
      String solrJsonUrl = ProductConstants.solrJsonUrl;
      solrJsonUrl = solrJsonUrl.replace("start=0", "start="+start);
      start += 500;
      ProductJsonString productJsonString = new ProductJsonString();
      String jsonResponse = null;
      try{
        jsonResponse = productJsonString.GET(solrJsonUrl);
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject response = jsonObject.getJSONObject("response");
        JSONArray docs = response.getJSONArray("docs");
        String id = null;
        String image_logo = null;
        for(int i = 0; i < docs.length(); i++){
          id = docs.getJSONObject(i).getString("id");
          try {
            image_logo = docs.getJSONObject(i).getString("image_logo");
          }catch (JSONException e){
            image_logo = "Image Not Found";
          }
          idList.add(id);
          imageList.add(image_logo);
        }
      } catch (JSONException e) {
        e.printStackTrace();
        System.out.println("No Internet" + jsonResponse);
      }
    }

      Writer writer = null;
      try {
        writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("Save.txt"), "utf-8"));
        for(int x=0; x<205300; x++) {
          writer.write("\n ID is :" + idList.get(x).toString() +"\n Image Url is :"+ imageList.get(x).toString() + "\n");
        }
      } catch (IOException ex) {
      } finally {
        try {writer.close();} catch (Exception ex) {}
      }


  }

}
