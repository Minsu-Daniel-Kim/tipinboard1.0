package com.corping.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.corping.R;
import com.corping.dashboard.DashboardActivity;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class ProjectActivity extends Activity {

	ParseUser currentUser = ParseUser.getCurrentUser();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpened(getIntent());
		
		String email = currentUser.getString("email");
		
		if (currentUser != null && email != null) {

			Toast.makeText(getApplicationContext(), "Hi!", 5000).show();
			Intent intent = new Intent(getApplicationContext(),
					DashboardActivity.class);

			startActivity(intent);
			finish();

		} else {

			Toast.makeText(getApplicationContext(), "로그인 해주세요~", 5000).show();

		}
	
	}
	
	public void getStarted(View v) {

		 Toast.makeText(getApplicationContext(), "Get Started", 3000).show();
		Intent intent = new Intent(getApplicationContext(),
				MainActivity_GetStarted.class);
		startActivity(intent);

	}

	public void signIn(View v) {

		 Toast.makeText(getApplicationContext(), "Sign In", 3000).show();
		Intent intent = new Intent(getApplicationContext(),
				MainActivity_Signin.class);
		startActivity(intent);

	}
	

}
