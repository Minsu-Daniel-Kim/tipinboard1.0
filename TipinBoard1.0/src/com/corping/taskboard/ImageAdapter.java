package com.corping.taskboard;

import java.util.ArrayList;

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
	
	
	ArrayList<String> boardTitles;
	ArrayList<Integer> members;
	ArrayList<String> objectIds;
	public ImageAdapter(Context context, ArrayList<String> boardTitles, ArrayList<Integer> members, ArrayList<String> objectIds) {
		this.context = context;
		this.boardTitles = boardTitles;
		this.members = members;
		this.objectIds = objectIds;
		
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
			TextView tv_title = (TextView) gridView
					.findViewById(R.id.tv_title);
			TextView tv_members = (TextView) gridView
					.findViewById(R.id.tv_members);
			
			TextView objectId = (TextView) gridView
					.findViewById(R.id.objectId);
//			textView.setText(mobileValues[position]);
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
			objectId.setText(objectIds.get(position));
			tv_title.setText(boardTitles.get(position));
			tv_members.setText(members.get(position)+"");
			imageView.setBackgroundResource(R.drawable.trip);
			

//			if(position == boardTitles.size() - 1){
//				
//				imageView.setBackgroundResource(R.drawable.ic_launcher);
//				tv_title.setText("New Task");
//				tv_members.setText("");
//			}
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	public int getCount() {
		return boardTitles.size();
	}
 
	public Object getItem(int position) {
		return null;
	}
 
	public long getItemId(int position) {
		return 0;
	}
 
}