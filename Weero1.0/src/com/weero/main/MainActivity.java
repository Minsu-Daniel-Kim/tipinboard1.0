package com.weero.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.weero.R;
import com.weero.listing.ListingActivity;
import com.weero.post.PostActivity;

public class MainActivity extends Activity {
	final ParseObject student = new ParseObject("Student");
	TextView textView01, textView02;
	EditText editText01, editText02;
	ParseUser user = ParseUser.getCurrentUser();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);

	}

	public void post(View v) {

		Intent intent = new Intent(getApplicationContext(), PostActivity.class);
		startActivity(intent);

	}

	public void listing(View v) {

		Intent intent = new Intent(getApplicationContext(),
				ListingActivity.class);
		startActivity(intent);

	}
}