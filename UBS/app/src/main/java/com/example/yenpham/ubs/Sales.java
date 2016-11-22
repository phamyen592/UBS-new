package com.example.yenpham.ubs;
/**
 * Created by ThaoNguyen on 11/13/16.
 */

public class Sales extends Classified {
    private String productName;
    private double price;
    private String condition;
    private String pictures;
    private String categoryName;
    private String description;

    public Sales() {
    }

    public Sales(int classifiedID, String title, int posterID,
                 String contactInfo, String dateTime, String productName,
                 double price, String condition,
                 String pictures, String categoryName, String description) {
        super.setClassifiedID(classifiedID);
        super.setTitle(title);
        this.setPosterID(posterID);
        this.setContactInfo(contactInfo);
        this.setDateTime(dateTime);
        this.productName = productName;
        this.price = price;
        this.condition = condition;
        this.pictures = pictures;
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
