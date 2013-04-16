package utils;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weero.R;

public class LazyAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<String> data;
	ArrayList<String> title;
	ArrayList<String> username;
	ArrayList<String> recomm;
	ArrayList<String> seen;
	ArrayList<String> objectId;

	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;

	public LazyAdapter(Activity a, ArrayList<String> mStrings, ArrayList<String> mObjectId) {
		activity = a;
		data = mStrings;
//		title = mTitle;
//		recomm = mRecom;
//		seen = mSeen;
		objectId = mObjectId;
//		username = mUsername;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		public TextView text;
		public ImageView image;
		public TextView username2;
		public TextView objectId2;
		public TextView seen;
		public TextView recom;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.timeline_featured_adapter, null);
			holder = new ViewHolder();
//			holder.text = (TextView) vi.findViewById(R.id.name);
			holder.image = (ImageView) vi.findViewById(R.id.image);
//			holder.username2 = (TextView) vi.findViewById(R.id.user);
			holder.objectId2 = (TextView) vi.findViewById(R.id.objectId);
//			holder.seen = (TextView) vi.findViewById(R.id.seen);
//			holder.recom = (TextView) vi.findViewById(R.id.recom);
			vi.setTag(holder);
		} else
			holder = (ViewHolder) vi.getTag();

//		holder.text.setText(title.get(position));
		holder.objectId2.setText(objectId.get(position));
//		holder.username2.setText(username.get(position));
//		holder.recom.setText(recomm.get(position));
//		holder.seen.setText(seen.get(position));

		holder.image.setTag(data.get(position));
		imageLoader.DisplayImage(data.get(position), activity, holder.image);
		return vi;
	}
}