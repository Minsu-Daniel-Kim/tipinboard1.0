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

import com.corping.R;

public class LazyAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<String> data;
	ArrayList<String> title;
	ArrayList<String> mProfilePic;
	ArrayList<String> mContent;
	ArrayList<String> objectId;

	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;

	public LazyAdapter(Activity a, ArrayList<String> mStrings,
			ArrayList<String> mProfilePic, ArrayList<String> mObjectId) {
		activity = a;
		data = mStrings;
		objectId = mObjectId;
		this.mProfilePic = mProfilePic;
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
		public ImageView profilePic;
		public TextView username2;
		public TextView objectId2;
		public TextView seen;
		public TextView recom;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {
//			vi = inflater.inflate(R.layout.timeline_featured_adapter, null);
//			holder = new ViewHolder();
//			holder.profilePic = (ImageView) vi.findViewById(R.id.profilePic);
//			holder.image = (ImageView) vi.findViewById(R.id.image);
//			holder.objectId2 = (TextView) vi.findViewById(R.id.objectId);
//			vi.setTag(holder);
		}
		holder = (ViewHolder) vi.getTag();

		holder.objectId2.setText(objectId.get(position));

		holder.image.setTag(data.get(position));
		holder.profilePic.setTag(mProfilePic.get(position));
		imageLoader.DisplayImage(data.get(position), activity, holder.image);
		imageLoader.DisplayImage(mProfilePic.get(position), activity,
				holder.profilePic);

		return vi;
	}
}