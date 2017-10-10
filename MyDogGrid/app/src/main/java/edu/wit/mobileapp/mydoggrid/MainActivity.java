package edu.wit.mobileapp.mydoggrid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.GridView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.graphics.BitmapFactory.decodeResource;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create image & place it at /res/drawable
        Bitmap defaultImage;
        defaultImage =
                decodeResource(getResources(), R.drawable.default_image);
        // Create currentDate string
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        //Making a list and checking it twice (Filling up the list with 50 iterations of test data
        List<ListItem> list = new ArrayList<ListItem>();
        ListItem item;
        for(int i=1; i<=50; i++){
            item = new ListItem();
            item.image = defaultImage;
            item.name = "Title-" + i;
            item.comment = currentDate;
            list.add(item);
        }

//        Create GridItemAdapter
//        Log.v("Lab4", "Create GridItemAdapter");
        GridItemAdapter adapter;
        adapter = new GridItemAdapter(this, 0, list);
//        Assign ListItem to GridView
//        Log.v("Lab4", "Assign GridItemAdapter adapter to  gridView");
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
    }
}
