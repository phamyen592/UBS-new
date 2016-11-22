package com.example.yenpham.ubs;

/**
 * Created by ThaoNguyen on 11/9/16.
 */

public interface dbInterface {
    int DATABASE_VERSION = 1;// Database Version
    String DATABASE_NAME = "UBSdb";// Database Name

    // Table name
    String TABLE_USER = "USER_TABLE";
    String TABLE_CLUB = "CLUB_TABLE";
    String TABLE_CLUBTOPICS = "CLUBTOPICS_TABLE";
    String TABLE_ADVERTISEMENT = "ADVERTISEMENT_TABLE";
    String TABLE_EVENT = "EVENT_TABLE";
    String TABLE_EVENTCOMMENT = "EVENCOMMENT_TABLE";
    String TABLE_DISCUSSION = "DISCUSSION_TABLE";
    String TABLE_CLUBMEMBER = "CLUBMEMBER_TABLE";
    String TABLE_CLASSIFIED = "CLASSIFIED_TABLE";
    String TABLE_REPLIES = "REPLIES_TABLE";
    String TABLE_SALES = "SALES_TABLE";
    String TABLE_CATEGORIES = "CATEGORIES_TABLE";
    String TABLE_HOUSING = "HOUSING_TABLE";

    // Attribute names
    String KEY_USER_ID = "ID";
    String FIRSTNAME = "FirstName";
    String LASTNAME = "LastName";
    String DOB = "DOB";
    String PHONENO = "PhoneNumber";
    String MAVEMAIL = "MavEmail";
    String MAV_ID = "MavID";
    String GMAIL = "Gmail";
    String MAJOR = "Major";
    String VERCODE = "VerificationCode";
    String KEY_CLUB_ID = "clubID";
    String CREATOR_ID = "creatorID";
    String CLUB_NAME = "clubName";
    String CLUB_TOPIC = "clubTopic";
    String CLUB_DESCRIPTION = "clubDescription";
    String CLUB_PROFILEPIC = "clubProfilePic";
    String CREATED_DATETIME = "createdDateTime";
    String KEY_TOPICS = "clubTopics";
    String KEY_AD_ID = "adID";
    String URL = "url";
    String KEY_EVENT_ID = "EventID";
    String CLUB_ID = "clubID";
    String EVENT_PICS = "eventPics";
    String EVENT_DESCRIPTION = "eventDescription";
    String EVENT_DATETIME = "eventDate&Time";
    String EVENT_LOCATION = "eventLocation";
    String cmID = " commentID";
    String MEMBER_ID = " memberID";
    String EVENT_ID = " eventID";
    String CONTENT = " Content";
    String CMT_DATETIME = " CMT_DATETIME";
    String DISCUSSION_ID = "ID";
    String DISCUSSION_CREATOR = "Creator";
    String USER_ID = "userID";
    String CLASSIFIED_ID = "ClassifiedID";
    String TITLE = "Title";
    String POSTER_ID = "PosterID";
    String CONTACT_INFO = "ContactInfo";
    String DATETIME = "DateTime";
    String REPLY_ID = "ReplyID";
    String PRODUCT_NAME = "productName";
    String PRICE = "Price";
    String CONDITIONS = "Conditions";
    String PICTURE = "Pictures";
    String CATEGORY = "CategoryName";
    String DESCRIPTION = "Description";
    String CATEGORY_NAME = "cateName";
    String NUM_BED = "numBed";
    String NUM_BATH = "numBath";
    String LOCATION = "location";
    String ZIPCODE = "zipcode";
    String FURNISHED = "furnished";
    String SIZE = "size";
    String AVAILABILITY = "availability";
    String CLASSIFIED_TYPE = "classifiedType";

    //Create SQL statements
    String SQL_CREATE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            KEY_USER_ID +    " INTEGER PRIMARY KEY," +
            FIRSTNAME + " TEXT NOT NULL,"+
            LASTNAME +  " TEXT NOT NULL," +
            DOB +       " DATE," +
            MAV_ID +    " TEXT NOT NULL UNIQUE," +
            PHONENO +   " TEXT NOT NULL," +
            MAVEMAIL +  " TEXT NOT NULL UNIQUE," +
            GMAIL +     " TEXT NOT NULL UNIQUE," +
            MAJOR +     " TEXT NOT NULL," +
            VERCODE +   " TEXT NOT NULL UNIQUE," +
            " )";

    String SQL_CREATE_CLUBTOPICS = "CREATE TABLE " + TABLE_CLUBTOPICS +
            " ("+KEY_TOPICS+" TEXT PRIMARY KEY,"+" )";

    String SQL_CREATE_CLUB = "CREATE TABLE " + TABLE_CLUB + " (" +
            KEY_CLUB_ID +   " INTEGER PRIMARY KEY," +
            CREATOR_ID +    " INTEGER," +
            CLUB_NAME +     " TEXT NOT NULL," +
            CLUB_TOPIC +    " DATE NOT NULL," +
        CLUB_DESCRIPTION +  " TEXT," +
        CLUB_PROFILEPIC +   " TEXT," +
        CREATED_DATETIME +  " DATETIME," +
            "FOREIGN KEY " +CLUB_TOPIC+" REFERENCES " +
            TABLE_CLUBTOPICS + "(" + KEY_TOPICS + ") ON UPDATE CASCADE," +
            "FOREIGN KEY " +CREATOR_ID+" REFERENCES " +
            TABLE_USER + "(" + KEY_USER_ID + ") " +
            " )";

    //CLUB MEMBER
    String SQL_CREATE_CLUBMEMBER = "CREATE TABLE " + TABLE_CLUBMEMBER+ " (" +
            CLUB_ID + "INTEGER PRIMARY KEY" +
            MEMBER_ID+ "INTEGER PRIMARY KEY" +
            "FOREIGN KEY " +CLUB_ID+" REFERENCES " +
            TABLE_CLUB + "(" + KEY_CLUB_ID + "), " +
            "FOREIGN KEY " +MEMBER_ID+" REFERENCES " +
            TABLE_USER + "(" + KEY_USER_ID + ") " +
            " )";

    String SQL_CREATE_EVENT = "CREATE TABLE " + TABLE_EVENT + " (" +
            KEY_EVENT_ID +  " INTEGER PRIMARY KEY," +
            CLUB_ID +       " INTEGER," +
            EVENT_PICS +    " TEXT," +
            EVENT_DESCRIPTION +     " DATE NOT NULL," +
            EVENT_DATETIME +    " DATETIME NOT NULL," +
            EVENT_LOCATION  +   " TEXT NOT NULL," +
            "FOREIGN KEY " +CLUB_ID+" REFERENCES " +
            TABLE_CLUB + "(" + KEY_CLUB_ID + "), " +
            " )";

    String SQL_CREATE_EVENT_COMMENT = "CREATE TABLE " + TABLE_EVENTCOMMENT + " (" +
            cmID +         " INTEGER PRIMARY KEY," +
            CLUB_ID +      " INTEGER, " +
            USER_ID +      " INTEGER," +
            EVENT_ID +     " TEXT," +
            CONTENT +      " DATE NOT NULL," +
            CMT_DATETIME + " DATETIME NOT NULL," +
            "FOREIGN KEY " +CLUB_ID+" REFERENCES " +
            TABLE_CLUBMEMBER + "(" + KEY_CLUB_ID + ") ON DELETE CASCADE, " +
            "FOREIGN KEY " +USER_ID+" REFERENCES " +
            TABLE_CLUBMEMBER + "(" + MEMBER_ID + ") ON DELETE CASCADE, " +
            "FOREIGN KEY " +EVENT_ID+" REFERENCES " +
            TABLE_EVENT + "(" + EVENT_ID + ") ON DELETE CASCADE, " +
            " )";

    String SQL_CREATE_ADVERTISEMENT = "CREATE TABLE " + TABLE_ADVERTISEMENT + " (" +
                    KEY_AD_ID + " INTEGER PRIMARY KEY," +
                    URL +       " TEXT" + " )";

    String SQL_CREATE_DISCUSSION = "CREATE TABLE " + TABLE_DISCUSSION + " (" +
            DISCUSSION_ID +     " INTEGER PRIMARY KEY," +
            CLUB_ID +           " INTEGER," +
            DISCUSSION_CREATOR +" INTEGER," +
            CONTENT +           " TEXT NOT NULL," +
            CREATED_DATETIME +  " DATETIME NOT NULL," +
            "FOREIGN KEY " +CLUB_ID+" REFERENCES " +
            TABLE_CLUBMEMBER + "(" + CLUB_ID + "), " +
            "FOREIGN KEY " +DISCUSSION_CREATOR+" REFERENCES " +
            TABLE_CLUBMEMBER + "(" + MEMBER_ID + "), " +
            " )";

    //CLASSIFIED
    String SQL_CREATE_CLASSIFIED = "CREATE TABLE " + TABLE_CLASSIFIED+ " (" +
            CLASSIFIED_ID +     " INTEGER PRIMARY KEY," +
            CLASSIFIED_TYPE +   " TEXT NOT NULL, " +
            TITLE +             " INTEGER NOT NULL," +
            POSTER_ID       +   " INTEGER," +
            CONTACT_INFO +      " TEXT," +
            CREATED_DATETIME +  " DATETIME," +
            "FOREIGN KEY " +POSTER_ID+" REFERENCES " +
            TABLE_USER + "(" + USER_ID + "), " +
            " )";
    //REPLIES
    String SQL_CREATE_REPLIES = "CREATE TABLE " + TABLE_REPLIES+ " (" +
            REPLY_ID +      " INTEGER PRIMARY KEY," +
            DISCUSSION_ID + " INTEGER," +
            USER_ID +       " INTEGER," +
            CONTENT +       " TEXT NOT NULL," +
            CREATED_DATETIME +  " DATETIME NOT NULL," +
            " )";

    String SQL_CREATE_CATEGORIES = "CREATE TABLE " + TABLE_CATEGORIES+ " (" +
            CATEGORY_NAME + " TEXT PRIMARY KEY" +
            " )";
    //SALES
    String SQL_CREATE_SALES = "CREATE TABLE " + TABLE_SALES+ " (" +
            CLASSIFIED_ID +     " INTEGER PRIMARY KEY," +
            PRODUCT_NAME +      " INTEGER NOT NULL," +
            PRICE   +           " REAL NOT NULL," +
            CONDITIONS +        " TEXT," +
            PICTURE +           " TEXT," +
            CATEGORY +          " TEXT NOT NULL," +
            DESCRIPTION +       " TEXT," +
            "FOREIGN KEY " +CATEGORY+" REFERENCES " +
            TABLE_CATEGORIES + "(" + CATEGORY_NAME + "), " +
            "FOREIGN KEY " +CLASSIFIED_ID+" REFERENCES " +
            TABLE_CLASSIFIED + "(" + CLASSIFIED_ID + "), " +
            " )";

    String SQL_CREATE_HOUSING = "CREATE TABLE " + TABLE_HOUSING+ " (" +
            CLASSIFIED_ID + " INTEGER PRIMARY KEY," +
            NUM_BED +       " INTEGER NOT NULL," +
            NUM_BATH +      " INTEGER NOT NULL," +
            LOCATION +      " TEXT NOT NULL," +
            ZIPCODE +       " INTEGER," +
            FURNISHED +     " BOOLEAN NOT NULL" +
            DESCRIPTION +   " TEXT NOT NULL," +
            PICTURE +       " TEXT," +
            PRICE +         " REAL NOT NULL," +
            SIZE +          " REAL," +
            AVAILABILITY +  " DATE NOT NULL," +
            "FOREIGN KEY " +CLASSIFIED_ID+" REFERENCES " +
            TABLE_CLASSIFIED + "(" + CLASSIFIED_ID + "), " +
            " )";

    String[] USER_COLUMNS =  {KEY_USER_ID, FIRSTNAME, LASTNAME, DOB,
                             PHONENO, MAVEMAIL, GMAIL, MAJOR, VERCODE};
    String[] CLUB_COLUMNS =  {KEY_CLUB_ID, CREATOR_ID, CLUB_NAME, CLUB_TOPIC,
                             CLUB_DESCRIPTION, CLUB_PROFILEPIC, CREATED_DATETIME};
    String [] CLUBTOPICS_COLUMN = {KEY_TOPICS};
    String [] TABLE_ADVERTISEMENT_COLUMN = {KEY_AD_ID, URL};
    String [] EVENT_COLUMN = {KEY_EVENT_ID, CLUB_ID, EVENT_PICS,
                            EVENT_DESCRIPTION, EVENT_DATETIME, EVENT_LOCATION};
    String [] EVENTCOMMENT_COLUMNS = {cmID, CLUB_ID, MEMBER_ID, EVENT_ID, CONTENT, CMT_DATETIME};
    String [] DISCUSSION_COLUMNS = {DISCUSSION_ID, CLUB_ID, DISCUSSION_CREATOR, CONTENT, CREATED_DATETIME};
    String [] CLUBMEMBER_COLUMNS = {CLUB_ID, MEMBER_ID};
    String [] CLASSIFIED_COLUMNS = {CLASSIFIED_ID, TITLE, POSTER_ID, CONTACT_INFO, DATETIME};
    String [] REPLIES_COLUMNS = {REPLY_ID, DISCUSSION_ID, MEMBER_ID, CLUB_ID, DATETIME, CONTENT};
    String [] SALES_COLUMNS = {CLASSIFIED_ID, PRODUCT_NAME, PRICE, CONDITIONS, PICTURE, CATEGORY, DESCRIPTION};
    String [] CATEGORIES_COLUMNS = {CATEGORY_NAME};
    String [] HOUSING_COLUMNS = {CLASSIFIED_ID, NUM_BED, NUM_BATH, LOCATION, ZIPCODE, FURNISHED,
                                 DESCRIPTION, PICTURE, PRICE, SIZE, AVAILABILITY};

}
