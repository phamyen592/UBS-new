package com.ubs.thaonguyen.databaseubs;

/**
 * Created by ThaoNguyen on 11/13/16.
 */

public class Club {
    private int clubID;
    private int creatorID;
    private String clubName;
    private String clubTopics;
    private String clubDescription;
    private String clubProfilePics;
    private String clubCreatedDateTime;

    public Club() {

    }

    public Club(int clubID, int creatorID, String clubName, String clubTopics,
                String clubDescription, String clubProfilePics, String clubCreatedDateTime) {
        this.clubID = clubID;
        this.creatorID = creatorID;
        this.clubName = clubName;
        this.clubTopics = clubTopics;
        this.clubDescription = clubDescription;
        this.clubProfilePics = clubProfilePics;
        this.clubCreatedDateTime = clubCreatedDateTime;
    }

    @Override
    public String toString() {
        return "Club{" +
                "clubID=" + clubID +
                ", creatorID=" + creatorID +
                ", clubName='" + clubName + '\'' +
                ", clubTopics='" + clubTopics + '\'' +
                ", clubDescription='" + clubDescription + '\'' +
                ", clubProfilePics='" + clubProfilePics + '\'' +
                ", clubCreatedDateTime='" + clubCreatedDateTime + '\'' +
                '}';
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubTopics() {
        return clubTopics;
    }

    public void setClubTopics(String clubTopics) {
        this.clubTopics = clubTopics;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public String getClubProfilePics() {
        return clubProfilePics;
    }

    public void setClubProfilePics(String clubProfilePics) {
        this.clubProfilePics = clubProfilePics;
    }

    public String getClubCreatedDateTime() {
        return clubCreatedDateTime;
    }

    public void setClubCreatedDateTime(String clubCreatedDateTime) {
        this.clubCreatedDateTime = clubCreatedDateTime;
    }
}
