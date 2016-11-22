package com.example.yenpham.ubs;

/**
 * Created by ThaoNguyen on 11/14/16.
 */

public class Housing extends Classified {
    private int numBed;
    private int numBath;
    private String location;
    private int zipCode;
    private boolean furnished;
    private String description;
    private String pictures;
    private double price;
    private int size;
    private String availability;

    public Housing() {
    }

    public Housing(int classifiedID, String title, int posterID,String contactInfo,
                   String dateTime,int numBed, int numBath, String location, int zipCode,
                   boolean furnished,String description, String pictures, double price,
                   int size, String availability) {
        this.setClassifiedID(classifiedID);
        this.setTitle(title);
        this.setPosterID(posterID);
        this.setContactInfo(contactInfo);
        this.setDateTime(dateTime);
        this.numBed = numBed;
        this.numBath = numBath;
        this.location = location;
        this.zipCode = zipCode;
        this.furnished = furnished;
        this.description = description;
        this.pictures = pictures;
        this.price = price;
        this.size = size;
        this.availability = availability;
    }

    public int getNumBed() {
        return numBed;
    }

    public void setNumBed(int numBed) {
        this.numBed = numBed;
    }

    public int getNumBath() {
        return numBath;
    }

    public void setNumBath(int numBath) {
        this.numBath = numBath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
