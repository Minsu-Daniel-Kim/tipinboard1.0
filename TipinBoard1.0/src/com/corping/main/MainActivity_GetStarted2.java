package com.corping.main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.corping.R;
import com.corping.taskboard.TaskboardActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

@TargetApi(9)
public class MainActivity_GetStarted2 extends Activity {

	String username, phone, verification;

	EditText et_username, et_verification;
	Intent intent;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_getstarted2);

		init();
		et_verification.setText(intent.getStringExtra("verification_number"));
	}

	public void init() {

		et_username = (EditText) findViewById(R.id.et_username);
		et_verification = (EditText) findViewById(R.id.et_verification);

		intent = getIntent();

		Toast.makeText(getApplicationContext(),
				intent.getStringExtra("verification_number"), 3000).show();
		SharedPreferences myPref = getSharedPreferences("myPref",
				Activity.MODE_WORLD_WRITEABLE);

		if (myPref != null && myPref.contains("verfication")) {
			verification = myPref.getString("verfication", "");
			phone = myPref.getString("phone", "");
		}
	}

	@TargetApi(9)
	public void register(View v) {

		username = et_username.getText().toString();
		// phone = intent.getStringExtra("phone");

		if (username.isEmpty()) {
			Toast.makeText(getApplicationContext(), "모두 기입해 주세요~", 3000).show();

		} else {

			if ((et_verification.getText().toString()).equals(verification)) {

				ParseUser user = new ParseUser();
				user.setUsername(phone);
				user.put("name", username);
				user.setPassword(phone);
				user.signUpInBackground(new SignUpCallback() {

					@Override
					public void done(ParseException e) {

						if (e == null) {

							Toast.makeText(getApplicationContext(), "가입 완료!",
									3000).show();
							// bundle.putString("password", password);
							Intent intent = new Intent(getApplicationContext(),
									TaskboardActivity.class);

							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
							startActivity(intent);
							finish();

						} else {

							Toast.makeText(getApplicationContext(),
									"이미 가입되셨습니다.", 3000).show();

						}
					}
				});
			}

		}

	}
}