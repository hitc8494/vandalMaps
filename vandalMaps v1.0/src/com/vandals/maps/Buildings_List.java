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
    static final int[] TO = { R.id.fullname, R.id.abbrev, R.id.cb };
    private static final String TAG = "Buildings_List";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        opener = new BuildingOpenHelper(this);
        db = opener.getReadableDatabase();
        dbWrite = opener.getWritableDatabase();
        
        Cursor myCur = null;
        try {
         myCur = db.query(BuildingOpenHelper.TABLE,null,null,null,null,null,BuildingOpenHelper._ID + " DESC");
        }
        catch(SQLException e){
            Log.d(TAG,"Query Went Bad");
        }
        startManagingCursor(myCur);

        //using depracated SimpleCursorAdapter. Not quite sure what the flags need to be when using updated constructor
        SimpleCursorAdapter adapt = new CheckBoxCursorAdapter(this, R.layout.buildinglisting, myCur, FROM, TO, dbWrite);
        
        setListAdapter(adapt);
    }
    
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
        db = opener.getReadableDatabase();
        dbWrite = opener.getWritableDatabase();
    }
}
