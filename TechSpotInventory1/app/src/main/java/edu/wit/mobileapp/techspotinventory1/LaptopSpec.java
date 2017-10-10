package edu.wit.mobileapp.techspotinventory1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.java.FeedReaderContact;

public class LaptopSpec extends AppCompatActivity {
    private final String TAG = "Inventory App";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laptop_specs);
        homeButton();

        /*
        // Set the path and database name
        String path = "/data/data/" + getPackageName() + "/laptopInventory.db";
        // Open the database. If it doesn't exist, create it.
        SQLiteDatabase db;
        db = SQLiteDatabase.openDatabase(path, null);

        String model = db.toString(); //this is the method to query
        String processor = db.toString();
        String graphics = db.toString();
        String memory = db.toString();

        Log.v(TAG, "Model:" + model + "Processor: " + processor + " Graphics: " + graphics + "Memory: " + memory);


        db.close();
        */

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                FeedEntry._ID,
                FeedEntry.COLUMN_NAME_TITLE,
                FeedEntry.COLUMN_NAME_SUBTITLE
        };

// Filter results WHERE "title" = 'My Title'
        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
    }


    public static Intent makeIntent(Context context) {
        return new Intent(context, LaptopSpec.class);
    }

    private void homeButton(){
        //Wire up the Home button
        ImageButton btnHome = (ImageButton) findViewById(R.id.homeButton1);
        //set click listener (set what happens when it clicks)
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "HomeButton clicked");
                Toast.makeText(getApplicationContext(), "Going Back Home!", Toast.LENGTH_SHORT).show();

                //Launch the ModelApple Activity
                Intent intent = MainActivity.makeIntent(LaptopSpec.this);
                startActivity(intent);
                //Kill Laptop Spec if going back home
                finish();
            }
        });
    }
}