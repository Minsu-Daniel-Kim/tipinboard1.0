package com.corping.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.corping.R;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private final String[] mobileValues;
 
	public ImageAdapter(Context context, String[] mobileValues) {
		this.context = context;
		this.mobileValues = mobileValues;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
 
			gridView = new View(context);
 
			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.featured_adapter_grid_items, null);
 
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(mobileValues[position]);
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
 
			String mobile = mobileValues[position];
 
			if (mobile.equals("Windows")) {
				imageView.setBackgroundResource(R.drawable.movie);
			} else if (mobile.equals("iOS")) {
				imageView.setBackgroundResource(R.drawable.restaurant);
			} else if (mobile.equals("Blackberry")) {
				imageView.setBackgroundResource(R.drawable.house);
			} else {
				imageView.setBackgroundResource(R.drawable.trip);
			}
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	public int getCount() {
		return mobileValues.length;
	}
 
	public Object getItem(int position) {
		return null;
	}
 
	public long getItemId(int position) {
		return 0;
	}
 
}