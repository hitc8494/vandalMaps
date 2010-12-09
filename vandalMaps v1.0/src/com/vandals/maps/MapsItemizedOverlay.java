package com.vandals.maps;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;



public class MapsItemizedOverlay extends ItemizedOverlay{
	//array to hold each of our geopoints
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	
		
	public MapsItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	@Override
	protected OverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}

	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	  @Override
	  public boolean onTap(GeoPoint p, MapView map) {
		  
		  
	      return false;
	  }	
}
