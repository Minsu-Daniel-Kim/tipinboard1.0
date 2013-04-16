package com.weero.listing;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.weero.R;

public class ListingActivity extends Activity {

	ArrayList<MyItem> arItem;
	MyItem mi;
	MyListAdapter MyAdapter = null;
	ListView MyList;
	ParseUser user = ParseUser.getCurrentUser();
	ProgressDialog progressdialog;

	TextView textView01, textView02;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listing);
		init();

		progressdialog = ProgressDialog.show(ListingActivity.this, "",
				"잠깐!", true);
		
		arItem = new ArrayList<MyItem>();
		MyAdapter = new MyListAdapter(this, R.layout.featured_adapter, arItem);
		loadTextAndImage();

		MyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int num,
					long arg3) {
				// TODO Auto-generated method stub

				Bundle bundle = new Bundle();

				TextView objID = (TextView) view.findViewById(R.id.status);

				String objectID = objID.getText().toString();

				bundle.putString("objectId", objectID);

				Intent intent = new Intent(getApplicationContext(),
						ListingActivity_Detail.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	public void init() {

		MyList = (ListView) findViewById(R.id.list);

	}

	public void loadTextAndImage() {
		ParseQuery query = new ParseQuery("Post");
		query.orderByDescending("createdAt");
		query.findInBackground(new FindCallback() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub

				if (e == null) {

					for (ParseObject pObj : objects) {
						final String title = pObj.getString("title");
						final String content = pObj.getString("content");
						final String objectid = pObj.getObjectId();
						// final int recommended = pObj.getInt("recommended");
						// final int seen = pObj.getInt("seen");
						

						// Toast.makeText(getApplicationContext(),
						// "잠시만 기다려 주세요~", Toast.LENGTH_LONG).show();

						mi = new MyItem(null, "제목 : " + title, 0, content, objectid);
						arItem.add(mi);
						

						// ParseObject obj = pObj;
						//
						// ParseFile applicantPic = (ParseFile) obj
						// .get("myFavoritePic");
						// applicantPic.getDataInBackground(new
						// GetDataCallback() {
						//
						// @Override
						// public void done(byte[] data, ParseException e) {
						// // TODO Auto-generated method stub
						// bitmap = BitmapFactory.decodeByteArray(data, 0,
						// data.length);
						//
						// mi = new MyItem(bitmap, title, seen, name,
						// "추천수 : "+recommended);
						// arItem.add(mi);
						// MyList.setAdapter(MyAdapter);
						//
						// }
						// });

					}
					MyList.setAdapter(MyAdapter);

				} else {
					Toast.makeText(getApplicationContext(), "Something wrong",
							3000).show();

				}
				
				progressdialog.dismiss();

			}
		});

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
			// ImageView img = (ImageView) convertView.findViewById(R.id.image);
			// img.setScaleType(ImageView.ScaleType.CENTER_CROP);
			// img.setImageBitmap(arSrc.get(position).Icon);

			// img.setAdjustViewBounds(true);

			TextView status = (TextView) convertView.findViewById(R.id.status);
			status.setText(arSrc.get(position).Status);

			TextView name = (TextView) convertView.findViewById(R.id.name);
			name.setText(arSrc.get(position).Name);

			TextView date = (TextView) convertView.findViewById(R.id.day);
			date.setText(arSrc.get(position).Date);

			TextView price = (TextView) convertView.findViewById(R.id.price);
			price.setText(arSrc.get(position).Price + "");

			// Button btn = (Button)convertView.findViewById(R.id.btn);
			// btn.setOnClickListener(new Button.OnClickListener() {
			// public void onClick(View v) {
			// String str = arSrc.get(pos).Name + "를 주문합니다.";
			// Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();
			// }
			// });

			// Toast.makeText(maincon, pos+"", 3000).show();

			return convertView;
		}
	}
}