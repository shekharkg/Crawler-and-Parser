package com.dataextractor.controller;

import com.dataextractor.constants.ProductConstants;
import com.dataextractor.model.ProductBase;
import com.dataextractor.model.ProductWebsite;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

import java.util.*;

/**
 * Created by SKG on 05-Apr-14.
 */
public class ProductDetailsParser {


  public static void example() {

    String jsonUrl = ProductConstants.jsonUrl;
    int start = 205000;
    ProductJsonString productJsonString = new ProductJsonString();
    for(int x=0; x<410; x++){

      String jsonResponse = productJsonString.POST(jsonUrl, start);
      start += 500;
      ProductBase productBase;
      Set<ProductWebsite> productSet = new HashSet<ProductWebsite>();

      JSONObject json = null;
      Document doc = null;
      try {
        json = new JSONObject(jsonResponse);
        JSONObject response1 = json.getJSONObject("response1");
        JSONArray products = response1.getJSONArray("products");

        for (int i = 0; i < products.length(); i++) {
            String title = null;
            String image = null;
          try{
            image = products.getJSONObject(i).getString("search_image");
            title = products.getJSONObject(i).getString("product");
          } catch (JSONException e){
            e.printStackTrace();
          }
            int price = products.getJSONObject(i).getInt("price");
            String brand = products.getJSONObject(i).getString("brands_filter_facet");
            String url = products.getJSONObject(i).getString("dre_landing_page_url");
            String id = products.getJSONObject(i).getString("id");
            id = id.replace("0_style_","");
            productSet.add(new ProductWebsite(title, image, id, brand, url,price));
         }
        } catch (JSONException e) {
              e.printStackTrace();
              System.out.println("No Internet" + jsonResponse);
              start -=500;
              continue;
        }
      /*
        for(ProductWebsite product: productSet){
          String jsoupUrl = ProductConstants.jsoupUrl + product.getUrl();
          try {
            doc = Jsoup.connect(jsoupUrl)
                .userAgent("Mozilla")
                .timeout(100000)
                .ignoreHttpErrors(true)
                .get();
          } catch (IOException e) {
            System.out.println("List of Jsoup not Parsed :"+ product.getId());
            continue;
          }
            //doc = Jsoup.connect(jsoupUrl).userAgent("Mozilla").get();

            Elements descriptionJsoup = doc.select("[id=product-description]");
            Elements imageArray = doc.select("[width=48]");
            Elements avail_size = doc.select("[data-availableinwarehouse]");
            Elements discountJsoup = doc.select("[class=discount]");

            String description = descriptionJsoup.text();
            List<String> imageList = new ArrayList<String>();
            List<String> sizeList = new ArrayList<String>();
            for(Element srcImg : imageArray){
              imageList.add(srcImg.attr("abs:src"));
            }
            for(Element srcSize : avail_size){
                sizeList.add(srcSize.text());
            }
            String discount = discountJsoup.text();

            product.setDescription(description);
            product.setImageList(imageList);
            product.setSizeList(sizeList);
            product.setDiscount(discount);

        }
        */

        Collection<SolrInputDocument> documentCollection = new ArrayList<SolrInputDocument>();
        String urlString = "http://localhost:8983/solr/collection1";
        SolrServer solr = new HttpSolrServer(urlString);
        for (ProductWebsite productValue : productSet){


            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", productValue.getId());
            document.addField("title", productValue.getTitle());
            document.addField("price", productValue.getPrice());
           // document.addField("description",productValue.getDescription());
            document.addField("url", productValue.getUrl());
            document.addField("image_logo", productValue.getImage());
          //  document.addField("discount", productValue.getDiscount());
            document.addField("brand", productValue.getBrand());
          //  document.addField("image_list", productValue.getImageList());
          //  document.addField("size_list", productValue.getSizeList());*/
            documentCollection.add(document);


             /* System.out.println("Product ID :"+productValue.getId());
              System.out.println("Product Title :"+productValue.getTitle());
              System.out.println("Product Price :"+productValue.getPrice());
              System.out.println("Product Description :"+productValue.getDescription());
              System.out.println("Product Url :"+productValue.getUrl());

              System.out.println("Product String Image :"+productValue.getImage());
              System.out.println("Product Discount :"+productValue.getDiscount());
              System.out.println("Product ImageList :"+productValue.getImageList());
              System.out.println("Product SizeList :"+productValue.getSizeList());
              System.out.println("Product Brand :"+productValue.getBrand());
              System.out.println("===================================================================");*/
            }
     /* try {
        UpdateResponse response = solr.add(documentCollection);
        //Delete query from Solr server
        // solr.deleteByQuery( "*:*" );

        // Remember to commit your changes!
        solr.commit();
      } catch (SolrServerException e) {
        e.printStackTrace();
      }*/

          }

  }

}
