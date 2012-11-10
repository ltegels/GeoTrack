	package com.example.geotrack3;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;




	 
public class Map extends MapActivity {
	  public TextView longitudeField;
	  
		public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_map);
	        MapView mapView = (MapView) findViewById(R.id.mapview);
	        mapView.setBuiltInZoomControls(true);
	        
	       // longitudeField = (TextView) findViewById(R.id.latitudeMap);
	        
	       // longitudeField.setText(String.valueOf(MainActivity.longitudeArray.get(0)));
	        
	        
	        Button back = (Button) findViewById(R.id.back); 
	        
	        back.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					finish();
					
				}
			});
	        
	       
	        
	      
	      
	        }

		@Override
		protected boolean isRouteDisplayed() {
			// TODO Auto-generated method stub
			return false;
		}
		
	
		   

		
	 
	    }
	