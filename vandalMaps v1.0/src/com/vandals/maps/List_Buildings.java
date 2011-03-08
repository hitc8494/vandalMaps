package com.vandals.maps;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


//seriously looking forward to building the SQLite database to replace this.
public class List_Buildings extends PreferenceActivity{
	
    private static final boolean option1 = false;
    private static final boolean option2 = false;
    private static final boolean option3 = false;
    private static final boolean option4 = false;
    private static final boolean option5 = false;
    private static final boolean option6 = false;
    private static final boolean option7 = false;
    private static final boolean option8 = false;
    private static final boolean option9 = false;
    private static final boolean option10 = false;
    private static final boolean option11 = false;
    private static final boolean option12 = false;
    private static final boolean option13 = false;
    private static final boolean option14 = false;
    private static final boolean option15 = false;
    private static final boolean option16 = false;
    private static final boolean option17 = false;
    private static final boolean option18 = false;
    private static final boolean option19 = false;
    private static final boolean option20 = false;
    private static final boolean option21 = false;
    private static final boolean option22 = false;
    private static final boolean option23 = false;
    private static final boolean option24 = false;
    private static final boolean option25 = false;
    private static final boolean option26 = false;
    private static final boolean option27 = false;
    private static final boolean option28 = false;
    private static final boolean option29 = false;
    private static final boolean option30 = false;
    private static final boolean option31 = false;
    private static final boolean option32 = false;
    private static final boolean option33 = false;
    private static final boolean option34 = false;
    private static final boolean option35 = false;
    private static final boolean option36 = false;
    private static final boolean option37 = false;
    private static final boolean option38 = false;
    private static final boolean option39 = false;
    private static final boolean option40 = false;
    private static final boolean option41 = false;
    private static final boolean option42 = false;
    private static final boolean option43 = false;
    private static final boolean option44 = false;
    private static final boolean option45 = false;
    private static final boolean option46 = false;
    private static final boolean option47 = false;
    private static final boolean option48 = false;
    private static final boolean option49 = false;
    private static final boolean option50 = false;
    private static final boolean option51 = false;
    private static final boolean option52 = false;
    private static final boolean option53 = false;
    private static final boolean option54 = false;
    private static final boolean option55 = false;      
    
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
	
	public static boolean getAARCH(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("aarch", option1);
	}
	public static boolean getAAID(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("aaid", option2);
	}
	public static boolean getAD(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("ad", option3);
	}
	public static boolean getAEEB(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("aeeb", option4);
	}
	public static boolean getAGBIO(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("agbio", option5);
	}
	public static boolean getAGSCI(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("agsci", option6);
	}
	public static boolean getALB(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("alb", option7);
	}
	public static boolean getALC(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("alc", option8);
	}
	public static boolean getARP(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("arp", option9);
	}
	public static boolean getBEL(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("bel", option10);
	}
    public static boolean getBOOK(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("book", option55);
   }	
	public static boolean getBRI(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("bri", option11);
	}
	public static boolean getCEB(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("ceb", option12);
	}
	public static boolean getDAIRY(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("dairy", option13);
	}
	public static boolean getED(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("ed", option14);
	}
	public static boolean getEP(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("ep", option15);
	}
	public static boolean getFIELDS(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("fields", option16);
	}
	public static boolean getFRC(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("frc", option17);
	}
	public static boolean getGAS(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("gas", option18);
	}
	public static boolean getGCH(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("gch", option19);
	}
	public static boolean getGH(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("gh", option20);
	}
	public static boolean getGJ(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("gj", option21);
	}
	public static boolean getHRTNG(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("hrtng", option22);
	}
	public static boolean getIDC(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("idc", option23);
	}
	public static boolean getINCUB(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("incub", option24);
	}
	public static boolean getITED(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("ited", option25);
	}
	public static boolean getJEB(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("jeb", option26);
	}
	public static boolean getJML(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("jml", option27);
	}
	public static boolean getKIBBIE(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("kibbie", option28);
	}
	public static boolean getLAW(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("law", option29);
	}
	public static boolean getLIB(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("lib", option30);
	}
	public static boolean getLIFE(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("life", option31);
	}
	public static boolean getLLC(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("llc", option32);
	}
	public static boolean getMCCL(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("mccl", option33);
	}
	public static boolean getMGYM(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("mgym", option34);
	}
	public static boolean getMINES(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("mines", option35);
	}
	public static boolean getMORR(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("morr", option36);
	}
	public static boolean getMUSIC(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("music", option37);
	}
	public static boolean getCNR(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("cnr", option38);
	}
	public static boolean getNAVY(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("navy", option39);
	}
	public static boolean getNICCOL(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("niccol", option40);
	}
	public static boolean getPEB(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("peb", option41);
	}
	public static boolean getPHI(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("phi", option42);
	}
	public static boolean getPOOL(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("pool", option43);
	}
	public static boolean getRAD(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("rad", option44);
	}
	public static boolean getREN(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("ren", option45);
	}
	public static boolean getRIDH(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("ridh", option46);
	}
	public static boolean getSHOUP(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("shoup", option47);
	}
	public static boolean getSHS(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("shs", option48);
	}
	public static boolean getSRC(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("src", option49);
	}
	public static boolean getSUB(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("sub", option50);
	}
	public static boolean getTLC(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("tlc", option51);
	}
	public static boolean getVETSC(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("vetsc", option52);
	}
	public static boolean getWALLC(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("wallc", option53);
	}
	public static boolean getWICKS(Context context){
	     return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("wicks", option54);
	}

	
    
}
