package com.weero.main;

import utils.MyProgressDialog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.weero.R;

public class MainActivity_Signin extends Activity {

	EditText editText_ID, editText_password;
	ParseUser currentUser = null;
//	ProgressDialog progressdialog;

	MyProgressDialog myProgressdialog;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_signin);

		currentUser = ParseUser.getCurrentUser();
		
		init();
		
		

//		SharedPreferences myPref = getSharedPreferences("myPref",
//				Activity.MODE_WORLD_WRITEABLE);
//
//		SharedPreferences.Editor myEditor = myPref.edit();
//		myEditor.putString("username", userName);
//		myEditor.putString("password", password);
//		myEditor.commit();
//		
		
		
//		
//		SharedPreferences myPref = getSharedPreferences("myPref",
//				Activity.MODE_WORLD_WRITEABLE);
//
//		if (myPref != null) {
//			String username = myPref.getString("username", "");
//			String password = myPref.getString("password", "");
//			editText_ID.setText(username);
//			editText_password.setText(password);
//
//		}
		
	}

	public void init() {

		editText_ID = (EditText) findViewById(R.id.editText_id);
		editText_password = (EditText) findViewById(R.id.editText_password);
	}

	public void getStarted(View v) {

		Toast.makeText(getApplicationContext(), "Get Started", 3000).show();
		Intent intent = new Intent(getApplicationContext(),
				MainActivity_GetStarted.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intent);
		finish();

	}

//	public void back(View v) {
//
//		finish();
//
//	}

	public void logIn(View v) {
		
//		ParseQuery query = new ParseQuery("User");

//		myProgressdialog = MyProgressDialog.show(MainActivity_Signin.this, "", "wait!");
		
//		progressdialog = ProgressDialog.show(MainActivity_Signin.this, "",
//				"잠시만 기다려 주세요 ...", true);
		ParseUser.logInInBackground(editText_ID.getText().toString(),
				editText_password.getText().toString(), new LogInCallback() {

					@Override
					public void done(ParseUser user, ParseException e) {
						// TODO Auto-generated method stub

						
						if (e == null && user != null) {

							Toast.makeText(getApplicationContext(),
									"로그인 되었습니다!", 3000).show();

							Intent intent = new Intent(getApplicationContext(),
									MainActivity_Tab.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
									| Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(intent);
						} else if (user == null) {

							Toast.makeText(getApplicationContext(),
									"아이디 또는 비밀번호가 틀렸습니다.", 3000).show();

						} else {

							e.printStackTrace();
							Toast.makeText(getApplicationContext(),
									"가입부터 해주세요!", 3000).show();

						}
//						progressdialog.dismiss();
//						myProgressdialog.dismiss();
					}
				});
		//progressDialog.dismiss();
		

	}
}