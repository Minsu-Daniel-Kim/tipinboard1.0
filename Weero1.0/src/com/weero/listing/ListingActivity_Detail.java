package com.weero.listing;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.weero.R;

public class ListingActivity_Detail extends Activity {

	TextView textvew_title, textvew_content;
	EditText edittext_comment;
	ListView listview;
	ArrayList<MyItem> arItem;
	MyItem mi;
	MyListAdapter MyAdapter = null;
	Bundle bundle;
	ProgressDialog progressdialog;
	Button done;
	
	static String objectID;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.listing_detail);

		init();

		progressdialog = ProgressDialog.show(ListingActivity_Detail.this, "",
				"잠시만 기다려 주세요 ...", true);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		bundle = getIntent().getExtras();
		objectID = bundle.getString("objectId");

		listview.setDivider(null);
		Toast.makeText(getApplicationContext(), objectID, 3000).show();

		// ////////////////////
		ParseQuery query = new ParseQuery("Post");
		query.whereEqualTo("objectId", objectID);
		query.findInBackground(new FindCallback() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub

				String title = objects.get(0).getString("title");
				String content = objects.get(0).getString("content");
				textvew_title.setText(title);
				textvew_content.setText(content);
			}
		});

		// /////////////////
		arItem = new ArrayList<MyItem>();
		MyAdapter = new MyListAdapter(this, R.layout.featured_adapter_2, arItem);
		// ///////////////////////////////////////////////////////////////////////////////////////

		getComment(objectID);

	}

	public void getComment(String objectID) {

		ParseQuery innerquQuery = new ParseQuery("Post");
		ParseQuery queryForComment = new ParseQuery("Comment");
		queryForComment.whereEqualTo("object", objectID);
		queryForComment.whereMatchesQuery("post", innerquQuery);
		queryForComment.findInBackground(new FindCallback() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				for (ParseObject obj : objects) {

					String content = obj.getString("content");
					String username = obj.getString("user");

					mi = new MyItem(null, content, 0, username, null);
					arItem.add(mi);

				}
				// MyAdapter.notifyDataSetChanged();

				listview.setAdapter(MyAdapter);
			
				progressdialog.dismiss();
				// MyAdapter.notifyDataSetChanged();
				
			}
		});
		
		

	}

	public void insertComment(View v) {

		progressdialog = ProgressDialog.show(ListingActivity_Detail.this, "",
				"잠시만 기다려 주세요 ...", true);

		// Toast.makeText(getApplicationContext(), "Comment", 3000).show();

		String comment = edittext_comment.getText().toString();
		Toast.makeText(getApplicationContext(), comment, 3000).show();

		final String objectID = bundle.getString("objectId");
		ParseQuery query = new ParseQuery("Post");
		query.whereEqualTo("objectId", objectID);
		query.findInBackground(new FindCallback() {

			@TargetApi(9)
			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub

				ParseUser user = ParseUser.getCurrentUser();

				String username = user.getUsername();

				String comment = edittext_comment.getText().toString();

				if (comment.isEmpty()) {
					Toast.makeText(getApplicationContext(), "댓글이 비어 있으면 안돼요~",
							3000).show();
				} else {
					// arItem.addAll(comment);
					// mi = new MyItem(null, comment, 0, username, null);
					// arItem.add(mi);
					// ((BaseAdapter) listview.getAdapter())
					// .notifyDataSetChanged();

					String obj = objectID;
					ParseObject myComment = new ParseObject("Comment");
					myComment.put("content", comment);
					myComment.put("post", objects.get(0));
					myComment.put("object", obj);
					myComment.put("user", username);
					myComment.saveInBackground();
					///////////////// 완료 키 누르고서 키워드창 내려가는 코드
					InputMethodManager imm = (InputMethodManager)done.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		            imm.hideSoftInputFromWindow(done.getWindowToken(), 0);
		            ////////////////////
					mi = new MyItem(null, comment, 0, username, null);
					arItem.add(mi);

					MyAdapter.notifyDataSetChanged();
				}
				


				edittext_comment.setText("");
				listview.setStackFromBottom(true);
				listview.setTranscriptMode(2);
		
				progressdialog.dismiss();

			}
		});
		
		

	}

	public void init() {

		// textvew_title = (TextView) findViewById(R.id.textView_title);
		// textvew_content = (TextView) findViewById(R.id.textView_content);
		edittext_comment = (EditText) findViewById(R.id.editText_comment);
		listview = (ListView) findViewById(R.id.list);
		done = (Button) findViewById(R.id.insertComment);

		LinearLayout header = (LinearLayout) getLayoutInflater().inflate(
				R.layout.listing_detail_header, null);
		textvew_content = (TextView) header.findViewById(R.id.textView_content);
		textvew_title = (TextView) header.findViewById(R.id.textView_title);
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
	
	public void refresh(View v){
		arItem.clear();
		getComment(objectID);
	}
	
	public void editText_comment(View v){
		
		listview.setStackFromBottom(true);
		listview.setTranscriptMode(2);
	}

}