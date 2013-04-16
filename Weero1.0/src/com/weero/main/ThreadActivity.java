package com.weero.main;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.weero.R;
import com.weero.listing.ListingActivity;
import com.weero.listing.ListingActivity_Detail;

public class ThreadActivity extends Activity {
	GridView gridView;
	TextView textview_title, textview_content;
	static final String[] list = new String[9];
	static final String[] userid = new String[9];
	ProgressDialog progressdialog;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.threadpage);

		textview_title = (TextView) findViewById(R.id.textview_title);
		textview_content = (TextView) findViewById(R.id.textview_content);

		gridView = (GridView) findViewById(R.id.gridView1);
		progressdialog = ProgressDialog.show(ThreadActivity.this, "", "잠깐!",
				true);
		// gridView.setAdapter(new ImageAdapter(this, MOBILE_OS));
		loadTextAndImage();
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Toast.makeText(
				// getApplicationContext(),
				// ((TextView) v.findViewById(R.id.grid_item_label))
				// .getText(), Toast.LENGTH_SHORT).show();

				Bundle bundle = new Bundle();

				TextView objID = (TextView) view
						.findViewById(R.id.grid_item_label);

				String objectID = objID.getText().toString();

				bundle.putString("objectId", objectID);

				Intent intent = new Intent(getApplicationContext(),
						ListingActivity_Detail.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

	}

	public void loadTextAndImage() {
		ParseQuery query = new ParseQuery("Post");
		query.orderByDescending("createdAt");
		query.setLimit(9);
		query.findInBackground(new FindCallback() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub

				if (e == null) {

					final String title = objects.get(0).getString("title");
					final String theme = objects.get(0).getString("theme");
					textview_title.setText(title);
					textview_content.setText(theme);

					int i = 0;
					for (ParseObject pObj : objects) {
						final String userId = pObj.getString("userId");
						final String objectid = pObj.getObjectId();

						list[i] = objectid;
						userid[i] = userId;
						i++;

					}
					gridView.setAdapter(new ImageAdapter(
							getApplicationContext(), list, userid));

				} else {
					Toast.makeText(getApplicationContext(), "Something wrong",
							3000).show();

				}

				progressdialog.dismiss();
			}
		});

	}

}