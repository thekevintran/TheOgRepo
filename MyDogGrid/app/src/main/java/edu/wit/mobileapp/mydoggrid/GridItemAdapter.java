package edu.wit.mobileapp.mydoggrid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by trank on 9/30/2017.
 */

public class GridItemAdapter extends ArrayAdapter<ListItem> {
    private LayoutInflater mInflater;
    public GridItemAdapter(Context context, int rid, List<ListItem> list){
        super(context, rid, list);
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Log.v("Lab4", "Public View in GridItem Adapter");
        // Retrieve data
        ListItem item = (ListItem)getItem(position);
        // Use layout file to generate View
        View view = mInflater.inflate(R.layout.grid, null);
        // Set image
        ImageView image;
        image = (ImageView)view.findViewById(R.id.image2);
        image.setImageBitmap(item.image);
        // Set user name
        TextView name;
        name = (TextView)view.findViewById(R.id.name2);
        name.setText(item.name);
        // Set comment
        TextView comment;
        comment = (TextView) view.findViewById(R.id.comment2);
        comment.setText(item.comment);

        return view;
    }
}