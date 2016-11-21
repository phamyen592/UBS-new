package com.example.yenpham.ubs;
/**
 * Created by ThaoNguyen on 11/13/16.
 */

public class UBScomment {
    private int cmID;
    private int clubID;
    private int userID;
    private int eventID;
    private String contents;
    private String dateTime;

    public UBScomment() {
    }

    public UBScomment(int cmID, int clubID, int userID, int eventID,
                      String contents, String dateTime) {
        this.cmID = cmID;
        this.clubID = clubID;
        this.userID = userID;
        this.eventID = eventID;
        this.contents = contents;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "UBScomment{" +
                "cmID=" + cmID +
                ", clubID=" + clubID +
                ", userID=" + userID +
                ", eventID=" + eventID +
                ", contents='" + contents + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }

    public int getCmID() {
        return cmID;
    }

    public void setCmID(int cmID) {
        this.cmID = cmID;
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
