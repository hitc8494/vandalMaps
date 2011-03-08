package com.vandals.maps;

import java.util.List;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class View_map extends MapActivity{

	public static final int ACCURACY_FINE = 0x00000001;
	private boolean onCampus = true;
	
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
	
	
	MyLocationOverlay overlayme; 
	
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
       	
    	/* Also setup a mapController so we can do the awesome stuff the Android is capable of*/
    	mapController = mapView.getController();
  
    	//set up our drawables
    	drawable = this.getResources().getDrawable(R.drawable.regroup);
    	me = this.getResources().getDrawable(R.drawable.androidmarker);
    	
    	/*Now, to add overlays (which will soon be imported from the preferences)
    	 * These need to be set up with the correct drawable icon.
    	 * */ 
    	itemizedoverlay = new MapsItemizedOverlay(this,drawable);
  
    	addOverlaysFromPref(itemizedoverlay);
    	initMyLocation();
    	
    	Context context = getApplicationContext();    
        /*
         * Right here, if the user is outside the bounds of the U of I Campus, notify them and set center to to
         * Jenssen Engineering. 
         * 
         * Otherwise, we are centering to the user. Easy Peasy
         * 
         */
    	if(onCampus == false) {
            String oncampus = "You are not on campus, animated to the center of U of I.";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, oncampus, duration).show();  
        }
    	
    	/*Populate overlays on the map with the icons from itemizedoverlay*/
    	mapOverlays.add(itemizedoverlay);
	}
	
	private void initMyLocation(){
        overlayme = new MyLocationOverlay(this,mapView);
        overlayme.enableMyLocation();
        overlayme.runOnFirstFix(new Runnable() {
            @Override
            public void run() {
               
                GeoPoint me = overlayme.getMyLocation();
                int myx = me.getLongitudeE6();//x
                int myy = me.getLatitudeE6(); //y         
                if(myx >= -117031903 && myx <= -117001390 && myy <= 46733713 && myy >= 46722535) {//I'm on campus 
                    mapController.setZoom(17);
                    mapController.setCenter(overlayme.getMyLocation());
                    mapController.animateTo(overlayme.getMyLocation());
                }
                else { //not on campus
                    onCampus = false;
                    GeoPoint center = new GeoPoint(46729044,-117014372);
                    mapController.setZoom(17);
                    mapController.setCenter(center);
                    mapController.animateTo(center);
                }
             }       
        });
        mapOverlays.add(overlayme);
    }
	
	//biggest waste of LoC I have ever seen. A SQLite database is in the works
	//to replace this heinous crime against humanity.
	public void addOverlaysFromPref(MapsItemizedOverlay itemizedoverlay){
		/*Part of ugly hack*/
    	GeoPoint hack = new GeoPoint(51096623,1190918);
    	OverlayItem hacked = new OverlayItem(hack,"","Where the Vandals Began");
    	itemizedoverlay.addOverlay(hacked);
    	
    	/*Add Each GeoPoint depending on whether or not it is selected in prefs*/
    	if(List_Buildings.getAARCH(this)){
    	    GeoPoint point = new GeoPoint(46727433,-117011202);
    	  OverlayItem overlayitem = new OverlayItem(point, "","aarch");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getAAID(this)){
    	    GeoPoint point = new GeoPoint(46727676,-117011368);
    	  OverlayItem overlayitem = new OverlayItem(point, "","aaid");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getAD(this)){
    	    GeoPoint point = new GeoPoint(46725948,-117011143);
    	  OverlayItem overlayitem = new OverlayItem(point, "","ad");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getAEEB(this)){
    	  GeoPoint point = new GeoPoint(46730099,-117018728);
    	  OverlayItem overlayitem = new OverlayItem(point, "","aeeb");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getAGBIO(this)){
    	    GeoPoint point = new GeoPoint(46729437,-117013954);
    	  OverlayItem overlayitem = new OverlayItem(point, "","agbio");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getAGSCI(this)){
    	    GeoPoint point = new GeoPoint(46729044,-117014372);
    	  OverlayItem overlayitem = new OverlayItem(point, "","agsci");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getALB(this)){
    	    GeoPoint point = new GeoPoint(46725624,-117011497);
    	  OverlayItem overlayitem = new OverlayItem(point, "","alb");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getARP(this)){
    	    GeoPoint point = new GeoPoint(46728904,-117024983);
    	  OverlayItem overlayitem = new OverlayItem(point, "","arp");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getBEL(this)){
    	    GeoPoint point = new GeoPoint(46729529,-117010649);
    	  OverlayItem overlayitem = new OverlayItem(point, "","bel");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}
    	
        if(List_Buildings.getBOOK(this)){
            GeoPoint point = new GeoPoint(46728511,-117006519);
          OverlayItem overlayitem = new OverlayItem(point, "","book");
          itemizedoverlay.addOverlay(overlayitem);
        }
        
    	if(List_Buildings.getBRI(this)){
    	    GeoPoint point = new GeoPoint(46728139,-117012334);
    	  OverlayItem overlayitem = new OverlayItem(point, "","bri");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getED(this)){
    	    GeoPoint point = new GeoPoint(46726043,-117012302);
    	  OverlayItem overlayitem = new OverlayItem(point, "","ed");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getEP(this)){
    	    GeoPoint point = new GeoPoint(46729264,-117009834);
    	  OverlayItem overlayitem = new OverlayItem(point, "","ep");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getFIELDS(this)){
    	    GeoPoint point = new GeoPoint(46730897,-117020295);
    	  OverlayItem overlayitem = new OverlayItem(point, "","fields");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getGAS(this)){
    	    GeoPoint point = new GeoPoint(46724602,-117010204);
    	  OverlayItem overlayitem = new OverlayItem(point, "","gas");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getGCH(this)){
    	    GeoPoint point = new GeoPoint(46723351,-117016786);
    	  OverlayItem overlayitem = new OverlayItem(point, "","gch");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getGH(this)){
    	    GeoPoint point = new GeoPoint(46730184,-117017602);
    	  OverlayItem overlayitem = new OverlayItem(point, "","gh");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getGJ(this)){
    	    GeoPoint point = new GeoPoint(46729492,-117011336);
    	  OverlayItem overlayitem = new OverlayItem(point, "","gj");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getHRTNG(this)){
    	    GeoPoint point = new GeoPoint(46728897,-117018009);
    	  OverlayItem overlayitem = new OverlayItem(point, "","hrtng");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getIDC(this)){
    	    GeoPoint point = new GeoPoint(46727463,-117012398);
    	  OverlayItem overlayitem = new OverlayItem(point, "","idc");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getINCUB(this)){
    	    GeoPoint point = new GeoPoint(46724477,-117002195);
    	  OverlayItem overlayitem = new OverlayItem(point, "","incub");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getITED(this)){
    	    GeoPoint point = new GeoPoint(46726109,-117005768);
    	  OverlayItem overlayitem = new OverlayItem(point, "","ited");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getJEB(this)){
    	    GeoPoint point = new GeoPoint(46728934,-117011529);
    	  OverlayItem overlayitem = new OverlayItem(point, "","jeb");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getJML(this)){
    	  GeoPoint point = new GeoPoint(46729684,-117020777);
    	  OverlayItem overlayitem = new OverlayItem(point, "","jml");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getKIBBIE(this)){
    	    GeoPoint point = new GeoPoint(46726345,-117017044);
    	  OverlayItem overlayitem = new OverlayItem(point, "","kibbie");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getLAW(this)){
    	    GeoPoint point = new GeoPoint(46727996,-117015864);
    	  OverlayItem overlayitem = new OverlayItem(point, "","law");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getLIB(this)){
    	    GeoPoint point = new GeoPoint(46727441,-117013986);
    	  OverlayItem overlayitem = new OverlayItem(point, "","lib");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getLIFE(this)){
    	    GeoPoint point = new GeoPoint(46727154,-117010446);
    	  OverlayItem overlayitem = new OverlayItem(point, "","life");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getLLC(this)){
    	    GeoPoint point = new GeoPoint(46730478,-117012645);
    	  OverlayItem overlayitem = new OverlayItem(point, "","llc");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getMCCL(this)){
    	    GeoPoint point = new GeoPoint(46728985,-117010671);
    	  OverlayItem overlayitem = new OverlayItem(point, "","mccl");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getMGYM(this)){
    	    GeoPoint point = new GeoPoint(46726624,-117013664);
    	  OverlayItem overlayitem = new OverlayItem(point, "","mgym");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getMINES(this)){
    	    GeoPoint point = new GeoPoint(46728558,-117010891);
    	  OverlayItem overlayitem = new OverlayItem(point, "","mines");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getMORR(this)){
    	    GeoPoint point = new GeoPoint(46728066,-117010843);
    	  OverlayItem overlayitem = new OverlayItem(point, "","morr");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getMUSIC(this)){
    	    GeoPoint point = new GeoPoint(46725676,-117007828);
    	  OverlayItem overlayitem = new OverlayItem(point, "","music");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getCNR(this)){
    	    GeoPoint point = new GeoPoint(46729389,-117012602);
    	  OverlayItem overlayitem = new OverlayItem(point, "","cnr");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getNAVY(this)){
    	    GeoPoint point = new GeoPoint(46728904,-117012388);
    	  OverlayItem overlayitem = new OverlayItem(point, "","navy");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getNICCOL(this)){
    	    GeoPoint point = new GeoPoint(46724852,-117010006);
    	  OverlayItem overlayitem = new OverlayItem(point, "","niccol");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getPEB(this)){
    	    GeoPoint point = new GeoPoint(46725521,-117013729);
    	  OverlayItem overlayitem = new OverlayItem(point, "","peb");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getPHI(this)){
    	    GeoPoint point = new GeoPoint(46728514,-117012806);
    	  OverlayItem overlayitem = new OverlayItem(point, "","phi");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getPOOL(this)){
    	    GeoPoint point = new GeoPoint(46726051,-117013686);
    	  OverlayItem overlayitem = new OverlayItem(point, "","pool");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getRAD(this)){
    	    GeoPoint point = new GeoPoint(46724764,-117012141);
    	  OverlayItem overlayitem = new OverlayItem(point, "","rad");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getREN(this)){
    	    GeoPoint point = new GeoPoint(46728022,-117014018);
    	  OverlayItem overlayitem = new OverlayItem(point, "","ren");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getRIDH(this)){
    	    GeoPoint point = new GeoPoint(46724491,-117008815);
    	  OverlayItem overlayitem = new OverlayItem(point, "","ridh");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getSHOUP(this)){
    	    GeoPoint point = new GeoPoint(46730162,-117015435);
    	  OverlayItem overlayitem = new OverlayItem(point, "","shoup");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getSHS(this)){
    	    GeoPoint point = new GeoPoint(4672726,-117009346);
    	  OverlayItem overlayitem = new OverlayItem(point, "","shs");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getSRC(this)){
    	    GeoPoint point = new GeoPoint(46731956,-117013514);
    	  OverlayItem overlayitem = new OverlayItem(point, "","src");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getSUB(this)){
    	    GeoPoint point = new GeoPoint(46728683,-117007205);
    	  OverlayItem overlayitem = new OverlayItem(point, "","sub");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getTLC(this)){
    	    GeoPoint point = new GeoPoint(46727433,-117012902);
    	  OverlayItem overlayitem = new OverlayItem(point, "","tlc");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getWALLC(this)){
    	    GeoPoint point = new GeoPoint(46730393,-117016352);
    	  OverlayItem overlayitem = new OverlayItem(point, "","wallc");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}

    	if(List_Buildings.getWICKS(this)){
    	    GeoPoint point = new GeoPoint(46730993,-117019372);
    	  OverlayItem overlayitem = new OverlayItem(point, "","wicks");
    	  itemizedoverlay.addOverlay(overlayitem);
    	}    
    	return;
	}
	
    //Stack Overflow is completely boss. Kudos to Marcin Gil for the code snippet
    @Override 
    public void onConfigurationChanged(Configuration newConfig){ 
    	super.onConfigurationChanged(newConfig); 
    }
    
    //default method that must be here.
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	//menu to control the "refresh location" button
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	
	//still a work in progress
	/*
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
	    	  mapOverlays.add(overlayme);
	          return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	*/

	//make sure to shut off the GPS system and resume when needed again
	@Override 
	public void onDestroy(){
		super.onDestroy();
		overlayme.disableMyLocation();
		//locationManager.removeUpdates(locationListener);	
	}
	
	@Override
	public void onPause() {
	    super.onPause();
	    overlayme.disableMyLocation();
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    overlayme.enableMyLocation();
	}
}