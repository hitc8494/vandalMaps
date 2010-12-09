package com.vandals.maps;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class Initiate extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initiate);
    
        //set up click listeners for the buttons!
        View mapButton = findViewById(R.id.MapButton);
        mapButton.setOnClickListener(this);
        View searchButton = findViewById(R.id.SearchButton);
        searchButton.setOnClickListener(this);
        View listButton = findViewById(R.id.ListButton);
        listButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.AboutButton);
        aboutButton.setOnClickListener(this);
        
    }
    //Stack Overflow is completely boss. Kudos to Marcin Gil code snippet
    @Override 
    public void onConfigurationChanged(Configuration newConfig){ 
    	super.onConfigurationChanged(newConfig); 
    }
    
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.MapButton:
			Intent i = new Intent(this,View_map.class);
			startActivity(i);
			break;
			
		case R.id.AboutButton:
			Intent k = new Intent(this,About.class);
			startActivity(k);
			break;
			
		case R.id.SearchButton:
			Intent j = new Intent(this,Search_buildings.class);
			startActivity(j);
			break;
			
		case R.id.ListButton:
			Intent l = new Intent(this,List_Buildings.class);
			startActivity(l);
			break;	

		}
	}
}