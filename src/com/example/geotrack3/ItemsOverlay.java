package com.example.geotrack3;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class ItemsOverlay extends ItemizedOverlay<OverlayItem> {

	private Context mContext;
	private ArrayList<OverlayItem> mOverlay = new ArrayList<OverlayItem>();
	
	public ItemsOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public ItemsOverlay(Drawable defaultMarker, Context context)
	{
		super(boundCenterBottom(defaultMarker));
		mContext = context;
		
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return mOverlay.get(i);
	}

	@Override
	public int size() {
		
		return mOverlay.size();
	}
	
	public void addOverlay(OverlayItem overlay){
		mOverlay.add(overlay);
		this.populate();
		
	}
	
	@Override
	protected boolean onTap(int index){
		OverlayItem item = mOverlay.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
		
	}
	
	
}
