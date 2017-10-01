package edu.wit.mobileapp.mydoggrid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;
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
        // Create testing data

        List<ListItem> list = new ArrayList<ListItem>();
        ListItem item;
        for(int i=1; i<=50; i++){
            item = new ListItem();
            item.image = defaultImage;
            item.name = "Title " + i;
            item.comment = "::Date::";
            list.add(item);
        }

        //Create GridItemAdapter
        Log.v("Lab4", "Create GridItemAdapter");
        GridItemAdapter adapter;
        adapter = new GridItemAdapter(this, 0, list);
//        Assign ListItem to GridView
        Log.v("Lab4", "Assign GridItemAdapter adapter to  gridView");
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
    }
}

        /*TESTING DATA
        ListItem item1 = new ListItem();
        item1.image = defaultImage;
        item1.name = "David";
        item1.comment = "Boston is not snowing now.";
        list.add(item1);
        ListItem item2 = new ListItem();
        item2.image = defaultImage;
        item2.name = "Cooper";
        item2.comment = "The design is so cool";
        list.add(item2);
        ListItem item3 = new ListItem();
        item3.image = defaultImage;
        item3.name = "Jones";
        item3.comment = "I like hacking. Do you like it?";
        list.add(item3);
        ListItem item4 = new ListItem();
        item4.image = defaultImage;
        item4.name = "Kevin";
        item4.comment = "Woof woof. I'm a dog.";
        list.add(item4);
        ListItem item5 = new ListItem();
        item5.image = defaultImage;
        item5.name = "Christine";
        item5.comment = "Meow meow. I'm a cat";
        list.add(item5);
        */
