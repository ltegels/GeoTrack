package com.example.geotrack3;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Fragment {
	
	public LocationManager mLocationManager;
	public static TextView latitudeField; //Defines the fields to display the current longitude and latitude
	public static TextView longitudeField;
	public static TextView dateField;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	        Bundle savedInstanceState) {
		
		
	        // Inflate the layout for this fragment
	        View view = inflater.inflate(R.layout.activity_main, container, false);
	        
	        
	      latitudeField =(TextView) view.findViewById(R.id.ValueLatitude); //Creating the field to display the current location
	      longitudeField = (TextView) view.findViewById(R.id.ValueLongitude);
	      dateField = (TextView) view.findViewById(R.id.date);
	         
	      Button toMaps = (Button) view.findViewById(R.id.toMaps); //Creating the button to enter the mapview
	      Button toList = (Button) view.findViewById(R.id.toList); //Creating the button to the listview
	         
	       toMaps.setOnClickListener(new View.OnClickListener() {
	 			
	 			public void onClick(View v) {
	 				Intent maps = new Intent(getActivity().getApplicationContext(), Map.class);
	 				
	 				startActivity(maps);
	 				
	 			}
	 		}); 
	         
	         toList.setOnClickListener(new View.OnClickListener() {
	 			
	 			public void onClick(View v) {
	 				Intent list = new Intent(getActivity().getApplicationContext(), LocationList.class);
	 				
	 				startActivity(list);
	 				
	 			}
	 		}); 
	        
	        return view;
	    }

}


