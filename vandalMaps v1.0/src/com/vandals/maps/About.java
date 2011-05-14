package com.vandals.maps;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class About extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
	}
	
    //Stack Overflow is completely boss. Kudos to Marcin Gil code snippet
    @Override 
    public void onConfigurationChanged(Configuration newConfig){ 
    	super.onConfigurationChanged(newConfig); 
    }
}
