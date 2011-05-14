package com.vandals.maps;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BuildingOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = "BuildingOpenHelper";
    static final int VERSION = 2;
    static final String DBNAME = "buildinglist.db";
    static final String TABLE = "buildinglist";
    static final String _ID = "_id";
    static final String C_XCOORD = "xcoord";
    static final String C_YCOORD = "ycoord";
    static final String C_NAME = "name";
    static final String C_ABBRV = "abbrv";
    static final String C_DISPLAYED = "displayed";
    static final String DB_CREATE_QUERY = 
        "CREATE TABLE " + TABLE + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C_XCOORD + " INTEGER, " + C_YCOORD + " INTEGER, " + 
        C_NAME + " TEXT, " + C_ABBRV + " TEXT, " + C_DISPLAYED + " INTEGER)";
   static final String DB_INSERT_QUERY = "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011202,46727433,'Art & Architecture','A&A',0)";
   static final String DB_INSERT_QUERY2 = "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011368,46727676,'A&A Interior Design','AAID',1)";
 
   Context context;

    
    public BuildingOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"Calling overrided onCreate.");
        try {
            Log.d(TAG, "Creating DB");
            db.execSQL(DB_CREATE_QUERY);
            Log.d(TAG, "Inserting into DB");
            db.execSQL(DB_INSERT_QUERY);
            Log.d(TAG, "Inserting into DB2");
            db.execSQL(DB_INSERT_QUERY2);
        } catch (Exception e) {
            Log.d(TAG, "Error Creating DB");
        } 
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Entering onUgrade for some reason.");
    }
}
