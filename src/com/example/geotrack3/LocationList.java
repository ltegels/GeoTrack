package com.example.geotrack3;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;



public class LocationList extends ListActivity {
	
	private ProgressDialog mProgressDialog = null;
	private ArrayList<Locations> mLocations = null;
	private LocationAdapter mAdapter;
	private Runnable viewLocations;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locationlist);
		mLocations = new ArrayList<Locations>();
		this.mAdapter = new LocationAdapter(this, R.layout.row, mLocations);
		setListAdapter(this.mAdapter);
		
		Button back = (Button) findViewById(R.id.back2); //Generates a button to go back
        
        back.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				finish();
				
			}
		});
		
		viewLocations = new Runnable(){
			public void run(){
				getOrders();
				
			}
		};
		
		Thread thread = new Thread(null, viewLocations, "MagentoBackground");
		thread.start();
		mProgressDialog = ProgressDialog.show(LocationList.this, "Please wait...", "Retrieving data...", true); //Defines the loadingscreen 
	
	}
	
	private void getOrders(){
		try{
			mLocations = MainActivity.locationArray; //Gets the list of visited locations from the mainactivity
			//Thread.sleep(2000);
			
			Log.i("ARRAY", ""+ mLocations.size());
			}
		catch(Exception e){
			Log.e("BACKGROUND_PROC", e.getMessage());
			
		}
		runOnUiThread(returnRes);
		
		
	}
	
	private Runnable returnRes = new Runnable() {

        public void run() {
            if(mLocations != null && mLocations.size() > 0){
                mAdapter.notifyDataSetChanged();
                for(int i=0;i<mLocations.size();i++)
                mAdapter.add(mLocations.get(i));
            }
            mProgressDialog.dismiss();
            mAdapter.notifyDataSetChanged();
        }
      };
	
	private class LocationAdapter extends ArrayAdapter<Locations> {

        private ArrayList<Locations> items;

        public LocationAdapter(Context context, int textViewResourceId, ArrayList<Locations> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) { //Creates the list items
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.row, null); //Uses the layout for rows decribed in the row.xml layout file
                }
                Locations loc = items.get(position);
                if (loc != null) {
                        TextView tt = (TextView) v.findViewById(R.id.toptext); //Fills the information about the locations  into the list items
                        TextView tt2 = (TextView) v.findViewById(R.id.toptext2);
                        TextView bt = (TextView) v.findViewById(R.id.bottomtext);
                        if (tt != null) {
                              tt.setText("Lng: "+loc.getLocationLong());                           
                              }
                        if(tt2 != null){
                        	tt2.setText("Lat: "+loc.getLocationLat());
                        }
                        if(bt != null){
                              bt.setText(String.valueOf(loc.getLocationDate()));
                        }
                }
                return v;
        }
}

}


