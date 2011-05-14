package com.vandals.maps;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
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
    Context context;
    Cursor c;
    SQLiteDatabase db;
    
    public CheckBoxCursorAdapter(Context context, int layout, Cursor c,
            String[] from, int[] to, SQLiteDatabase db) {
        super(context, layout, c, from, to);
        this.context = context;
        this.c = c;
        this.db = db;
    }
    
    
    @Override   
    public void bindView(View view, Context context, Cursor cursor) {
            view.setOnLongClickListener(new OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // TODO Auto-generated method stub
                    return false;
                }
                
                
            });
        
        
            final int cbindex = cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_DISPLAYED);
            final int cbFullindex = cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_NAME);
            final int cbAbbrevindex = cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_ABBRV);

            CheckBox cb = (CheckBox)view.findViewById(R.id.cb);
            TextView cbFull = (TextView)view.findViewById(R.id.fullname);
            TextView cbAbbrev = (TextView)view.findViewById(R.id.abbrev);
            
            
            final String abbrv = cursor.getString(cbAbbrevindex);
            
            cbFull.setText(cursor.getString(cbFullindex));
            cbAbbrev.setText(abbrv);
       
            cb.setChecked(cursor.getInt(cbindex) == 1 ? true : false);
            
            Log.v(TAG, "displayed = " + Long.toString(cursor.getInt(cbindex)));
 
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
