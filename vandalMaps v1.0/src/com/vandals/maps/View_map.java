package com.vandals.maps;

import java.util.List;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class View_map extends MapActivity {

	//To make the map work
	LinearLayout linearLayout;
	MapView mapView;
	
	//To make the Overlays work
	List<Overlay> mapOverlays;
	Drawable drawable;
	MapsItemizedOverlay itemizedoverlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		//basic setup
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_map);
    	mapView = (MapView) findViewById(R.id.mapview);
    	mapView.setBuiltInZoomControls(true);
    	
    	/*Now, to add overlays (which will soon be imported from the bundle)*/
    	mapOverlays = mapView.getOverlays();
    	drawable = this.getResources().getDrawable(R.drawable.androidmarker);
    	itemizedoverlay = new MapsItemizedOverlay(drawable);
    
    	GeoPoint point = new GeoPoint(19240000,-99120000);
    	OverlayItem overlayitem = new OverlayItem(point, "Hello", "There");
    	
    	itemizedoverlay.addOverlay(overlayitem);
    	mapOverlays.add(itemizedoverlay);
    	
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
}
