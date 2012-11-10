package com.example.geotrack3;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.location.*;

public class MainActivity extends Activity {

	public LocationManager mLocationManager;
	private TextView latitudeField; //Defines the fields to display the current longitude and latitude
	private TextView longitudeField;
	private TextView dateField;
	static public ArrayList<Double> longitudeArray = new ArrayList<Double>();
	static public ArrayList<Double> latitudeArray = new ArrayList<Double>();
	static public ArrayList<Date> dateArray = new ArrayList<Date>();
	static public ArrayList<Locations> locationArray= new ArrayList<Locations>();
	
	
	
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        latitudeField =(TextView) findViewById(R.id.ValueLatitude);
        longitudeField = (TextView) findViewById(R.id.ValueLongitude);
        dateField = (TextView) findViewById(R.id.date);
        
        
        
        
        
        Button toMaps = (Button) findViewById(R.id.toMaps); //Creating the button to enter the mapview
        Button toList = (Button) findViewById(R.id.toList); //Creating the button to the listview
        
        toMaps.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent maps = new Intent(getApplicationContext(), Map.class);
				
				startActivity(maps);
				
			}
		});
        
        toList.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent list = new Intent(getApplicationContext(), LocationList.class);
				
				startActivity(list);
				
			}
		});
        
     
        
        
        
        
    }
    @Override
    public void onResume() {
    	super.onResume(); 
    	String context = Context.LOCATION_SERVICE;
    	mLocationManager = (LocationManager) getSystemService(context);
    	getLocation();
    	
    	
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
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
        
        mLocationManager.requestLocationUpdates(provider, 15000, 50,
                mLocationListener);
        
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
           
           Locations loc = new Locations();
           loc.setLocationDate(time);
           loc.setLocationLat(lat);
           loc.setLocationLong(lng);
           locationArray.add(loc);
           
           
           
           
           
           
           longitudeField.setText(String.valueOf(lng));
           latitudeField.setText(String.valueOf(lat));
           dateField.setText(String.valueOf(time));
           
           
           longitudeArray.add(lng); //Add the information about this location to a list
           latitudeArray.add(lat);
           dateArray.add(time);
           
       	
       }
   };
}
