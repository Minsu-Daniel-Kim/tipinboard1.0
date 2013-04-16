package com.corping.dashboard;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.corping.R;
import com.corping.manual.ManualActivity;

public class Dashboard_List extends Activity {

	ListView listview;
	ArrayList<String> items;
	ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dashboardpage_list);
		init();
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				Toast.makeText(getApplicationContext(), position + "", 3000)
						.show();
				Intent intent = new Intent(getApplicationContext(),
						ManualActivity.class);
				startActivity(intent);

			}
		});

	}

	public void init() {

		listview = (ListView) findViewById(R.id.listView);

		items = new ArrayList<String>();
		items.add("빵사와");
		items.add("빵사와");
		items.add("빵사와");
		items.add("빵사와");
		items.add("빵사와");
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
	}
}
