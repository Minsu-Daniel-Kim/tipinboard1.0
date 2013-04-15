package com.corping.media;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.corping.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@SuppressLint("NewApi")
public class ChecklistActivity extends Activity {
	private ListView lView;

	ArrayList<String> lv_items = new ArrayList<String>();
	ArrayAdapter<String> app;
	private String my_sel_items;
	TextView tv_percent;

	EditText et_todoitem;
	ProgressBar progressBar;
	ArrayList<Integer> checkedItem = new ArrayList<Integer>();

	SparseBooleanArray a;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.checklist);
		init();

		progressBar.setProgress(0);
		app = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, lv_items);
		lView.setAdapter(app);
		lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		lView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				Toast.makeText(getApplicationContext(), position + "", 3000)
						.show();

				return false;
			}
		});

		lView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView arg0, View arg1, int arg2,
					long arg3) {
				// List list = new ArrayList();
				// my_sel_items = new String("Selected Items");
				int num = 0;
				String sum = "";

				a = lView.getCheckedItemPositions();

				for (int i = 0; i < lv_items.size(); i++) {
					if (a.valueAt(i)) {
						/*
						 * Long val = lView.getAdapter().getItemId(a.keyAt(i));
						 * Log.v("MyData", "index=" + val.toString() +
						 * "item value="+lView.getAdapter().getItem(i));
						 * list.add(lView.getAdapter().getItemId((a.keyAt(i))));
						 */
						sum = sum + ","
								+ (String) lView.getAdapter().getItem(i);
						// count.add((String) lView.getAdapter().getItem(i));
					}
				}
				// System.out.println(num + "/" + lv_items.size());
				// System.out.println((num * 100) / lv_items.size());

				Toast.makeText(getApplicationContext(), sum + "", 3000).show();
				int result = (lView.getCheckedItemCount() * 100)
						/ lv_items.size();
				tv_percent.setText(result + "%");
				System.out.println(a);
				progressBar.setProgress(result);

			}

		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onPause();

		ArrayList<Integer> nums = new ArrayList<Integer>();

		a = lView.getCheckedItemPositions();

		String sum = "";

		for (int i = 0; i < lv_items.size(); i++) {
			if (a.valueAt(i)) {

				sum = sum + (String) lView.getAdapter().getItem(i);
				nums.add(i);
			}
		}
		Toast.makeText(getApplicationContext(), sum, Toast.LENGTH_LONG).show();

		ParseObject checklist = new ParseObject("CheckList");
		checklist.put("checked", nums);
		checklist.put("todoitem", lv_items);
		checklist.saveInBackground();

	}

	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		ParseQuery query = new ParseQuery("CheckList");
		query.orderByDescending("createdAt");
		query.getFirstInBackground(new GetCallback() {

			@Override
			public void done(ParseObject object, ParseException e) {
				// TODO Auto-generated method stub
				ArrayList<String> temp = new ArrayList<String>();
				if (object != null) {

					// Toast.makeText(getApplicationContext(), "what?",
					// 3000).show();

					checkedItem = (ArrayList<Integer>) object.get("checked");
					temp = (ArrayList<String>) object.get("todoitem");

					for (int i = 0; i < temp.size(); i++) {

						lView.setItemChecked(i, false);
						lv_items.add(temp.get(i));

					}

					for (int i = 0; i < checkedItem.size(); i++) {

						lView.setItemChecked(checkedItem.get(i), true);

					}

					int result = (lView.getCheckedItemCount() * 100)
							/ lv_items.size();
					tv_percent.setText(result + "%");
					progressBar.setProgress(result);
				}
			}
			// }
		});
	}

	public void init() {

		lView = (ListView) findViewById(R.id.listView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		tv_percent = (TextView) findViewById(R.id.tv_percent);
		// et_todoitem = (EditText) findViewById(R.id.et_todoitem);

	}

	public void done(View v) {
		String todoitem = et_todoitem.getText().toString();
		Toast.makeText(getApplicationContext(), "done", 3000).show();
		lv_items.add(todoitem);

		int result = (lView.getCheckedItemCount() * 100) / lv_items.size();
		tv_percent.setText(result + "%");
		progressBar.setProgress(result);
		app.notifyDataSetChanged();

	}

}
