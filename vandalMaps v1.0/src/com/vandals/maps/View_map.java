package com.vandals.maps;

import java.util.List;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class View_map extends MapActivity{

	private static final String TAG = null;
	private double mLongitude = 0;
	private double mLatitude = 0;
	private final boolean enabledOnly = true;
	public static final int ACCURACY_FINE = 0x00000001;
	private String bestProvider;
	
	/* My Location Listener */ /*
	private final LocationListener locationListener = new LocationListener() {
		  public void onLocationChanged(Location location) {
			  overlayme.clear();
	          Double longitude = location.getLongitude()*1E6;
	          Double latitude = location.getLatitude()*1E6;
	          GeoPoint locationPoint = new GeoPoint(latitude.intValue(),longitude.intValue());
	    	  OverlayItem overlayitem = new OverlayItem(locationPoint, "Me", "Me");
	          overlayme.addOverlay(overlayitem);
	    	  mapOverlays.add(overlayme);
		  }
		  public void onProviderDisabled(String provider){}
		  public void onProviderEnabled(String provider) {}
		  public void onStatusChanged(String provider, int status, Bundle extras) {}
	};
	*
	*/
	//this code is stripped directly from the google tutorial

	// Define a listener that responds to location updates
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
	LinearLayout linearLayout;
	MapView mapView;
	Location loc;	
	
	//To make the Overlays work
	List<Overlay> mapOverlays;
	Drawable drawable;
	Drawable invisipoint;
	Drawable me;
	
	MapsItemizedOverlay itemizedoverlay;
	MapsItemizedOverlay invisible;
	MapsItemizedOverlay overlayme;
	
	MapController mapController;
	LocationManager mlocManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		//basic setup
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_map);
    	mapView = (MapView) findViewById(R.id.mapview);
    	mapView.setBuiltInZoomControls(true);
       	mapOverlays = mapView.getOverlays();
       	
    	/* Also setup mapcontroller so we can do the awesome stuff the android is capable of*/
    	mapController = mapView.getController();
  
    	//set up our drawables
    	drawable = this.getResources().getDrawable(R.drawable.regroup);
    	me = this.getResources().getDrawable(R.drawable.androidmarker);
    	
    	/*Now, to add overlays (which will soon be imported from the preferences)*/

    	itemizedoverlay = new MapsItemizedOverlay(this,drawable);
    	overlayme = new MapsItemizedOverlay(this,me);
    	
    	/*Hack to solve the map erroring out when no overlays are present 
    	invisipoint = this.getResources().getDrawable(R.drawable.invisipoint);
    	invisible = new MapsItemizedOverlay(this,invisipoint);
    	mapOverlays.add(invisible);*/
    	
    	/*Add whatever the user wants to the map according to their preferences to our list of
    	 * possible overlays*/
    	addOverlaysFromPref(itemizedoverlay);
    	
    	
    	/*Set up the location managers needed *//*
    	mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	//LocationListener mlocListener = new MyLocationListener();
    	mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 6000, 0, locationListener);
    	
    	
    	//set up a criteria
    	
    	Criteria criteria = new Criteria();
    	criteria.setAccuracy(Criteria.ACCURACY_FINE);
    	criteria.setAltitudeRequired(false);
    	criteria.setBearingRequired(false);
    	criteria.setCostAllowed(true);
    	criteria.setPowerRequirement(Criteria.POWER_LOW);
    	
    	//using our criteria and our location manager dig out our location
    	bestProvider = mlocManager.getBestProvider(criteria, true);
    	
    	loc = mlocManager.getLastKnownLocation(bestProvider);
    	
    	
    	
    	//get our location on startup
    	Double longitude = loc.getLongitude()*1E6;
        Double latitude = loc.getLatitude()*1E6;
    	GeoPoint me = new GeoPoint(latitude.intValue(),longitude.intValue());
    	OverlayItem mypoint = new OverlayItem(me, "Me", "Me");	
    	overlayme.addOverlay(mypoint);
    	*/
    	/* After much frustration, trying something from StackOverflow user Janne Oksanen*/
    	//this doesn't work either, found an obscure bug in the google API
    	/*
    	mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	Location lastKnownLoc = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

    	GeoPoint gp;
    	if (lastKnownLoc != null){	
    		int longTemp = (int)(lastKnownLoc.getLongitude()* 1000000);
    		int latTemp = (int)(lastKnownLoc.getLatitude() * 1000000);
    		gp = new GeoPoint(latTemp, longTemp);
    	}
    	else {
    		gp = new GeoPoint(37579413,-101074219);
    	}
    	OverlayItem mypoint = new OverlayItem(gp, "Me", "Me");	
    	overlayme.addOverlay(mypoint);
    	*/
    	
    	//google solution
    	// Register the listener with the Location Manager to receive location updates
    	locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    	locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    	Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    	
    	GeoPoint gp;
    	if (lastKnownLocation != null){	
    		int longTemp = (int)(lastKnownLocation.getLongitude()* 1000000);
    		int latTemp = (int)(lastKnownLocation.getLatitude() * 1000000);
    		gp = new GeoPoint(latTemp, longTemp);
    	}
    	else {
    		gp = new GeoPoint(37579413,-101074219);
    	}
    	OverlayItem mypoint = new OverlayItem(gp, "Me", "Me");	
    	overlayme.addOverlay(mypoint);
    	
    	
    	
    	
    	/*Populate overlays on the map with the icons from itemizedoverlay*/
    	mapOverlays.add(itemizedoverlay);
    	mapOverlays.add(overlayme);
    	
    	//center the map on Jenssen Engineering
    	GeoPoint center = new GeoPoint(46728941,-117011411);
    	mapController.setCenter(center);
    	mapController.animateTo(center);
    	mapController.setZoom(17);
    	
	}
	
	public void addOverlaysFromPref(MapsItemizedOverlay itemizedoverlay){
		/*Part of ugly hack*/
    	GeoPoint hack = new GeoPoint(51096623,1190918);
    	OverlayItem hacked = new OverlayItem(hack,"","Where the Vandals Began");
    	itemizedoverlay.addOverlay(hacked);
    	
    	/*Add Each GeoPoint depending on whether or not it is selected in prefs*/
		if(List_Buildings.getJEB(this)){
			GeoPoint point = new GeoPoint(46728959,-117011628);
	    	OverlayItem overlayitem = new OverlayItem(point, "","Jeb");
	    	itemizedoverlay.addOverlay(overlayitem);
		}
		
		if(List_Buildings.getGauss(this)){
	    	GeoPoint point1 = new GeoPoint(46729467,-117011486);
	    	OverlayItem overlayitem1 = new OverlayItem(point1,"","Gauss");
	    	itemizedoverlay.addOverlay(overlayitem1);
		}
		
		if(List_Buildings.getBuch(this)){
			GeoPoint point2 = new GeoPoint(46729487,-117010674);
			OverlayItem overlayitem2 = new OverlayItem(point2,"","Buchanan");
			itemizedoverlay.addOverlay(overlayitem2);
		}
    	return;
	}
	
    //Stack Overflow is completely boss. Kudos to Marcin Gil for the code snippet
    @Override 
    public void onConfigurationChanged(Configuration newConfig){ 
    	super.onConfigurationChanged(newConfig); 
    }
    
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	
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
	    	  mapOverlays.add(overlayme);*/
	          return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	

	public void getFirstCoords(Location loc){
		
		return;
	}
	@Override 
	public void onDestroy(){
		super.onDestroy();
		locationManager.removeUpdates(locationListener);	
	}

}
