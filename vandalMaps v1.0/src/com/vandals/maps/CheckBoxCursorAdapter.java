package com.vandals.maps;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CheckBoxCursorAdapter extends SimpleCursorAdapter{
    static final String TAG = "CheckBoxCursorAdapter";
    final Context contextMain;
    Cursor c;
    SQLiteDatabase db;
    
    public CheckBoxCursorAdapter(Context context, int layout, Cursor c,
            String[] from, int[] to, SQLiteDatabase db) {
        super(context, layout, c, from, to);
        this.contextMain = context;
        this.c = c;
        this.db = db;
    }
    
    
    @Override   
    public void bindView(View view, final Context context, Cursor cursor) {
            final int latitude = cursor.getInt(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_YCOORD));
            final int longitude = cursor.getInt(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_XCOORD));
            
            view.setOnLongClickListener(new OnLongClickListener() {    
                @Override
                public boolean onLongClick(View v) {
                    Intent i = new Intent(contextMain,View_map.class);
                    i.putExtra("lat", latitude);
                    i.putExtra("long", longitude);
                    Log.d(TAG,"Latitude onLongClick" + latitude + "");
                    Log.d(TAG,"Longitude onLongClick" + longitude +"");
                    
                    contextMain.startActivity(i);
                    return false;
                } 
            });
        

            CheckBox cb = (CheckBox)view.findViewById(R.id.cb);
            TextView cbFull = (TextView)view.findViewById(R.id.fullname);
            TextView cbAbbrev = (TextView)view.findViewById(R.id.abbrev);

            final String abbrv = cursor.getString(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_ABBRV));
            
            cbFull.setText(cursor.getString(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_NAME)));
            cbAbbrev.setText(abbrv);
       
            cb.setChecked(cursor.getInt(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_DISPLAYED)) == 1 ? true : false);
            
            Log.v(TAG, "displayed = " + Long.toString(cursor.getInt(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_DISPLAYED))));
 
            cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton cb, boolean isChecked) {            
                    if(cb.isChecked()) {                    
                       Log.v(TAG, "Calling isChecked==true!");
                       db.execSQL("UPDATE buildinglist SET displayed='1' WHERE abbrv='"+ abbrv +"'");
                       cb.setChecked(true);
                    }
                    else  {
                        Log.v(TAG, "Calling isChecked==false!");
                        db.execSQL("UPDATE buildinglist SET displayed='0' WHERE abbrv='"+ abbrv +"'");
                        cb.setChecked(false);
                    }
                }           
            });
    }
}
