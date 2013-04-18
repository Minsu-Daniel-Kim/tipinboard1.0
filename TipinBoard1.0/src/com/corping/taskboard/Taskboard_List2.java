package com.corping.taskboard;

import java.util.ArrayList;

import utils.LazyAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.corping.R;
import com.corping.animation.ActivitySwitcher;
import com.corping.manual.ManualActivity_Timeline;

public class Taskboard_List2 extends Activity {

	ListView MyList;
	LazyAdapter adapter;
	ArrayList<String> items;
//	RelativeLayout header;

	// ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dashboardpage_list);
//		header = (RelativeLayout) getLayoutInflater().inflate(
//				R.layout.featured_adapter_dashboard_list, null);
		init();

		MyList.setAdapter(adapter);
		MyList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				animatedStartActivity();

				Toast.makeText(getApplicationContext(), position + "", 3000)
						.show();
				// Intent intent = new Intent(getApplicationContext(),
				// ManualActivity.class);
				// startActivity(intent);

			}
		});

	}

	protected void onResume() {
		// animateIn this activity
		ActivitySwitcher.animationIn(findViewById(R.id.container),
				getWindowManager());
		super.onResume();
	}

	private void animatedStartActivity() {
		// we only animateOut this activity here.
		// The new activity will animateIn from its onResume() - be sure to
		// implement it.
		final Intent intent = new Intent(getApplicationContext(),
				ManualActivity_Timeline.class);
		// disable default animation for new intent
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

		ActivitySwitcher.animationOut(findViewById(R.id.container),
				getWindowManager(),
				new ActivitySwitcher.AnimationFinishedListener() {
					public void onAnimationFinished() {
						startActivity(intent);
					}
				});
	}

	public void init() {

		MyList = (ListView) findViewById(R.id.listView);

		items = new ArrayList<String>();
		items.add("빵사와");
		items.add("빵사와");
		items.add("빵사와");
		items.add("빵사와");
		items.add("빵사와");
		adapter = new LazyAdapter(this, items);

		// adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, items);
	}
}
