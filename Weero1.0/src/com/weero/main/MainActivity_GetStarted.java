package com.weero.main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.weero.R;

@TargetApi(9)
public class MainActivity_GetStarted extends Activity {
	final int ACTIVITY_SELECT_IMAGE = 100;
	ImageButton imageButton;
	byte[] b;
	EditText editText_name, editText_id, editText_email, editText_password;
	Bundle bundle = new Bundle();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_getstarted);

		init();

	}

	public void init() {

	//	editText_name = (EditText) findViewById(R.id.editText_name);
		editText_id = (EditText) findViewById(R.id.editText_id);
		editText_email = (EditText) findViewById(R.id.editText_email);
		editText_password = (EditText) findViewById(R.id.editText_password);
	//	imageButton = (ImageButton) findViewById(R.id.imageButton);

	}

	@TargetApi(9)
	public void register(View v) {


		String userName = null, password = null, email = null;
		
		 userName = editText_id.getText().toString();
		password = editText_password.getText().toString();
		 email = editText_email.getText().toString();
//		nameOfuser = editText_name.getText().toString();

		 
		if ((userName.isEmpty()) || (password.isEmpty()) || (email.isEmpty())){
			Toast.makeText(getApplicationContext(), "모두 기입해 주세요~", 3000).show();
			
		}else{
			
			ParseUser user = new ParseUser();
			user.setUsername(userName);
			user.setEmail(email);
			user.setPassword(password);
//			user.put("NameOfUser", nameOfuser);

//			SharedPreferences myPref = getSharedPreferences("myPref",
//					Activity.MODE_WORLD_WRITEABLE);
//
//			SharedPreferences.Editor myEditor = myPref.edit();
//			myEditor.putString("username", userName);
//			myEditor.putString("password", password);
//			myEditor.commit();

			user.signUpInBackground(new SignUpCallback() {

				@Override
				public void done(ParseException e) {

					if (e == null) {

						Toast.makeText(getApplicationContext(), "가입 완료!", 3000)
								.show();

		//				uploadImage(b);

						Intent intent = new Intent(getApplicationContext(),
								MainActivity_Signin.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		//				intent.putExtras(bundle);
						finish();
						startActivity(intent);

					} else {

						Toast.makeText(getApplicationContext(),
								"이메일 형식이 잘못 됐거나 이미 등록 됐습니다.", 3000).show();

					}
				}
			});
		}
	}
//
//	public void signIn(View v) {
//		Toast.makeText(getApplicationContext(), "Sign In", 3000).show();
//	}

	public void back(View v) {

		finish();

	}

//	public void selectImage(View v) {
//
//		// Toast.makeText(getApplicationContext(), "Select image", 3000).show();
//		Intent i = new Intent(Intent.ACTION_PICK,
//				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//		startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
//
//	}
//
//	public void uploadImage(byte[] picture) {
//		ParseFile file = new ParseFile("userProfile.png", picture);
//		file.saveInBackground();
//		ParseUser user = ParseUser.getCurrentUser();
//		user.put("myPicture", file);
//		user.saveInBackground();
//
//	}


}