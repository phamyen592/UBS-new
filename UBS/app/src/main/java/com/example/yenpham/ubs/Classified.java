package com.example.yenpham.ubs;

/**
 * Created by ThaoNguyen on 11/13/16.
 */

public abstract class Classified {
    private int classifiedID;
    private String title;
    private int posterID;
    private String contactInfo;
    private String dateTime;

    public Classified() {

    }

    public int getClassifiedID() {
        return classifiedID;
    }

    public void setClassifiedID(int classifiedID) {
        this.classifiedID = classifiedID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosterID() {
        return posterID;
    }

    public void setPosterID(int posterID) {
        this.posterID = posterID;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
