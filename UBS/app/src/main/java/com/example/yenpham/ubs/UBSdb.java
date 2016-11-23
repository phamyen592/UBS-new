package com.example.yenpham.ubs;
<<<<<<< HEAD

=======
>>>>>>> 504ee9c006b54802de2a7b943b5393cd518a7b9d
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.*;

import java.util.ArrayList;

/*
 * Created by ThaoNguyen on 11/9/16.
 */

public class UBSdb extends SQLiteOpenHelper implements dbInterface {
    /* Constructor */
    public UBSdb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_CLUBTOPICS);
        db.execSQL(SQL_CREATE_CLUB);
        db.execSQL(SQL_CREATE_CLUBMEMBER);
        db.execSQL(SQL_CREATE_EVENT);
        db.execSQL(SQL_CREATE_EVENT_COMMENT);
        db.execSQL(SQL_CREATE_DISCUSSION);
        db.execSQL(SQL_CREATE_REPLIES);
        db.execSQL(SQL_CREATE_CATEGORIES);
        db.execSQL(SQL_CREATE_CLASSIFIED);
        db.execSQL(SQL_CREATE_SALES);
        db.execSQL(SQL_CREATE_HOUSING);
        db.execSQL(SQL_CREATE_ADVERTISEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL("DROP TABLE IF EXISTS *");
        // create fresh tables
        this.onCreate(db);
    }

    void addUser(User user){
        Log.d("addUser", user.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getID());
        values.put(FIRSTNAME, user.getFirstName());
        values.put(LASTNAME, user.getLastName());
        values.put(DOB, user.getDOB());
        values.put(MAV_ID, user.getMavID());
        values.put(PHONENO, user.getPhoneNumber());
        values.put(MAVEMAIL, user.getMavEmail());
        values.put(GMAIL, user.getGmail());
        values.put(MAJOR, user.getMajor());
        values.put(VERCODE, user.getVerificationCode());
        // 3. insert
        db.insert(TABLE_USER, null, values);
        // 4. close
        db.close();
    }
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        // 2. build query
        Cursor cursor = db.query(TABLE_USER,
                        USER_COLUMNS, // b. column names
                        " ID = ?", // c. selections
                        new String[] {String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        User user = new User();
        if (cursor != null) {
            cursor.moveToFirst();
            // 4. build user object
            int stringIndex = 0;
            user.setID(Integer.parseInt(cursor.getString(stringIndex)));
            user.setFirstName(cursor.getString(++stringIndex));
            user.setLastName(cursor.getString(++stringIndex));
            user.setDOB(cursor.getString(++stringIndex));
            user.setMavID(Integer.parseInt(cursor.getString(++stringIndex)));
            user.setPhoneNumber(cursor.getString(++stringIndex));
            user.setMavEmail(cursor.getString(++stringIndex));
            user.setGmail(cursor.getString(++stringIndex));
            user.setMajor(cursor.getString(++stringIndex));
            user.setVerificationCode(cursor.getString(++stringIndex));
            cursor.close();
        }
        //log
        Log.d("getUser("+id+")", user.toString());
        db.close();
        // 5. return user
        return user;
    }
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_USER;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build user and add it to list
            User user;
            if (cursor.moveToFirst()) {
                do {
                    user = new User();
                    int stringIndex = 0;
                    user.setID(Integer.parseInt(cursor.getString(stringIndex)));
                    user.setFirstName(cursor.getString(++stringIndex));
                    user.setLastName(cursor.getString(++stringIndex));
                    user.setDOB(cursor.getString(++stringIndex));
                    user.setMavID(Integer.parseInt(cursor.getString(++stringIndex)));
                    user.setPhoneNumber(cursor.getString(++stringIndex));
                    user.setMavEmail(cursor.getString(++stringIndex));
                    user.setGmail(cursor.getString(++stringIndex));
                    user.setMajor(cursor.getString(++stringIndex));
                    user.setVerificationCode(cursor.getString(++stringIndex));
                    // Add user to userList
                    userList.add(user);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllUsers()", userList.toString());
        // return users
        return userList;
    }
    public int updateUser(User user) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getID());
        values.put(FIRSTNAME, user.getFirstName());
        values.put(LASTNAME, user.getLastName());
        values.put(DOB, user.getDOB());
        values.put(MAV_ID, user.getID());
        values.put(PHONENO, user.getPhoneNumber());
        values.put(MAVEMAIL, user.getMavEmail());
        values.put(GMAIL, user.getGmail());
        values.put(MAJOR, user.getMajor());
        values.put(VERCODE, user.getVerificationCode());
        // 3. updating row
        int i = db.update(TABLE_USER, //table
                values, // column/value
                KEY_USER_ID+" = ?", // selections
                new String[] { String.valueOf(user.getID()) }); //selection args
        // 4. close
        db.close();
        return i;
    }
    public void deleteUser(User user) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(TABLE_USER, //table name
                KEY_USER_ID+" = ?",  // selections
                new String[] { String.valueOf(user.getID()) }); //selections args
        // 3. close
        db.close();
        //log
        Log.d("deleteUser", user.toString());
    }
    public boolean GmailExist(String gmail){
        String query = "SELECT  * FROM " + TABLE_USER+ "WHERE" + GMAIL + " =" + gmail;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
            return false;
        }
    }
    public boolean MavMailExist(String mavMail){
        String query = "SELECT  * FROM " + TABLE_USER+ "WHERE" + MAVEMAIL + " =" + mavMail;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
            return false;
        }
    }

    public void addClubTopics(String clubTopics){
        Log.d("addClubTopics", clubTopics);//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TOPICS, clubTopics);
        // 3. insert
        db.insert(TABLE_CLUBTOPICS, null, values);
        // 4. close
        db.close();
    }
    public String getClubTopics(String clubTopic){
        SQLiteDatabase db = this.getReadableDatabase();
        // 2. build query
        Cursor cursor = db.query(TABLE_CLUBTOPICS,
                CLUBTOPICS_COLUMN, // b. column names
                " clubTopic = ?", // c. selections
                new String[] {String.valueOf(clubTopic)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        // 3. if we got results get the first one
        String selectedTopic = "";
        if (cursor != null) {
            cursor.moveToFirst();
            // 4. build user object
            int stringIndex = 0;
            selectedTopic = cursor.getString(0);
            cursor.close();
        }
        //log
        Log.d("getClubTopics("+clubTopic+")", selectedTopic);
        db.close();
        // 5. return user
        return selectedTopic;

    }
    public ArrayList<String> getAllClubTopics(){
        ArrayList<String> clubTopics = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CLUBTOPICS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            if (cursor.moveToFirst()) {
                do {
                    String topic;
                    topic = cursor.getString(0);
                    cursor.close();
                    // Add club to clubList
                    clubTopics.add(topic);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllClubTopics()", clubTopics.toString());
        // return clubList
        return clubTopics;
    }
    public void updateClubTopics(){ //TO BE IMPLEMENTED
    }

    public void addClub(Club club){
        Log.d("addClub", club.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, club.getClubID());
        values.put(FIRSTNAME, club.getCreatorID());
        values.put(LASTNAME, club.getClubName());
        values.put(DOB, club.getClubTopics());
        values.put(MAV_ID, club.getClubDescription());
        values.put(PHONENO, club.getClubProfilePics());
        values.put(MAVEMAIL, club.getClubCreatedDateTime());
        // 3. insert
        db.insert(TABLE_CLUB, null, values);
        // 4. close
        db.close();
    }
    public Club getClub(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        // 2. build query
        Cursor cursor = db.query(TABLE_CLUB,
                CLUB_COLUMNS, // b. column names
                " ID = ?", // c. selections
                new String[] {String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        // 3. if we got results get the first one
        Club club = new Club();
        if (cursor != null) {
            cursor.moveToFirst();
            // 4. build user object
            int stringIndex = 0;
            club.setClubID(Integer.parseInt(cursor.getString(stringIndex)));
            club.setCreatorID(Integer.parseInt(cursor.getString(stringIndex)));
            club.setClubName(cursor.getString(++stringIndex));
            club.setClubTopics(cursor.getString(++stringIndex));
            club.setClubDescription(cursor.getString(++stringIndex));
            club.setClubProfilePics(cursor.getString(++stringIndex));
            club.setClubCreatedDateTime(cursor.getString(++stringIndex));
            cursor.close();
        }
        //log
        Log.d("getClub("+id+")", club.toString());
        db.close();
        // 5. return user
        return club;

    }
    public ArrayList<Club> getAllClubs() {
        ArrayList<Club> clubList = new ArrayList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CLUB;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            Club club;
            if (cursor.moveToFirst()) {
                do {
                    club = new Club();
                    int stringIndex = 0;
                    club.setClubID(Integer.parseInt(cursor.getString(stringIndex)));
                    club.setCreatorID(Integer.parseInt(cursor.getString(++stringIndex)));
                    club.setClubName(cursor.getString(++stringIndex));
                    club.setClubTopics(cursor.getString(++stringIndex));
                    club.setClubDescription(cursor.getString(++stringIndex));
                    club.setClubProfilePics(cursor.getString(++stringIndex));
                    club.setClubCreatedDateTime(cursor.getString(++stringIndex));
                    cursor.close();
                    // Add club to clubList
                    clubList.add(club);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllClubs()", clubList.toString());
        // return clubList
        return clubList;
    }
    public void updateClub(){ //TO BE IMPLEMENTED
    }

    public void addClubMember(int clubID, int memberID){
        Log.d("addClubMember", "Member: " + memberID + " into club " + clubID);//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CLUB_ID, clubID);
        values.put(MEMBER_ID, memberID);
        // 3. insert
        db.insert(TABLE_CLUBMEMBER, null, values);
        // 4. close
        db.close();
    }
    public ArrayList<User> getAllClubMember(int clubID){
        ArrayList<User> userList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CLUBMEMBER +
                        "WHERE " + CLUB_ID + " = " + clubID ;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build user and add it to list
            if (cursor.moveToFirst()) {
                do {
                    int userID = Integer.parseInt(cursor.getString(0));
                    userList.add(getUser(userID));
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getClubMembers()", userList.toString());
        // return users
        return userList;
    }
    public boolean isMember(int clubID, int userID){
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CLUBMEMBER +
                "WHERE " + CLUB_ID + " = " + clubID + "AND" + USER_ID + " =" + userID;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build user and add it to list
            if (cursor.moveToFirst()) {
                cursor.close();
                return true;
            }
            else return false;
        }
    }
    public void updateClubMember(){//TO BE IMPLEMENTED
    }

    public void addEvent(UBSEvent event){
        Log.d("addEvent", event.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_ID, event.getEventID());
        values.put(CLUB_ID, event.getClubID());
        values.put(EVENT_PICS, event.getEventPics());
        values.put(EVENT_DESCRIPTION, event.getEventDescription());
        values.put(EVENT_DATETIME, event.getDateTime());
        values.put(EVENT_LOCATION, event.getEventLocation());

        // 3. insert
        db.insert(TABLE_EVENT, null, values);
        // 4. close
        db.close();

    }
    public ArrayList<UBSEvent> getAllEvent(int clubID){
        ArrayList<UBSEvent> eventList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_EVENT +
                "WHERE " + CLUB_ID + " =" + clubID;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            UBSEvent event = new UBSEvent();
            if (cursor.moveToFirst()) {
                do {
                    int stringIndex = 0;
                    event.setEventID(Integer.parseInt(cursor.getString(stringIndex)));
                    event.setClubID(Integer.parseInt(cursor.getString(++stringIndex)));
                    event.setEventPics(cursor.getString(++stringIndex));
                    event.setEventDescription(cursor.getString(++stringIndex));
                    event.setDateTime(cursor.getString(++stringIndex));
                    event.setEventLocation(cursor.getString(++stringIndex));
                    cursor.close();
                    // Add club to clubList
                    eventList.add(event);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllEVent()", eventList.toString());
        // return clubList
        return eventList;
    }
    public void updateEvent(){
    }

    public void addEventComment(UBScomment cmt){
        Log.d("addEventComment", cmt.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(cmID, cmt.getEventID());
        values.put(CLUB_ID, cmt.getClubID());
        values.put(USER_ID, cmt.getUserID());
        values.put(EVENT_ID, cmt.getEventID());
        values.put(CONTENT, cmt.getContents());
        values.put(CMT_DATETIME, cmt.getDateTime());
        // 3. insert
        db.insert(TABLE_EVENTCOMMENT, null, values);
        // 4. close
        db.close();
    }
    public ArrayList<UBScomment> getAllEventComment(UBSEvent event){
        ArrayList<UBScomment> cmtList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_EVENTCOMMENT +
                "WHERE " + CLUB_ID + " =" + event.getClubID() +
                "AND" + EVENT_ID + " ="  + event.getEventID() ;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            UBScomment cmt = new UBScomment();
            if (cursor.moveToFirst()) {
                do {
                    int stringIndex = 0;
                    cmt.setCmID(Integer.parseInt(cursor.getString(stringIndex)));
                    cmt.setClubID(Integer.parseInt(cursor.getString(++stringIndex)));
                    cmt.setUserID(Integer.parseInt(cursor.getString(++stringIndex)));
                    cmt.setEventID(Integer.parseInt(cursor.getString(++stringIndex)));
                    cmt.setContents(cursor.getString(++stringIndex));
                    cmt.setDateTime(cursor.getString(++stringIndex));
                    cursor.close();
                    // Add club to clubList
                    cmtList.add(cmt);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllEVent()", cmtList.toString());
        // return clubList
        return cmtList;
    }
    public void updateEventComment(){// TO BE IMPLEMENTED
    }

    public void addAdvertisement(int adID, String url){
        Log.d("addAdvertisement", url);//for logging
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_AD_ID, adID);
        values.put(URL, url);
        // 3. insert
        db.insert(TABLE_ADVERTISEMENT, null, values);
        // 4. close
        db.close();
    }
    public ArrayList <String> getAdvertisement(){
        ArrayList<String> adsList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_ADVERTISEMENT;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            if (cursor.moveToFirst()) {
                do {
                    String url;
                    url = cursor.getString(1);
                    cursor.close();
                    // Add club to clubList
                    adsList.add(url);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllAds()", adsList.toString());
        // return clubList
        return adsList;
    }

    public void addDiscussion(Discussion discussion){
        Log.d("addDiscussion", discussion.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(DISCUSSION_ID, discussion.getDiscussionID());
        values.put(CLUB_ID, discussion.getClubID());
        values.put(DISCUSSION_CREATOR, discussion.getDisCreator());
        values.put(CONTENT, discussion.getContents());
        values.put(DATETIME, discussion.getDateTime());
        // 3. insert
        db.insert(TABLE_DISCUSSION, null, values);
        // 4. close
        db.close();
    }
    public ArrayList<Discussion> getAllDiscussions(int clubID){
        ArrayList<Discussion> discussionList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_DISCUSSION +
                "WHERE " + CLUB_ID + " =" + clubID;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            Discussion discussion = new Discussion();
            if (cursor.moveToFirst()) {
                do {
                    int stringIndex = 0;
                    discussion.setDiscussionID(Integer.parseInt(cursor.getString(stringIndex)));
                    discussion.setClubID(Integer.parseInt(cursor.getString(++stringIndex)));
                    discussion.setDisCreator(Integer.parseInt(cursor.getString(++stringIndex)));
                    discussion.setContents(cursor.getString(++stringIndex));
                    discussion.setDateTime(cursor.getString(++stringIndex));
                    cursor.close();
                    // Add club to clubList
                    discussionList.add(discussion);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllDiscussions()", discussionList.toString());
        // return discussionList
        return discussionList;
    }
    public void updateDiscussion(){//TO BE IMPLEMENTED
    }

    public void addReply(Reply rep){
        Log.d("addReply", rep.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(REPLY_ID, rep.getRepID());
        values.put(DISCUSSION_ID, rep.getDisID());
        values.put(USER_ID, rep.getUserID());
        values.put(CLUB_ID, rep.getClubID());
        values.put(DATETIME, rep.getDateTime());
        values.put(CONTENT, rep.getDateTime());
        // 3. insert
        db.insert(TABLE_REPLIES, null, values);
        // 4. close
        db.close();

    }
    public ArrayList<Reply> getAllReply(Discussion discussion){
        ArrayList<Reply> replyList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT * FROM " + TABLE_REPLIES +
                "WHERE " + CLUB_ID + " =" + discussion.getClubID() +
                "AND" + DISCUSSION_ID + " =" + discussion.getDiscussionID();

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            Reply reply = new Reply();
            if (cursor.moveToFirst()) {
                do {
                    int stringIndex = 0;
                    reply.setRepID(Integer.parseInt(cursor.getString(stringIndex)));
                    reply.setDisID(Integer.parseInt(cursor.getString(++stringIndex)));
                    reply.setClubID(Integer.parseInt(cursor.getString(++stringIndex)));
                    reply.setDateTime(cursor.getString(++stringIndex));
                    reply.setContent(cursor.getString(++stringIndex));
                    cursor.close();
                    // Add club to clubList
                    replyList.add(reply);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllReply()", replyList.toString());
        // return discussionList
        return replyList;
    }
    public void updateReply(){// TO BE IMPLEMENTED
    }

    public void addCategory(String category){
        Log.d("addCategory", category);//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CATEGORY_NAME, category);
        // 3. insert
        db.insert(TABLE_CATEGORIES, null, values);
        // 4. close
        db.close();

    }
    public ArrayList <String> getAllCategory(){
        ArrayList<String> categoryList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CATEGORIES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            if (cursor.moveToFirst()) {
                do {
                    String cat;
                    cat = cursor.getString(1);
                    cursor.close();

                    categoryList.add(cat);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllCategories()", categoryList.toString());
        return categoryList;

    }
    public void updateCategory(){ // TO BE IMPLEMENTED
    }

    public void addSales(Sales item){
        Log.d("addSales", item.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues sales_values = new ContentValues();
        ContentValues Classified_values = new ContentValues();

        Classified_values.put(CLASSIFIED_ID, item.getClassifiedID());
        Classified_values.put(TITLE, item.getTitle());
        Classified_values.put(POSTER_ID, item.getPosterID());
        Classified_values.put(CONTACT_INFO, item.getContactInfo());
        Classified_values.put(DATETIME, item.getDateTime());

        sales_values.put(CLASSIFIED_ID, item.getClassifiedID());
        sales_values.put(PRODUCT_NAME, item.getProductName());
        sales_values.put(PRICE, item.getPrice());
        sales_values.put(CONDITIONS, item.getCondition());
        sales_values.put(PICTURE, item.getPictures());
        sales_values.put(CATEGORY_NAME, item.getCategoryName());
        sales_values.put(DESCRIPTION, item.getDescription());

        // 3. insert
        db.insert(TABLE_SALES, null, sales_values);
        db.insert(TABLE_CLASSIFIED, null, Classified_values);
        // 4. close
        db.close();
    }
    public ArrayList <Sales> getAllSales(){
        ArrayList<Sales> SalesList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SALES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            Sales sale = new Sales();
            if (cursor.moveToFirst()) {
                do {
                    int stringIndex = 0;
                    sale.setClassifiedID(Integer.parseInt(cursor.getString(stringIndex)));
                    sale.setProductName(cursor.getString(++stringIndex));
                    sale.setPrice(Double.parseDouble(cursor.getString(++stringIndex)));
                    sale.setCondition(cursor.getString(++stringIndex));
                    sale.setPictures(cursor.getString(++stringIndex));
                    sale.setCategoryName(cursor.getString(++stringIndex));
                    sale.setDescription(cursor.getString(++stringIndex));
                    cursor.close();
                    // Add club to clubList
                    SalesList.add(sale);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllSales()", SalesList.toString());
        // return discussionList
        return SalesList;
    }
    public void updateSales(){


    }

    public void addHousing(Housing item){
        Log.d("addSales", item.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues Classified_values = new ContentValues();
        ContentValues Housing_values = new ContentValues();
        Classified_values.put(CLASSIFIED_ID, item.getClassifiedID());
        Classified_values.put(TITLE, item.getTitle());
        Classified_values.put(POSTER_ID, item.getPosterID());
        Classified_values.put(CONTACT_INFO, item.getContactInfo());
        Classified_values.put(DATETIME, item.getDateTime());

        Housing_values.put(CLASSIFIED_ID, item.getClassifiedID());
        Housing_values.put(NUM_BED, item.getNumBed());
        Housing_values.put(NUM_BATH, item.getNumBath());
        Housing_values.put(LOCATION, item.getLocation());
        Housing_values.put(ZIPCODE, item.getZipCode());
        Housing_values.put(FURNISHED, item.isFurnished());
        Housing_values.put(DESCRIPTION, item.getDescription());
        Housing_values.put(PICTURE, item.getPictures());
        Housing_values.put(PRICE, item.getPrice());
        Housing_values.put(SIZE, item.getSize());
        Housing_values.put(AVAILABILITY, item.getAvailability());

        // 3. insert
        db.insert(TABLE_CLASSIFIED, null, Classified_values);
        db.insert(TABLE_HOUSING, null, Housing_values);
        // 4. close
        db.close();

    }
    public ArrayList <Housing> getHousing(){
        ArrayList<Housing> housingList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CLASSIFIED + "NATURAL JOIN" + TABLE_HOUSING;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            Housing housing = new Housing();
            if (cursor.moveToFirst()) {
                do {
                    int stringIndex = 0;
                    housing.setClassifiedID(Integer.parseInt(cursor.getString(stringIndex)));
                    housing.setTitle(cursor.getString(++stringIndex));
                    housing.setPosterID(Integer.parseInt(cursor.getString(++stringIndex)));
                    housing.setDateTime(cursor.getString(++stringIndex));
                    housing.setNumBed(Integer.parseInt(cursor.getString(++stringIndex)));
                    housing.setNumBath(Integer.parseInt(cursor.getString(++stringIndex)));
                    housing.setLocation(cursor.getString(++stringIndex));
                    cursor.close();
                    housingList.add(housing);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
            Log.d("getAllHousing()", housingList.toString());
            return housingList;
    }
    public void updateHousing(){

    }
}
