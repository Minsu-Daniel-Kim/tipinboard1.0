package com.weero.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.weero.R;
import com.weero.example.TimelineActivity;
import com.weero.listing.ListingActivity;

public class MainActivity_Tab extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainpage_tab);

		TabHost tabHost = getTabHost();

		LayoutInflater layout = getLayoutInflater();
//		View tab_best = layout.inflate(R.layout.tab_bestpage, null);
//		View tab_event = layout.inflate(R.layout.tab_eventpage, null);
//		View tab_post = layout.inflate(R.layout.tab_post, null);
//		View tabe_keep = layout.inflate(R.layout.tab_keeppage, null);
//		View tab_morepage = layout.inflate(R.layout.tab_morepage, null);

		// BestActivity_tab
		Intent best_activity = new Intent(this, ListingActivity.class);
		TabSpec tabSpecBest = tabHost.newTabSpec("best").setIndicator("홈")
				.setContent(best_activity);

		// EventActivity_tab
		Intent event_activity = new Intent(this, ListingActivity.class);
		TabSpec tabSpecEvent = tabHost.newTabSpec("event").setIndicator("프로필")
				.setContent(event_activity);

		// PostActivity_tab
		Intent post_activity = new Intent().setClass(this,
				ThreadActivity.class);
		TabSpec tabSpecPost = tabHost.newTabSpec("Post").setIndicator("방")
				.setContent(post_activity);

		// KeepActivity_tab
		Intent keep_activity = new Intent().setClass(this,
				TimelineActivity.class);
		TabSpec tabSpecKeep = tabHost.newTabSpec("keep").setIndicator("더보기")
				.setContent(keep_activity);

		// MoreActivity_tab
//		Intent more_activity = new Intent().setClass(this,
//				ListingActivity.class);
//		TabSpec tabSpecMore = tabHost.newTabSpec("more").setIndicator(tab_morepage)
//				.setContent(more_activity);

		// add all tabs
		tabHost.addTab(tabSpecEvent);
		tabHost.addTab(tabSpecBest);
		tabHost.addTab(tabSpecPost);
		tabHost.addTab(tabSpecKeep);
//		tabHost.addTab(tabSpecMore);

		// set Windows tab as default (zero based)
		tabHost.setCurrentTab(3);

	}

}
