package com.corping.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.corping.R;
import com.corping.main.ProjectActivity;
import com.corping.mypage.MypageActivity;
import com.parse.ParseUser;

public class DashboardActivity extends Activity {
	TextView tv_comment;
	ImageView iv_contentPic;
	SurfaceHolder holder;
	TextView tv_username;
	String videoUrl;
	ParseUser user = ParseUser.getCurrentUser();
	GridView gridView;

	static final String[] MOBILE_OS = new String[] { "Android", "iOS",
			"Windows", "Blackberry", "Blackberry", "Blackberry" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dashboardpage);
		init();

		gridView = (GridView) findViewById(R.id.gridView1);

		gridView.setAdapter(new ImageAdapter(this, MOBILE_OS));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(
						getApplicationContext(),
						((TextView) v.findViewById(R.id.grid_item_label))
								.getText(), Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(),
						Dashboard_List.class);
				startActivity(intent);

			}
		});

	}

	public void init() {

		tv_username = (TextView) findViewById(R.id.tv_username);
		String username = user.getUsername();
		tv_username.setText(username);

		// SurfaceView surface = new SurfaceView(this);
		// holder = surface.getHolder();
		// holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		// FrameLayout frame = (FrameLayout) findViewById(R.id.videoLayout);
		// frame.addView(surface);
	}

	public void myPage(View v) {

		Toast.makeText(getApplicationContext(), "My page", 3000).show();
		Intent intent = new Intent(getApplicationContext(),
				MypageActivity.class);
		startActivity(intent);

	}

	// @Override
	// protected void onResume() {
	// // TODO Auto-generated method stub
	// super.onResume();
	// getPosts();
	// }
	//
	// public void getPosts() {
	//
	// ParseQuery query = new ParseQuery("Post");
	// query.orderByDescending("createdAt");
	// query.findInBackground(new FindCallback() {
	//
	// @Override
	// public void done(List<ParseObject> objects, ParseException e) {
	// // TODO Auto-generated method stub
	// ParseObject obj = objects.get(0);
	//
	//
	// ParseFile videoFile = (ParseFile) obj.get("contentVideo");
	// videoUrl = videoFile.getUrl();
	// Toast.makeText(getApplicationContext(), videoUrl, 3000).show();
	// }
	// });
	//
	//
	//
	// }

	// public void detail(View v) {
	//
	// Intent intent = new Intent(getApplicationContext(),
	// Dashboard_List.class);
	// startActivity(intent);
	//
	// }

	// public void play(View v) {
	//
	// ParseQuery query = new ParseQuery("Post");
	// query.orderByDescending("createdAt");
	// query.findInBackground(new FindCallback() {
	//
	// @Override
	// public void done(List<ParseObject> objects, ParseException e) {
	// // TODO Auto-generated method stub
	// ParseObject obj = objects.get(0);
	//
	// ParseFile videoFile = (ParseFile) obj.get("contentVideo");
	// videoUrl = videoFile.getUrl();
	// // Toast.makeText(getApplicationContext(), videoUrl,
	// // 3000).show();
	// Intent intent = new Intent(getApplicationContext(),
	// VideoPlayActivity.class);
	// intent.putExtra("url", videoUrl);
	// startActivity(intent);
	// }
	// });
	//
	// }

	public void logout() {

		Toast.makeText(getApplicationContext(), "로그아웃!", 3000).show();

		ParseUser.logOut();

		Intent intent = new Intent(getApplication(), ProjectActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(intent);
		finish();

	}

}
