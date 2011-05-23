package com.vandals.maps;

import java.util.List;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class View_map extends MapActivity{

    public static final String TAG = "View_Map";
	public static final int ACCURACY_FINE = 0x00000001;
	private static boolean onCampus = true;
	SQLiteDatabase db;
    Cursor myCur = null;
    GeoPoint center = null;
    
    
	// Define a listener that responds to location updates
	//this is not even used. Why do I still have this?
	LocationListener locationListener = new LocationListener() {
	    public void onLocationChanged(Location location) {
	      // Called when a new location is found by the network location provider.
	    }
	    public void onStatusChanged(String provider, int status, Bundle extras) {}

	    public void onProviderEnabled(String provider) {}

	    public void onProviderDisabled(String provider) {}
	  };
	  LocationManager locationManager;
	
	//To make the map work
	//private static LinearLayout linearLayout;
	private static MapView mapView;
	//private static Location loc;	
	
	//To make the Overlays work
	private static List<Overlay> mapOverlays;
	private static Drawable drawable;
	
	private static MapsItemizedOverlay itemizedoverlay;
	//private static MapsItemizedOverlay invisible;
	
	private static MyLocationOverlay overlayme; 
	
	private static MapController mapController;
	//private static LocationManager mlocManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		//basic setup
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_map);
    	mapView = (MapView) findViewById(R.id.mapview);
    	mapView.setBuiltInZoomControls(true);
       	mapOverlays = mapView.getOverlays();
       	
       	//get our extras
       
       	Log.d(TAG,"Grabbing Extras");
       	Bundle extras = getIntent().getExtras();
        if(extras != null) {//we have a center!
         //   Log.d(TAG,"EXTRA 1: " + extras.getInt("lat"));
            center = new GeoPoint(-extras.getInt("lat"),extras.getInt("long"));            
        }
    	/* Also setup a mapController so we can do the awesome stuff the Android is capable of*/
    	mapController = mapView.getController();
  
    	//set up our drawables
    	drawable = this.getResources().getDrawable(R.drawable.regroup);
    	//me = this.getResources().getDrawable(R.drawable.androidmarker);
    	
    	/*Now, to add overlays (which will soon be imported from the preferences)
    	 * These need to be set up with the correct drawable icon.
    	 * */ 
    	itemizedoverlay = new MapsItemizedOverlay(this,drawable);
  
    	//addOverlaysFromPref(itemizedoverlay);
        Log.v(TAG, "Right Before AddOverlaysFromCursor");
    	addOverlaysFromCursor(itemizedoverlay);
    	initMyLocation();
    	
    	Context context = getApplicationContext();    
        /*
         * Right here, if the user is outside the bounds of the U of I Campus, notify them and set center to to
         * Jenssen Engineering. 
         * 
         * Otherwise, we are centering to the user. Easy Peasy
         * 
         */
    	if(onCampus == false) {
            String oncampus = "You are not on campus, animated to the center of U of I or your selection";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, oncampus, duration).show();  
        }
    	
    	/*Populate overlays on the map with the icons from itemizedoverlay*/
    	mapOverlays.add(itemizedoverlay);
	}
	
	private void initMyLocation(){
        overlayme = new MyLocationOverlay(this,mapView);
        overlayme.enableMyLocation();
        overlayme.runOnFirstFix(new Runnable() {
            @Override
            public void run() {
               
                GeoPoint me = overlayme.getMyLocation();
                int myx = me.getLongitudeE6();//x
                int myy = me.getLatitudeE6(); //y         
                if(myx >= -117031903 && myx <= -117001390 && myy <= 46733713 && myy >= 46722535) {//I'm on campus 
                    if(center == null) {
                        mapController.setZoom(17);
                        mapController.setCenter(overlayme.getMyLocation());
                        mapController.animateTo(overlayme.getMyLocation());
                    }
                    else {
                        mapController.setZoom(17);
                        mapController.setCenter(center);
                        mapController.animateTo(center);
                    }
                }
                else { //not on campus
                    onCampus = false;
                    if(center == null) {
                        center = new GeoPoint(46729044,-117014372);
                        mapController.setZoom(17);
                        mapController.setCenter(center);
                        mapController.animateTo(center);
                    }else {
                        mapController.setZoom(17);
                        mapController.setCenter(center);
                        mapController.animateTo(center);
                    }
                }
             }       
        });
        mapOverlays.add(overlayme);
    }
	
	//SQLiteDB Implementation of addOverlaysFromPref That horrible abomination is now gone.
	public void addOverlaysFromCursor(MapsItemizedOverlay itemizedOverlay) {
	    BuildingOpenHelper opener = new BuildingOpenHelper(this);
	    db = opener.getReadableDatabase();
	    Log.v(TAG, "Adding overlays from cursor.");

        try {
         //query buildinglist WHERE displayed=1 ORDER BY _ID DESC
         myCur = db.query(BuildingOpenHelper.TABLE,null,"displayed=?",new String[] {"1"},null,null,BuildingOpenHelper._ID + " DESC");
        }
        catch(SQLException e){
            Log.d(TAG,"Query Went Bad");
        }
        
        
        GeoPoint hack = new GeoPoint(51096623,1190918);
        OverlayItem hacked = new OverlayItem(hack,"","Where the Vandals Began");
        itemizedoverlay.addOverlay(hacked);
        
        /* static final String DB_CREATE_QUERY = 
         *                                                ~-117                    ~43
         *                                                  1                       2                          3                   4                          5
         _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C_XCOORD + " INTEGER, " + C_YCOORD + " INTEGER, " + C_NAME + " TEXT, " + C_ABBRV + " TEXT, " + C_DISPLAYED + " INTEGER)";
        */
        myCur.moveToFirst();
        while(myCur.isAfterLast() == false) {
            GeoPoint point = new GeoPoint(myCur.getInt(2),myCur.getInt(1));
            OverlayItem overlayitem = new OverlayItem(point, "",myCur.getString(4));
            itemizedoverlay.addOverlay(overlayitem);  
            myCur.moveToNext();
        }

        db.close();
        myCur.close();    
	}
	
	
	
    //Stack Overflow is completely boss. Kudos to Marcin Gil for the code snippet
    @Override 
    public void onConfigurationChanged(Configuration newConfig){ 
    	super.onConfigurationChanged(newConfig); 
    }
    
    //default method that must be here.
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	//menu to control the "refresh location" button
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	
	//still a work in progress
	/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//get our new location and set the two private variables to it	
	    switch (item.getItemId()) {
	        case R.id.add_new_marker:
	         /* overlayme.clear();
	          double Longitude = loc.getLongitude();
	          double Latitude = loc.getLatitude();
	    	  Log.i(TAG, "<long, lat> = <" + Longitude + "," + Latitude + ">");
	    	  GeoPoint point = new GeoPoint((int)(Longitude * 1E6), (int)(Latitude * 1E6));
	    	  OverlayItem overlayitem = new OverlayItem(point, "Me", "Hello ");
	          overlayme.addOverlay(overlayitem);
	    	  mapOverlays.add(overlayme);
	          return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	*/

	//make sure to shut off the GPS system and resume when needed again
	@Override 
	public void onDestroy(){
		super.onDestroy();
		overlayme.disableMyLocation();
		//locationManager.removeUpdates(locationListener);	
	}
	
	@Override
	public void onPause() {
	    super.onPause();
	    overlayme.disableMyLocation();
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    overlayme.enableMyLocation();
	}
}