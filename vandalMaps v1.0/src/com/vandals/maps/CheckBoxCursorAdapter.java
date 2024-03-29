package com.vandals.maps;



import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
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
    public void bindView(View view, Context context, Cursor cursor) {           

  
        //    cb.setOnClickListener(null);
            TextView cbFull = (TextView)view.findViewById(R.id.fullname);
            TextView cbAbbrev = (TextView)view.findViewById(R.id.abbrev);
           // TextView cb = (TextView)view.findViewById(R.id.cb);
            
       //   cb.setCheckMarkDrawable(android.R.drawable.btn_default);
           // cb.setVisibility(View.VISIBLE);
            
            final String abbrv = cursor.getString(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_ABBRV));
            
            cbFull.setText(cursor.getString(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_NAME)));
          //  cbAbbrev.setText(abbrv);
         // cb.setChecked(cursor.getInt(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_DISPLAYED)) == 1 ? true : false);
            
            Log.v(TAG, "displayed = " + Long.toString(cursor.getInt(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_DISPLAYED))));
                
            
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((CheckedTextView) v).isChecked()) {                    
                        Log.v(TAG, "Calling isChecked==true!");
                        db.execSQL("UPDATE buildinglist SET displayed='1' WHERE abbrv='"+ abbrv +"'");
                     //   ((CheckedTextView) v).setChecked(true);
                     }
                     else  {
                         Log.v(TAG, "Calling isChecked==false!");
                         db.execSQL("UPDATE buildinglist SET displayed='0' WHERE abbrv='"+ abbrv +"'");
                         //(CheckedTextView) v).setChecked(false);
                     //    ((CheckedTextView) v).setChecked(false);
                     }
                }           
            });
           
            final int latitude = cursor.getInt(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_YCOORD));
            final int longitude = cursor.getInt(cursor.getColumnIndexOrThrow(BuildingOpenHelper.C_XCOORD));
            
            view.setOnLongClickListener(new OnLongClickListener() {    
                @Override
                public boolean onLongClick(View v) {
                 //   Log.v(TAG, "Calling isChecked==true!");
                  //  db.execSQL("UPDATE buildinglist SET displayed='1' WHERE abbrv='"+ abbrv +"'");
                   // ((CheckedTextView) v).setChecked(true);
                    
                    Intent i = new Intent(contextMain,View_map.class);
                    i.putExtra("lat", latitude);
                    i.putExtra("long", longitude);
                    Log.d(TAG,"Latitude onLongClick" + latitude + "");
                    Log.d(TAG,"Longitude onLongClick" + longitude +"");
                    
                    contextMain.startActivity(i);
                    return false;
                } 
            });
    }
}
