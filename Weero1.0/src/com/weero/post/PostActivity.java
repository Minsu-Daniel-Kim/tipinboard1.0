package com.weero.post;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.weero.R;

public class PostActivity extends Activity {
	/** Called when the activity is first created. */

	EditText editText_title, editText_content;
	String title, content;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);

		init();
	}

	public void init() {

		editText_title = (EditText) findViewById(R.id.editText_title);
		editText_content = (EditText) findViewById(R.id.editText_content);

	}

	@TargetApi(9)
	public void complete(View v) {

		ParseUser user = ParseUser.getCurrentUser();

//		Toast.makeText(getApplicationContext(), "complete", 3000).show();

		title = editText_title.getText().toString();
		content = editText_content.getText().toString();

////////타이틀 + 컨텐츠 저장
		if(title.isEmpty() || content.isEmpty()){
			Toast.makeText(getApplicationContext(), "제목이나 내용은 비어 있으면 안돼요~", 3000)
			.show();
			
		}else{
		
		ParseObject myPost = new ParseObject("Post");
		myPost.put("title", title);
		myPost.put("content", content);
		myPost.put("user", user);
		myPost.saveInBackground();
		
		finish();
		}
	}

}