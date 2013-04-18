package com.corping.taskboard;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.corping.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MyFragment extends Fragment {
	ListView MyList;
	// LazyAdapter adapter;
	ArrayList<String> items = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	TextView content, aaaa, tv_username, tv_user;
	LinearLayout linear_bottom, linear_bottom_textfield;
	Button bt_insert_complete;
	ImageButton image_bt_insert;
	EditText et_insert;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Bundle bundle = getArguments();
		final String boardId = bundle.getString("boardId");
		final String userName = bundle.getString("username");
		final String name = bundle.getString("name");


		View view = inflater.inflate(R.layout.fragment, null, false);

		MyList = (ListView) view.findViewById(R.id.listView);
		tv_username = (TextView) view.findViewById(R.id.tv_username);
		linear_bottom = (LinearLayout) view.findViewById(R.id.linear_bottom);
		linear_bottom_textfield = (LinearLayout) view
				.findViewById(R.id.linear_bottom_textfield);
		et_insert = (EditText) view.findViewById(R.id.et_insert);
		image_bt_insert = (ImageButton) view.findViewById(R.id.image_bt_insert);
		bt_insert_complete = (Button) view
				.findViewById(R.id.bt_insert_complete);

		image_bt_insert.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Toast.makeText(getActivity(), "wow!", 3000).show();
				linear_bottom.setVisibility(View.GONE);
				linear_bottom_textfield.setVisibility(View.VISIBLE);
			}
		});

		bt_insert_complete.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), et_insert.getText().toString(),
						3000).show();
				String item = et_insert.getText().toString();
				items.add(item);
				linear_bottom_textfield.setVisibility(View.GONE);
				linear_bottom.setVisibility(View.VISIBLE);
				adapter.notifyDataSetChanged();
				insertTask(boardId, userName, item);
				et_insert.setText("");
				InputMethodManager imm = (InputMethodManager) v.getContext()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				
				


			}

		});

		
		String usernum = bundle.getString("usernum");

		Toast.makeText(getActivity(), usernum, 3000).show();

		if (usernum.equals("1")) {

			linear_bottom.setVisibility(View.VISIBLE);

		}

		tv_username.setText(name);
//		Toast.makeText(getActivity(), boardId + "/" + userName, 3000).show();

		ParseQuery query = new ParseQuery("TodoList");
		query.whereEqualTo("boardId", boardId);
		query.whereEqualTo("username", userName);
		query.findInBackground(new FindCallback() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				for (ParseObject obj : objects) {

					String item = obj.getString("item");
					items.add(item);
//					Toast.makeText(getActivity(), items.get(0), 3000).show();

				}
				adapter = new ArrayAdapter<String>(getActivity(),
						android.R.layout.simple_list_item_1, items);
				MyList.setAdapter(adapter);
			}
		});

		MyList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				Toast.makeText(getActivity(), position + "", 3000).show();

			}

		});
		// adapter.notifyDataSetChanged();
		return view;
	}

	public void insertTask(String boardId, String username, String item){
		
		
		ParseObject insertTask = new ParseObject("TodoList");
		insertTask.put("boardId", boardId);
		insertTask.put("username", username);
		insertTask.put("item", item);
		insertTask.saveInBackground();
		
		
	}

	// public void insert(View v) {
	//
	// Toast.makeText(getActivity(), "wow!", 3000).show();
	// linear_bottom.setVisibility(View.GONE);
	// linear_bottom_textfield.setVisibility(View.VISIBLE);
	//
	// }
}
