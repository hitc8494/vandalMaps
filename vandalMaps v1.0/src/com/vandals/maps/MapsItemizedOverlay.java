package com.vandals.maps;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;



public class MapsItemizedOverlay extends ItemizedOverlay{
	//array to hold each of our geopoints
	public ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
		
	public MapsItemizedOverlay(Context context, Drawable defaultMarker) {
	    super(boundCenterBottom(defaultMarker));
	    mContext = context;
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
	  public boolean onTap(int pIndex) {
		Toast.makeText(mContext, mOverlays.get(pIndex).getSnippet(),Toast.LENGTH_SHORT).show();
		return true;
	  }	
	  
	 public void clear(){
		 mOverlays.clear();
	 }
}
