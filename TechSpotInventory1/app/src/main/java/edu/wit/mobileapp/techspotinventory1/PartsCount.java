package edu.wit.mobileapp.techspotinventory1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PartsCount extends AppCompatActivity {
    private final String TAG = "Inventory App";
    String rowID = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parts_count);
        homeButton();
        countButton();

        //      Bundle receive
        Bundle bundle = getIntent().getExtras();
        String modelSelection = bundle.getString("modelSelection");

        if (modelSelection != null && modelSelection.equals("ThinkPad W541")){
            rowID = "1";
        }else if (modelSelection != null && modelSelection.equals("ThinkPad P50")){
            rowID = "2";
        }else if (modelSelection != null && modelSelection.equals("ThinkPad Yoga P40")){
            rowID = "3";
        }else if (modelSelection != null && modelSelection.equals("MacBook 15\" 2017")){
            rowID = "4";
        }else if (modelSelection != null && modelSelection.equals("MacBook 13\" 2017")){
            rowID = "5";
        }else if (modelSelection != null && modelSelection.equals("MacBook 15\" Late 2015")){
            rowID = "6";
        }

        // Set the path and database name
        String path = "/data/data/" + getPackageName() + "/laptopInventory.db";
        // Open the database. If it doesn't exist, create it.
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);

        //"SELECT" portion of the query
        String[] projection = {
                FeedReaderContract.FeedCountEntry.COLUMN_ID,
                FeedReaderContract.FeedCountEntry.COLUMN_MODEL,
                FeedReaderContract.FeedCountEntry.COLUMN_PROCESSOR,
                FeedReaderContract.FeedCountEntry.COLUMN_GRAPHICS,
                FeedReaderContract.FeedCountEntry.COLUMN_MEMORY
        };

        //"WHERE" portion of the query
        String selection = FeedReaderContract.FeedCountEntry.COLUMN_ID + " = ?";
        Log.v(TAG, "The rowID is: " + rowID);
        String[] selectionArgs = { rowID };
        String tableName = "partsInventory";

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedCountEntry._ID + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedCountEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        //Reading the data into "itemIds" List Object
        List<String> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedCountEntry.COLUMN_ID));
            itemIds.add(itemId);
            itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedCountEntry.COLUMN_MODEL));
            itemIds.add(itemId);
            itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedCountEntry.COLUMN_PROCESSOR));
            itemIds.add(itemId);
            itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedCountEntry.COLUMN_GRAPHICS));
            itemIds.add(itemId);
            itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedCountEntry.COLUMN_MEMORY));
            itemIds.add(itemId);
        }

        /* Check for reading all elements in the list
        for (int i=0;i < itemIds.size();i++)
        {
            Log.i("Value of element "+i,itemIds.get(i));
        }
        */
        TextView textModel = (TextView) findViewById(R.id.modelName);
        TextView textProcessorCount = (TextView) findViewById(R.id.processorCount);
        TextView textGraphicsCount = (TextView) findViewById(R.id.graphicsCount);
        TextView textMemoryCount = (TextView) findViewById(R.id.memoryCount);
        textModel.setText(itemIds.get(1));
        textProcessorCount.setText(itemIds.get(2));
        textGraphicsCount.setText(itemIds.get(3));
        textMemoryCount.setText(itemIds.get(4));
        db.close();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, PartsCount.class);
    }

    private void homeButton() {
        //Wire up the Home button
        ImageButton btnHome = (ImageButton) findViewById(R.id.homeButton);
        //set click listener (set what happens when it clicks)
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "HomeButton clicked");
                Toast.makeText(getApplicationContext(), "Going Back Home!", Toast.LENGTH_SHORT).show();

                //Launch the MainActivity
                Intent intent = MainActivity.makeIntent(PartsCount.this);
                startActivity(intent);
                //Kill Parts Count if going back home
                finish();
            }
        });
    }
    private void countButton(){
        //Wire up the count button
        ImageButton btnCount = (ImageButton) findViewById(R.id.countButton);
        //set click listener (set what happens when it clicks)
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "CountButton clicked");
                Toast.makeText(getApplicationContext(), "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}