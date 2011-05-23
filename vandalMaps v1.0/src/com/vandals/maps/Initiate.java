    package com.vandals.maps;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Initiate extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    private static final String TAG = "Initiate";
    BuildingOpenHelper opener;
    SQLiteDatabase db;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initiate);
        Log.d(TAG, "Starting Initiate.");
            
           
        
        BuildingOpenHelper opener = new BuildingOpenHelper(this);
        
        db = opener.getWritableDatabase();
        /*
        ContentValues values = new ContentValues();
        
        Log.d(TAG, "Setting up values to put to database.");
        values.clear();
        values.put(BuildingOpenHelper.C_XCOORD,-117011202);
        values.put(BuildingOpenHelper.C_YCOORD,46727433);
        values.put(BuildingOpenHelper.C_NAME,"Art & Architecture");
        values.put(BuildingOpenHelper.C_ABBRV,"A&A");
        values.put(BuildingOpenHelper.C_DISPLAYED,0);        
        try {
            Log.d(TAG, "Trying to push to db");
            db.insertOrThrow(BuildingOpenHelper.TABLE, null, values);
        }catch(SQLException e) {
            Log.d(TAG, "Something went wrong." + e);
        }
        */
      //  Log.d(TAG, "Closing db.");
     //   db.close();
      //  Log.d(TAG, "Dropping buildinglist");
       // db.execSQL("DROP TABLE IF EXISTS buildinglist");
        //Log.d(TAG, "Executing db_create_query");
      // db.execSQL(BuildingOpenHelper.DB_CREATE_QUERY);
        
        opener.close();
        db.close();
        
        //set up click listeners for the buttons!
        View mapButton = findViewById(R.id.MapButton);
        mapButton.setOnClickListener(this);
        
  /*      View searchButton = findViewById(R.id.SearchButton);
        searchButton.setOnClickListener(this);*/
        
        View listButton = findViewById(R.id.ListButton);
        listButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.AboutButton);
        aboutButton.setOnClickListener(this);
        
    }
    //Stack Overflow is completely boss. Kudos to Marcin Gil for the code snippet
    @Override 
    public void onConfigurationChanged(Configuration newConfig){ 
    	super.onConfigurationChanged(newConfig); 
    }
    
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.MapButton:
		    Log.d(TAG, "Entering Map");
			Intent i = new Intent(this,View_map.class);
			startActivity(i);
			break;
			
		case R.id.AboutButton:
		    Log.d(TAG, "Entering About");
			Intent k = new Intent(this,About.class);
			startActivity(k);
			break;
			
	/*	case R.id.SearchButton:
			Intent j = new Intent(this,Search_buildings.class);
			startActivity(j);
			break;
		*/	
		case R.id.ListButton:
		    Log.d(TAG, "Entering Building List");
			Intent l = new Intent(this,Buildings_List.class);
			startActivity(l);
			break;	
		}
	}
	
	@Override
    public void onDestroy() {
	    super.onDestroy();
    }
	
}