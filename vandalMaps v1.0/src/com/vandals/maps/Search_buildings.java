package com.vandals.maps;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class Search_buildings extends Activity{
	@Override 
	public void onCreate(Bundle savedInstanceState){	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_buildings);
	}
	
    //Stack Overflow is completely boss. Kudos to Marcin Gil code snippet
    @Override 
    public void onConfigurationChanged(Configuration newConfig){ 
    	super.onConfigurationChanged(newConfig); 
    }	
}
