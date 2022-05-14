package course.examples.UI.GridLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
	Context context;
	private static final int PADDING = 8;
	private static final int WIDTH = 345;
	private static final int HEIGHT = 345;
	private Context mContext;          // This will have to be passed to the ImageView
	private List<Integer> mThumbIds;   // Adapter must store AdapterView's items
	private List<String> mNames;
	ArrayList animal_wiki;

	// Save the list of image IDs and the context
	public ImageAdapter(Context c, List<Integer> ids, List<String> names) {
		mContext = c;
		this.mThumbIds = ids;
		this.mNames = names;
	}

	// Now the methods inherited from abstract superclass BaseAdapter

	// Return the number of items in the Adapter
	@Override
	public int getCount() {
		return mThumbIds.size();
	}

	// Return the data item at position
	@Override
	public Object getItem(int position) {
		return mThumbIds.get(position);
	}

	// Will get called to provide the ID that
	// is passed to OnItemClickListener.onItemClick()
	@Override
	public long getItemId(int position) {
		return mThumbIds.get(position);
	}

	// Return an ImageView for each item referenced by the Adapter
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View grid;
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


		// if convertView's not recycled, initialize some attributes
		if (convertView == null) {
			grid = inflater.inflate(R.layout.itemview, null);

			ImageView imageView = (ImageView)grid.findViewById(R.id.gridImage);
			TextView textView = (TextView) grid.findViewById(R.id.gridText);

			imageView.setImageResource(mThumbIds.get(position));
			textView.setText(mNames.get(position));
		}

		else {
			grid = (View) convertView;
		}

		return grid;



//		ImageView imageView = (ImageView) convertView;
//
//		// if convertView's not recycled, initialize some attributes
//		if (imageView == null) {
//			imageView = new ImageView(mContext);
//			imageView.setLayoutParams(new GridView.LayoutParams(WIDTH, HEIGHT));
//			imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
//			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//		}
//
//		imageView.setImageResource(mThumbIds.get(position));
//		return imageView;
	}
}
