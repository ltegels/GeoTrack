	package com.example.geotrack3;



import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;




	 
public class Map extends MapActivity {
	 public TextView longitudeField;
	 public static Locations currentLoc;
	  
	 public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_map);
	       MapView mapView = (MapView) findViewById(R.id.mapview);
	       mapView.setBuiltInZoomControls(true);
	        
	       
	       displayFromList(mapView); //Checks if we came from the listview by looking if currentLoc is empty, if there is a object it is drawn
	    
	        
	        Button back = (Button) findViewById(R.id.back); 
	        back.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					finish();	
				}
			});
	        
	        Button show = (Button) findViewById(R.id.showAll); 
	        show.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					showAll();
				}
			});
	      }
		
		private void showAll(){
			MapView mapView = (MapView) findViewById(R.id.mapview);
			List<Overlay> mapOverlay = mapView.getOverlays();
		       Drawable icon = this.getResources().getDrawable(R.drawable.ic_launcher);
		       ItemsOverlay itemOverlay = new ItemsOverlay(icon, this);
		       
			for(int i=0;i<MainActivity.locationArray.size();i++){
				Locations location = MainActivity.locationArray.get(i); //gets the i-th item from the locationarray/all the locations that have been visited
				int lng = (int) (location.getLocationLong()*1000000);
				int lat = (int) (location.getLocationLat()*1000000);
				
				GeoPoint point = new GeoPoint(lat, lng);
				
				OverlayItem here = new OverlayItem(point, String.valueOf(location.getLocationDate()), "Lat: "+String.valueOf(location.getLocationLat())+" Long: "+String.valueOf(location.getLocationLong()));
				itemOverlay.addOverlay(here);
				
			}
			mapOverlay.add(itemOverlay);
		} 
		
		private void displayFromList(MapView mapView){
			
			List<Overlay> mapOverlay = mapView.getOverlays();
	       	Drawable icon = this.getResources().getDrawable(R.drawable.ic_launcher);
	       	ItemsOverlay itemOverlay = new ItemsOverlay(icon, this);
	       
		   if(currentLoc != null)
	       {
	    	   int lng = (int) (currentLoc.getLocationLong()*1000000); //Convert the long and lat to microdegrees which geopoint needs
	    	   int lat = (int) (currentLoc.getLocationLat()*1000000);
	    	GeoPoint point = new GeoPoint(lat, lng);
	    	
	    	OverlayItem location = new OverlayItem(point, String.valueOf(currentLoc.getLocationDate()), "Lat: "+String.valueOf(currentLoc.getLocationLat())+" Long: "+String.valueOf(currentLoc.getLocationLong()));
	    	itemOverlay.addOverlay(location);
	    	
	    	mapOverlay.add(itemOverlay);
	    	
	    	currentLoc=null;
	       }
		} 
		
		

		@Override
		protected boolean isRouteDisplayed() {
			// TODO Auto-generated method stub
			return false;
		}
		
	
		   

		
	 
	    }
	