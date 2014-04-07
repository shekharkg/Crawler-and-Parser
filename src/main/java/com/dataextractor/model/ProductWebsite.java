package com.dataextractor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SKG on 05-Apr-14.
 */
public class ProductWebsite extends ProductBase{

  List<String> imageList = new ArrayList<String>();
  List<String> sizeList = new ArrayList<String>();
  String description, discount;

  public ProductWebsite(String title, String image, String id, String brand, String url, int price) {
    super(title, image, id, brand, url, price);
  }

  public List<String> getImageList() {
    return imageList;
  }

  public void setImageList(List<String> imageList) {
    this.imageList = imageList;
  }

  public List<String> getSizeList() {
    return sizeList;
  }

  public void setSizeList(List<String> sizeList) {
    this.sizeList = sizeList;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDiscount() {
    return discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
  }
}
