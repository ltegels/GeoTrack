package com.example.geotrack3;

import java.util.Date;

public class Locations {

	private double locationLong;
	private double locationLat; 
	private Date locationDate;
	
	public double getLocationLong(){
		return locationLong;
	}
	
	public void setLocationLong(double lng){
		this.locationLong = lng;
	}
	
	public double getLocationLat(){
		return locationLat;
	}
	
	public void setLocationLat(double lat){
		this.locationLat = lat;
	}
	
	public Date getLocationDate(){
	return locationDate;
	}
	
	public void setLocationDate(Date date){
		this.locationDate = date;
	}
}
