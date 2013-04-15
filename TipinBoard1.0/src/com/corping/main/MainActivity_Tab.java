﻿package com.corping.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.corping.R;
import com.daniel.media.ChecklistActivity;
import com.daniel.media.MediaActivity;
import com.daniel.media.MediaActivity_Post;
import com.daniel.media.MediaActivity_Timeline;
import com.parse.ParseUser;

public class MainActivity_Tab extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainpage_tab);

		TabHost tabHost = getTabHost();

		LayoutInflater layout = getLayoutInflater();
		// View tab_mission = layout.inflate(R.layout.tab_missonpage, null);
		// View tab_timeline = layout.inflate(R.layout.tab_timelinepage, null);
		// View tab_status = layout.inflate(R.layout.tab_statuspage, null);

		// MissionActivity_tab
		Intent mission_activity = new Intent(this, MediaActivity_Post.class);
		TabSpec tabSpecMission = tabHost.newTabSpec("Mission")
				.setIndicator("작성").setContent(mission_activity);

		// TimelineActivity_tab
		Intent timeline_activity = new Intent(this,
				MediaActivity_Timeline.class);
		TabSpec tabSpecTimeline = tabHost.newTabSpec("TimeLine")
				.setIndicator("포스트").setContent(timeline_activity);

		Intent check_activity = new Intent()
				.setClass(this, ChecklistActivity.class);
		TabSpec tabCheckStatus = tabHost.newTabSpec("Status")
				.setIndicator("체크").setContent(check_activity);

		// StatusActivity_tab
		Intent status_activity = new Intent().setClass(this,
				MediaActivity.class);
		TabSpec tabSpecStatus = tabHost.newTabSpec("Status").setIndicator("기타")
				.setContent(status_activity);

		// add all tabs
		tabHost.addTab(tabSpecMission);
		tabHost.addTab(tabSpecTimeline);
		tabHost.addTab(tabCheckStatus);
		tabHost.addTab(tabSpecStatus);

		// set Windows tab as default (zero based)
		tabHost.setCurrentTab(1);

	}

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
