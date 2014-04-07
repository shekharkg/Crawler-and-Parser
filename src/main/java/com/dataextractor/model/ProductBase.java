package com.dataextractor.model;

/**
 * Created by SKG on 05-Apr-14.
 */
public class ProductBase {
    String title;
    String image;
    String id;
    String brand;
    String url;
    int price;


    public ProductBase(String title, String image, String id, String brand, String url, int price){
        this.title = title;
        this.image = image;
        this.id = id;
        this.url = url;
        this.brand = brand;
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBase that = (ProductBase) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

