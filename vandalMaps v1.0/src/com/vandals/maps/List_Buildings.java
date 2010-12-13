package com.vandals.maps;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;



public class List_Buildings extends PreferenceActivity{
	
	private static final boolean option = false; 
	private static final boolean option1 = false; 
	private static final boolean option2 = false; 
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
	
	public static boolean getJEB(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("Jeb", option);
	}
	
	public static boolean getGauss(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("Gauss", option1);
	}

	public static boolean getBuch(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("Buch", option2);
	}
}
