package com.corping.main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.corping.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

@TargetApi(9)
public class MainActivity_GetStarted extends Activity {

	String password, email;

	EditText editText_email, editText_password;
	Bundle bundle = new Bundle();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_getstarted);

		init();

	}

	public void init() {

		editText_email = (EditText) findViewById(R.id.editText_email);
		editText_password = (EditText) findViewById(R.id.editText_password);

	}

	@TargetApi(9)
	public void register(View v) {

		password = editText_password.getText().toString();
		email = editText_email.getText().toString();
		if ((password.isEmpty()) || (email.isEmpty())) {
			Toast.makeText(getApplicationContext(), "모두 기입해 주세요~", 3000).show();

		} else {

			ParseUser user = new ParseUser();
			user.setUsername(email);
			user.setEmail(email);
			user.setPassword(password);
			user.signUpInBackground(new SignUpCallback() {

				@Override
				public void done(ParseException e) {

					if (e == null) {

						Toast.makeText(getApplicationContext(), "가입 완료!", 3000)
								.show();
						bundle.putString("password", password);
						Intent intent = new Intent(getApplicationContext(),
								MainActivity_Signin.class);

						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
						startActivity(intent);
						finish();

					} else {

						Toast.makeText(getApplicationContext(),
								"이메일 형식이 잘못 됐거나 이미 등록 됐습니다.", 3000).show();

					}
				}
			});
		}

	}

}