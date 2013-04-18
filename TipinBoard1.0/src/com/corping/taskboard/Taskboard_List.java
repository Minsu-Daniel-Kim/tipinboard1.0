package com.corping.taskboard;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.corping.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Taskboard_List extends FragmentActivity {

	ArrayList<MyFragment> fragmentList = new ArrayList<MyFragment>();
	ViewPager pager;
	pagerAdpater adapter;
	int num;
	TextView tv_boardtitle;
	Intent intent;
	String OBJECTID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.taskboardpage_list);

		tv_boardtitle = (TextView) findViewById(R.id.tv_boardtitle);

		pager = (ViewPager) findViewById(R.id.viewpager);
		adapter = new pagerAdpater(getSupportFragmentManager());
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(10);

		// Intent intent = getIntent();
		intent = getIntent();
		OBJECTID = intent.getStringExtra("objectId");
		String boardTitle = intent.getStringExtra("boardTitle");

		tv_boardtitle.setText(boardTitle);
		// Toast.makeText(getApplicationContext(), OBJECTID, 3000).show();

		ParseQuery query = new ParseQuery("TaskBoard");
		query.whereEqualTo("objectId", OBJECTID);
		query.getFirstInBackground(new GetCallback() {

			@Override
			public void done(ParseObject object, ParseException e) {
				// TODO Auto-generated method stub
				ParseUser asdf = (ParseUser) object.get("admin");
				ParseUser myself = null;
				try {
					myself = asdf.fetch();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<ParseUser> list = (ArrayList<ParseUser>) object
						.get("user");
				for (ParseUser obj : list) {
					try {
						ParseUser user = obj.fetch();
						Bundle bundle = new Bundle();
						// bundle.putString("userid", use.getObjectId());
						bundle.putString("username", user.getUsername());
						bundle.putString("name", user.getString("name"));
						bundle.putString("boardId", OBJECTID);

						if ((ParseUser.getCurrentUser().getUsername())
								.equals(myself.getUsername())) {

							bundle.putString("usernum", "1");

						} else {
							bundle.putString("usernum", "0");
						}

						MyFragment fragment = new MyFragment();
						fragment.setArguments(bundle);
						fragmentList.add(fragment);
					} catch (Exception e2) {
					}
				}
				adapter.notifyDataSetChanged();
			}
		});

	}


	public void addemployee(View v) throws ParseException {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle("추가할분의 휴대폰번호를 기입해주세요.");
		final EditText input = new EditText(this);
		builder.setView(input);
		builder.setInverseBackgroundForced(true);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				String phone = input.getText().toString();
				Toast.makeText(getApplicationContext(), phone, 3000).show();

				try {
					inviteEmployee(phone);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();

		// phone = "01055872130";

		Toast.makeText(getApplicationContext(), "+ person!", 3000).show();

	}

	public void inviteEmployee(String phone) throws ParseException {
		ParseQuery adduser = ParseUser.getQuery();

		adduser.whereEqualTo("username", phone);
		Toast.makeText(getApplicationContext(), "+ person!" + phone, 3000)
				.show();
		final ParseUser user = (ParseUser) (adduser.getFirst());

		Toast.makeText(getApplicationContext(), user.getUsername(), 3000)
				.show();

		ParseQuery query = new ParseQuery("TaskBoard");
		query.whereEqualTo("objectId", OBJECTID);
		query.getFirstInBackground(new GetCallback() {

			@Override
			public void done(ParseObject object, ParseException e) {
				// TODO Auto-generated method stub

				object.add("user", user);
				object.saveInBackground();
			}
		});

	}

	class pagerAdpater extends FragmentPagerAdapter {

		public pagerAdpater(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}

	}
}
