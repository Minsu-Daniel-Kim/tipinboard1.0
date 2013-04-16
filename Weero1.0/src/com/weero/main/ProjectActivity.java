package com.weero.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.weero.R;

public class ProjectActivity extends Activity {
	final ParseObject student = new ParseObject("Student");
	TextView textView01, textView02;
	EditText editText01, editText02;
	ParseUser currentUser = ParseUser.getCurrentUser();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_intro);
		

//		currentUser = null;
		
		//progressDialog = ProgressDialog.show(ProjectActivity.this, "", "Loading...");

		if (currentUser != null) {

			Toast.makeText(getApplicationContext(), "Hi!", 5000).show();
			Intent intent = new Intent(getApplicationContext(),
					MainActivity_Tab.class);

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