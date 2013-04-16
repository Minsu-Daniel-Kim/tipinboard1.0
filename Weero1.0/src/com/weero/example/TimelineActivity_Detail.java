package com.weero.example;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.weero.R;

public class TimelineActivity_Detail extends Activity {

	TextView textvew_title, textvew_content, textview_cost;
	static byte[] picture2 = null;
	TextView textView_eventContent, textView_titled, textView_hardness,
			textView_time, textView_cost, textView_material;
	RelativeLayout event_listing;

	LinearLayout layout_hardness, layout_timeTaken, layout_materials;

	RatingBar ratingbar;
	ImageView imageview_eventPic;
	EditText edittext_comment;
	ListView listview;
	ArrayList<MyItem> arItem;
	MyItem mi;
	MyListAdapter MyAdapter = null;
	Bundle bundle;
	ProgressDialog progressdialog;
	// MyProgressDialog myProgressdialog;
	ImageButton done;
	Button edit_button, favorite_button, delete_button;
	static String userName;
	// /////////////////ImageDialog
	private static final int ALERT_DIALOG1 = 1;
	private Dialog dialog;
	// ///////////////////
	boolean fromKeep;
	boolean mine;
	static String title, content, hardness, timeTaken, material, cost,
			username, postId, category;
	static String nickname;
	static String ownername;
	static String objectID2;
	String events;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.timeline_listing_detail);

		init();

		bundle = getIntent().getExtras();
		objectID2 = bundle.getString("objectId");

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		Toast.makeText(getApplicationContext(), objectID2 + "", 3000).show();
		chunk(objectID2);

	}

	public void chunk(String objectID) {
		getComment(objectID);
		ParseQuery query = new ParseQuery("Posts");
		query.whereEqualTo("objectId", objectID);
		query.findInBackground(new FindCallback() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub

				ParseUser user = ParseUser.getCurrentUser();

				final ParseObject object = objects.get(0);

				ParseFile applicantPic = (ParseFile) object.get("picture");

				applicantPic.getDataInBackground(new GetDataCallback() {
					//
					// @Override
					public void done(byte[] data, ParseException e) {

						picture2 = data;

						Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
								data.length);

						imageview_eventPic.setImageBitmap(bitmap);
						imageview_eventPic
								.setScaleType(ImageView.ScaleType.CENTER_CROP);
						// imageview_eventPic.setAdjustViewBounds(true);
						// progressdialog.dismiss();

					}
				});
			}
		});

		// /////////////////
		arItem = new ArrayList<MyItem>();
		MyAdapter = new MyListAdapter(this, R.layout.featured_adapter_comment,
				arItem);
		// ///////////////////////////////////////////////////////////////////////////////////////

	}

	public void getComment(String objectID) {

		ParseQuery innerquQuery = new ParseQuery("MyPost");
		ParseQuery queryForComment = new ParseQuery("Comment");
		queryForComment.whereEqualTo("object", objectID);
		queryForComment.whereMatchesQuery("post", innerquQuery);
		queryForComment.findInBackground(new FindCallback() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				for (ParseObject obj : objects) {

					String content = obj.getString("content");
					// String username = obj.getString("user");
					String nickname = obj.getString("nickname");
					mi = new MyItem(null, content, 0, nickname, null);
					arItem.add(mi);

				}
				Log.d("here2", "here2");
				listview.setAdapter(MyAdapter);
				Log.d("here3", "here3");
				// progressdialog.dismiss();

			}
		});
		Log.d("here4", "here4");

	}

	public void insertComment(View v) {

		// progressdialog =
		// ProgressDialog.show(EventActivity_Listing_Detail.this,
		// "", "잠시만 기다려 주세요 ...", true);

		String comment = edittext_comment.getText().toString();

		// final String objectID = bundle.getString("objectId");
		final String objectID = objectID2;
		ParseQuery query = new ParseQuery("MyPost");
		query.whereEqualTo("objectId", objectID);
		query.findInBackground(new FindCallback() {

			@TargetApi(9)
			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub

				ParseUser user = ParseUser.getCurrentUser();

				username = user.getUsername();

				nickname = user.getString("nickname");

				String comment = edittext_comment.getText().toString();

				if (comment.isEmpty()) {
					Toast.makeText(getApplicationContext(), "댓글이 비어 있으면 안돼요~",
							3000).show();
				} else {

					String obj = objectID;
					ParseObject myComment = new ParseObject("Comment");
					myComment.put("content", comment);
					myComment.put("nickname", nickname);

					myComment.put("post", objects.get(0));
					myComment.put("object", obj);
					myComment.put("user", username);
					myComment.saveInBackground();

					// /////////////// 완료 키 누르고서 키워드창 내려가는 코드
					InputMethodManager imm = (InputMethodManager) done
							.getContext().getSystemService(
									Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(done.getWindowToken(), 0);
					// //////////////////
					mi = new MyItem(null, comment, 0, nickname, null);
					arItem.add(mi);

					MyAdapter.notifyDataSetChanged();
				}

				edittext_comment.setText("");
				listview.setStackFromBottom(true);
				listview.setTranscriptMode(2);

				// /////////
				// ParsePush push = new ParsePush();
				// push.setChannel(userName);
				// push.setMessage("(댓글) " + nickname + " : " + comment);
				// push.sendInBackground();
				// ////////

				// JSONObject object = new JSONObject();
				// try {
				// object.put("alert", nickname + " : " + comment);
				// object.put("title", "최고의 여친 (댓글)");
				// // object.put("title", objectID);
				// object.put("action", "MyAction");
				// object.put("id", objectID);
				// ParsePush pushMessageClient1 = new ParsePush();
				// pushMessageClient1.setData(object);
				// pushMessageClient1.setChannel(userName);
				// pushMessageClient1.sendInBackground();
				// } catch (JSONException ex) {
				// ex.printStackTrace();
				// }

				// /////////

			}
		});

		// Toast.makeText(getApplicationContext(), userName, 3000).show();

	}

	public void init() {

		edittext_comment = (EditText) findViewById(R.id.editText_comment);
		listview = (ListView) findViewById(R.id.list);
		done = (ImageButton) findViewById(R.id.insertComment);

		LinearLayout header = (LinearLayout) getLayoutInflater().inflate(
				R.layout.timeline_listing_detail_header, null);

		imageview_eventPic = (ImageView) header
				.findViewById(R.id.imageView_eventpic);

		// textView_titled = (TextView)
		// header.findViewById(R.id.textView_titled);

		textView_eventContent = (TextView) header
				.findViewById(R.id.textView_eventcontent);

		listview.addHeaderView(header);

	}

	class MyItem {
		MyItem(Bitmap aIcon, String aName, int price, String date, String status) {
			Icon = aIcon;
			Price = price;
			Name = aName;
			Status = status;
			Date = date;
		}

		Bitmap Icon;
		int Price;
		String Name;
		String Status;
		String Date;
	}

	// 어댑터 클래스
	class MyListAdapter extends BaseAdapter {
		Context maincon;
		LayoutInflater Inflater;
		ArrayList<MyItem> arSrc;
		int layout;

		public MyListAdapter(Context context, int alayout,
				ArrayList<MyItem> aarSrc) {
			maincon = context;
			Inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			arSrc = aarSrc;
			layout = alayout;
		}

		public int getCount() {
			return arSrc.size();
		}

		public String getItem(int position) {
			return arSrc.get(position).Name;
		}

		public long getItemId(int position) {
			return position;
		}

		// 각 항목의 뷰 생성
		public View getView(int position, View convertView, ViewGroup parent) {
			final int pos = position;
			if (convertView == null) {
				convertView = Inflater.inflate(layout, parent, false);
			}

			TextView status = (TextView) convertView.findViewById(R.id.status);
			status.setText(arSrc.get(position).Status);

			TextView name = (TextView) convertView.findViewById(R.id.name);
			name.setText(arSrc.get(position).Name);

			TextView date = (TextView) convertView.findViewById(R.id.day);
			date.setText(arSrc.get(position).Date);

			TextView price = (TextView) convertView.findViewById(R.id.price);
			price.setText(arSrc.get(position).Price + "");

			return convertView;
		}
	}

	public void refresh(View v) {
		arItem.clear();
		getComment(objectID2);
	}

	public void editText_comment(View v) {

		listview.setStackFromBottom(true);
		listview.setTranscriptMode(2);
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			// pDlg.dismiss();
			bmImage.setImageBitmap(result);
		}
	}
}