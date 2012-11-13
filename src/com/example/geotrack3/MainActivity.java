package com.example.geotrack3;

import java.util.ArrayList;
import java.util.Date;
import android.os.Bundle;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.location.*;

public class MainActivity extends FragmentActivity {

	public LocationManager mLocationManager;
	private int first=0;
	
	
	
	static public ArrayList<Locations> locationArray= new ArrayList<Locations>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        
     
        //Check that the activity is using the layout version
        // the framelayout
        
        if(findViewById(R.id.fragment_container)!=null){
        	//To avoid overlapping fragments check if we are restored from a state, then we don't have to do anything
        	if(savedInstanceState != null){
        		return;
        	}
        	
        	Splash splashscr = new Splash();
        	getSupportFragmentManager().beginTransaction()
        	.add(R.id.fragment_container, splashscr).commit();
        	}
    }  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    @Override
    public void onResume() {
    	super.onResume(); 
    	String context = Context.LOCATION_SERVICE;
    	mLocationManager = (LocationManager) getSystemService(context);
    	getLocation(); 
    	
    	
    } 

  
    
  private void getLocation() { //Gets the new location using certain criteria. It checks every 15s and only changes the location if the distance with the previous point is at least 50m
        
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        String provider = mLocationManager.getBestProvider(criteria, false);
        
        mLocationManager.requestLocationUpdates(provider, 0, 0,
                mLocationListener); //For easier testing put the values to 0 and 0 instead of 15000 and 50
        
    }
  
  private final LocationListener mLocationListener = new LocationListener() {
      
      // @Override
       public void onStatusChanged(String provider, int status, Bundle extras) {
           // TODO Auto-generated method stub
       }
       
      // @Override
       public void onProviderEnabled(String provider) {
           // TODO Auto-generated method stub
           
       }
       
      // @Override
       public void onProviderDisabled(String provider) {
           // TODO Auto-generated method stub
           
       }
       
	public void onLocationChanged(final Location location) { //When the location has changed(enough) we want to update the longitude and latitude and display them
           
           double lat = (double) (location.getLatitude());
           double lng = (double) (location.getLongitude());
           Date time = new Date();
           
           Locations loc = new Locations(); //Makes a location and adds it to our location array
           loc.setLocationDate(time);
           loc.setLocationLat(lat);
           loc.setLocationLong(lng);
           locationArray.add(loc);
           
          if(first<1){ //Checks if it is the first time that a the location changed, if so it switches fragments
  
        	Main main = new Main();
        	
           	FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
           	transaction.replace(R.id.fragment_container, main);
           	transaction.commit();
           	
          }
          first++;
               
         // It is not possible to set these textfields here for some reason, it is possible in the onResume(). Why?
         // Main.longitudeField.setText(String.valueOf(lng));
         // Main.latitudeField.setText(String.valueOf(lat));
         // Main.dateField.setText(String.valueOf(time));
         

       }
   };
}
