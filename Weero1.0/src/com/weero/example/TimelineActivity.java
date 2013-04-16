package com.weero.example;

import java.util.ArrayList;
import java.util.List;

import utils.LazyAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.weero.R;

public class TimelineActivity extends Activity {
	private static final int[] ITEM_DRAWABLES = { R.drawable.ic_launcher,
		R.drawable.ic_launcher, R.drawable.ic_launcher };
	Bitmap bitmap = null;
	TextView textView_Temp1, textView_Temp2;
	ImageView imageView_Temp, imageView_main;
	ImageButton recentSort, popularSort;
	ParseUser user = ParseUser.getCurrentUser();
	ListView MyList;

	private static final int ALERT_DIALOG1 = 1;
	private Dialog dialog;

	RelativeLayout event_listing;

	ArrayList<String> mStrings = new ArrayList<String>();
	ArrayList<String> mTitle = new ArrayList<String>();
	ArrayList<String> mRecom = new ArrayList<String>();
	ArrayList<String> mSeen = new ArrayList<String>();
	ArrayList<String> mUsername = new ArrayList<String>();
	ArrayList<String> mObjectId = new ArrayList<String>();

	LazyAdapter adapter;

	// ///////
	int current_position = 0;

	public static final int PICK_FROM_ALBUM = 1;
	ProgressDialog progressdialog;
	Gallery mGallery;
	Bitmap[] image = new Bitmap[6];
	Button btn_edit_complete;
	static String category;
	// 100일 단위 이벤트
	String event;

	// ///////

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.timelinepage);
		init();

		Intent result = getIntent();
		// event = result.getStringExtra("event");
		loadTextAndImage();

		adapter = new LazyAdapter(this, mStrings, mObjectId);

		MyList.setAdapter(adapter);

		MyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int num,
					long arg3) {
				// TODO Auto-generated method stub

				Bundle bundle = new Bundle();

				TextView objID = (TextView) view.findViewById(R.id.objectId);

				String objectID = objID.getText().toString();

				bundle.putString("objectId", objectID);

				// Toast.makeText(getApplicationContext(), objectID+"",
				// 3000).show();

				Intent intent = new Intent(getApplicationContext(),
						TimelineActivity_Detail.class);

				intent.putExtras(bundle);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});

	}

	public void init() {

		MyList = (ListView) findViewById(R.id.list);

		LinearLayout header = (LinearLayout) getLayoutInflater().inflate(
				R.layout.timelinepage_header, null);
		// event_listing = (RelativeLayout) findViewById(R.id.event_listing);

		MyList.addHeaderView(header);

//		ArcMenu arcMenu = (ArcMenu) findViewById(R.id.arc_menu);
//
//		final int itemCount = ITEM_DRAWABLES.length;
//		for (int i = 0; i < itemCount; i++) {
//			ImageView item = new ImageView(this);
//			item.setImageResource(ITEM_DRAWABLES[i]);
//
//			final int position = i;
//			arcMenu.addItem(item, new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//
//					switch (position) {
//					case 0:
//						Intent intent0 = new Intent(getApplicationContext(),
//								MyTimelineActivity.class);
//						startActivity(intent0);
//
//						break;
//					case 1:
//						Intent intent1 = new Intent(getApplicationContext(),
//								SettingActivity.class);
//						startActivity(intent1);
//						break;
//					case 2:
//
//						break;
//					default:
//						break;
//					}
//				}
//			});// Add a menu item
	//	}

		

	}

	public void loadTextAndImage() {

		// progressdialog = ProgressDialog.show(EventActivity_Listing.this, "",
		// "잠시만요...");
		mStrings.clear();
		mTitle.clear();
		mUsername.clear();
		mObjectId.clear();
		mRecom.clear();
		mSeen.clear();

		ParseQuery query = new ParseQuery("Posts");
		query.findInBackground(new FindCallback() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub

				if (e == null) {

					// for (int i = 0; i < 5; i++) {

					for (ParseObject pObj : objects) {

//						 mTitle.add(pObj.getString("title"));
//						 mUsername.add(pObj.getString("nickname"));
					
						mObjectId.add(pObj.getObjectId());

//						 mRecom.add(pObj.getInt("recommended") + "");
//						 mSeen.add(pObj.getInt("seen") + "");

						ParseObject obj = pObj;

						ParseFile applicantPic = (ParseFile) obj
								.get("picture");

						
						
						String url = applicantPic.getUrl();
						Log.d("url", url);
						mStrings.add(url);
						
						MyList.setAdapter(adapter);
					}
					

				} else {
					Toast.makeText(getApplicationContext(), "Something wrong",
							3000).show();

				}

				// progressdialog.dismiss();
				// dialog.dismiss();
			}
		});
		
	

	}

	// public void sort(View v) {
	//
	// switch (v.getId()) {
	// case R.id.recentSort:
	//
	// Toast.makeText(getApplicationContext(), "recentsort", 3000).show();
	// loadTextAndImage("createdAt", category);
	// break;
	// case R.id.popularSort:
	// Toast.makeText(getApplicationContext(), "popularsort", 3000).show();
	// loadTextAndImage("recommended", category);
	// break;
	// }
	//
	// }

	// //////////////////////////////

	// protected Dialog onCreateDialog(int id) {
	//
	// dialog = new Dialog(this);
	// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// dialog.getWindow().getDecorView()
	// .setBackgroundResource(android.R.color.transparent);
	// dialog.setContentView(R.layout.loading);
	// ImageView imageView = (ImageView) dialog
	// .findViewById(R.id.loadingImage);
	//
	// imageView.setBackgroundResource(R.drawable.loading);
	// AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
	// anim.start();
	// dialog.setCancelable(false);
	// return dialog;
	//
	// // this.overridePendingTransition(R.anim.slide_in_left,
	// // R.anim.slide_out_right);
	//
	// }

	// ////////////////////
}