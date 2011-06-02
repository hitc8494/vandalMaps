package com.vandals.maps;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class Buildings_List extends ListActivity{
    BuildingOpenHelper opener;
    SQLiteDatabase db;
    SQLiteDatabase dbWrite;
    static final String[] FROM = {BuildingOpenHelper.C_NAME, BuildingOpenHelper.C_ABBRV, BuildingOpenHelper.C_DISPLAYED};
   // static final int[] TO = { R.id.fullname, R.id.abbrev, R.id.cb };
    private static final String TAG = "Buildings_List";
    //SimpleCursorAdapter adapt;
    CursorAdapter adapt;
    Cursor myCur = null;
    Bundle savedInstanceState2;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        savedInstanceState2 = savedInstanceState;   
        super.onCreate(savedInstanceState2);
        ListView awesome = getListView();
 
        opener = new BuildingOpenHelper(this);
        db = opener.getReadableDatabase();
        dbWrite = opener.getWritableDatabase();
        
        
        try {
         myCur = db.query(BuildingOpenHelper.TABLE,null,null,null,null,null,BuildingOpenHelper._ID + " DESC");
        }
        catch(SQLException e){
            Log.d(TAG,"Query Went Bad");
        }
        startManagingCursor(myCur);

        //using depracated SimpleCursorAdapter. Not quite sure what the flags need to be when using updated constructor
        adapt = new MapCursorAdapter(this, myCur, dbWrite);
        
        
        
        setListAdapter(adapt);
    }
    //   awesome.setOnItemClickListener(itemclicked);
    /*private OnItemClickListener itemclicked = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View v, int arg2,
                long arg3) {
            if(((CheckedTextView) v).isChecked()) {                    
                Log.v(TAG, "Calling isChecked==true!");
                db.execSQL("UPDATE buildinglist SET displayed='1' WHERE abbrv='"+ abbrv +"'");
                ((CheckedTextView) v).setChecked(true);
             }
             else  {
                 Log.v(TAG, "Calling isChecked==false!");
                 db.execSQL("UPDATE buildinglist SET displayed='0' WHERE abbrv='"+ abbrv +"'");
                 //(CheckedTextView) v).setChecked(false);
                 ((CheckedTextView) v).setChecked(false);
             }
        }
    };*/
    
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        dbWrite.close();
    }
    
    public void onPause() {
        super.onPause();
        db.close();
        dbWrite.close();
    }
    
    public void onResume() {
        super.onResume();
        onCreate(savedInstanceState2);
        db = opener.getReadableDatabase();
        dbWrite = opener.getWritableDatabase();
    }
}
