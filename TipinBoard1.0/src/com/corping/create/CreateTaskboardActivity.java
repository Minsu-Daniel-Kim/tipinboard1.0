package com.corping.create;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.corping.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class CreateTaskboardActivity extends Activity {

	EditText et_todolist, et_members;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.create_taskboard_page);
		init();
	}

	public void init() {

		et_todolist = (EditText) findViewById(R.id.et_todolist);
		et_members = (EditText) findViewById(R.id.et_members);
	}

	public void save(View v) {

		final ParseUser user = ParseUser.getCurrentUser();

		final String todolist = et_todolist.getText().toString();
		String member = et_members.getText().toString();
		
		ParseQuery query = ParseUser.getQuery();
		query.whereEqualTo("username", member);	
		query.getFirstInBackground(new GetCallback() {
			
			@Override
			public void done(ParseObject object, ParseException e) {
				// TODO Auto-generated method stub
				ParseUser user2 = (ParseUser) object;
				
				ArrayList<ParseUser> list = new ArrayList<ParseUser>();
				list.add(user);
				list.add(user2);
				final ParseObject taskboard = new ParseObject("TaskBoard");
				taskboard.put("boardTitle", todolist);
				taskboard.put("admin", user);
				taskboard.put("user", list);
				taskboard.saveInBackground();
			}
		});
//		members.add(user.getUsername());
//		members.add("01065552131");
//		members.add(member);
		

//		final ParseObject taskboard = new ParseObject("TaskBoard");
//		taskboard.put("boardTitle", todolist);
//		taskboard.put("members", user);
//		taskboard.saveInBackground();
				// query.getFirstInBackground(new GetCallback() {
				//
				// @Override
				// public void done(ParseObject object, ParseException e) {
				// // TODO Auto-generated method stub
				// ParseUser person = (ParseUser) object.get("username");
				// ParseRelation relation = person.getRelation("taskboards");
				// relation.add(taskboard);
				// person.saveInBackground();
				// }
				// });

//			}
//		});
		// taskboard.saveInBackground(new SaveCallback() {
		//
		// @Override
		// public void done(ParseException e) {
		// // TODO Auto-generated method stub
		//
		// ParseRelation relation = user.getRelation("taskboards");
		// relation.add(taskboard);
		// user.saveInBackground();
		//
		// }
		// });
		// taskboard.saveInBackground(new SaveCallback() {
		//
		// @Override
		// public void done(ParseException e) {
		// // TODO Auto-generated method stub
		// ParseQuery query = new ParseQuery("TaskBoard");
		// query.whereContains("boardTitle", todolist);
		// query.getFirstInBackground(new GetCallback() {
		//
		// @Override
		// public void done(final ParseObject object, ParseException e) {
		// // TODO Auto-generated method stub
		//
		// ArrayList<String> members = (ArrayList<String>) object
		// .get("members");
		//
		// for (final String member : members) {
		//
		// ParseQuery query = new ParseQuery("User");
		// query.whereEqualTo("username", member);
		// query.getFirstInBackground(new GetCallback() {
		//
		// @Override
		// public void done(ParseObject obj,
		// ParseException e) {
		// // TODO Auto-generated method stub
		// ParseUser user = obj.getParseUser(member);
		// ParseRelation relation = user
		// .getRelation("Taskboards");
		// relation.add(object);
		// user.saveInBackground();
		// }
		// });
		//
		// }
		//
		// }
		// });
		//
		// }
		// });

		finish();

	}
}
