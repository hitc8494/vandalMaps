package com.vandals.maps;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MapCursorAdapter extends CursorAdapter{
    static final String TAG = "MapCursorAdapter";
    private Cursor c;
    LayoutInflater mInflater;
    private Context context;
    private SQLiteDatabase db;
    boolean changedDB = false;
    
    private static class ViewHolder{
        TextView full;
        TextView abbrev;
        boolean cb;
    }
    
    public MapCursorAdapter(Context context, Cursor c, SQLiteDatabase db) {
        super(context, c);
        this.c = c;
        this.mInflater = LayoutInflater.from(context) ;
        this.context = context;
        this.db = db;
    }

    @Override 
    public View getView(int position, View convertView, ViewGroup parent) {  
        ViewHolder holder = null;
        //update the cursor if the db has changed
        if(changedDB == true) {
            try {
                c = db.query(BuildingOpenHelper.TABLE,null,null,null,null,null,BuildingOpenHelper._ID + " DESC");
               }
               catch(SQLException e){
                   Log.d(TAG,"Query Went Bad");
               }
               changedDB = false;
        }
        
        
        c.moveToPosition(position); 
        final String abbrv = c.getString(c.getColumnIndexOrThrow(BuildingOpenHelper.C_ABBRV));
        
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.buildinglisting, null);
                
            holder = new ViewHolder();
            holder.full = (TextView)convertView.findViewById(R.id.fullname);
            holder.abbrev = (TextView)convertView.findViewById(R.id.abbrev);
            holder.cb = (c.getInt(c.getColumnIndexOrThrow(BuildingOpenHelper.C_DISPLAYED)) == 1 ? true : false);
                
            convertView.setTag(holder);
         }
         else {
             holder = (ViewHolder) convertView.getTag();
             holder.cb = (c.getInt(c.getColumnIndexOrThrow(BuildingOpenHelper.C_DISPLAYED)) == 1 ? true : false);    
         }
            
         holder.full.setText(c.getString(c.getColumnIndexOrThrow(BuildingOpenHelper.C_NAME)));
            
         if(holder.cb == true) {
             holder.abbrev.setTextColor(0xff189400);
             holder.abbrev.setText(abbrv + " is shown on the map. Tap to remove.");
         }
         else {
             holder.abbrev.setTextColor(0xff940000);
             holder.abbrev.setText("Tap to show " + abbrv + " on the map.");
         }
        
        
        //onClick functionality
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder holder = (ViewHolder) v.getTag();
                if(holder.cb == true) {                    
                    holder.abbrev.setTextColor(0xff940000);
                    db.execSQL("UPDATE buildinglist SET displayed='0' WHERE abbrv='"+ abbrv +"'");
                    holder.abbrev.setText("Tap to show " + abbrv + " on the map.");
                    holder.cb = false;
                    changedDB = true;
                 }
                 else  {
                     holder.abbrev.setTextColor(0xff189400);
                     db.execSQL("UPDATE buildinglist SET displayed='1' WHERE abbrv='"+ abbrv +"'");
                     holder.abbrev.setText(abbrv + " is shown on the map. Tap to remove.");
                     holder.cb = true;
                     changedDB = true;
                 }
            }           
        });
        
        //onLongClick functionality
        final int latitude = c.getInt(c.getColumnIndexOrThrow(BuildingOpenHelper.C_YCOORD));
        final int longitude = c.getInt(c.getColumnIndexOrThrow(BuildingOpenHelper.C_XCOORD));
        
        convertView.setOnLongClickListener(new OnLongClickListener() {    
            @Override
            public boolean onLongClick(View v) {
                ViewHolder holder = (ViewHolder) v.getTag();
                //Log.v(TAG, "Calling isChecked==true!");
                db.execSQL("UPDATE buildinglist SET displayed='1' WHERE abbrv='"+ abbrv +"'");
                //((CheckedTextView) v).setChecked(true);
                
                changedDB = true;
                Intent i = new Intent(context,View_map.class);
                i.putExtra("lat", latitude);
                i.putExtra("long", longitude);
                Log.d(TAG,"Latitude onLongClick" + latitude + "");
                Log.d(TAG,"Longitude onLongClick" + longitude +"");
                
                context.startActivity(i);
                
                return false;
            } 
        });
        
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // TODO Auto-generated method stub
        return null;
    }

}
