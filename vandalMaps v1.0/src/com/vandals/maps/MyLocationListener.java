package com.vandals.maps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class MyLocationListener implements LocationListener{
		@Override
		public void onLocationChanged(Location loc){
		/*	loc.getLatitude();
			loc.getLongitude();
		  /*String Text = “My current location is: “ + “Latitud = “ + loc.getLatitude() + “Longitud = “ + loc.getLongitude();*/
		//	String Text = "My current location is: " + "Latitude = " + loc.getLatitude() + "Longitude = " + loc.getLongitude();
		//	Toast.makeText(getApplicationContext(),Text,Toast.LENGTH_SHORT).show();
			//*/
		}

		@Override
		public void onProviderDisabled(String provider){
		}

		@Override
		public void onProviderEnabled(String provider){}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras){
		}
}
