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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LaptopSpec extends AppCompatActivity {
    private final String TAG = "Inventory App";
    String rowID = "0";

    public static Intent makeIntent(Context context) {
        return new Intent(context, LaptopSpec.class);
    }

    private void homeButton(){
        //Wire up the Home button
        ImageButton btnHome = (ImageButton) findViewById(R.id.homeButton);
        //set click listener (set what happens when it clicks)
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "HomeButton clicked");
                //Toast.makeText(getApplicationContext(), "Going Back Home!", Toast.LENGTH_SHORT).show();

                //Launch the MainActivity
                Intent intent = MainActivity.makeIntent(LaptopSpec.this);
                startActivity(intent);
                //Kill Laptop Spec if going back home
                finish();
            }
        });
    }

    private void countButton(String rID){
        final String rowID = rID;
        //Wire up the count button
        ImageButton btnCount = (ImageButton) findViewById(R.id.countButton);
        //set click listener (set what happens when it clicks)
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "CountButton clicked from LaptopSpec");
                //Toast.makeText(getApplicationContext(), "Going to Parts Count!", Toast.LENGTH_SHORT).show();

                //Launch the PartsCount Activity
                Intent intent = PartsCount.makeIntent(LaptopSpec.this);
                Bundle bundle = new Bundle();
                bundle.putString("rowID", rowID);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void lowCountButton(){
        //Wire up the lowCount button
        ImageButton btnLowCount = (ImageButton) findViewById(R.id.lowCountButton);
        //set click listener (set what happens when it clicks)
        btnLowCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "lowCount Button clicked from LaptopSpec");
                Toast.makeText(getApplicationContext(), "Please proceed through Parts Count", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laptop_specs);
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
                FeedReaderContract.FeedEntry.COLUMN_ID,
                FeedReaderContract.FeedEntry.COLUMN_MODEL,
                FeedReaderContract.FeedEntry.COLUMN_PROCESSOR,
                FeedReaderContract.FeedEntry.COLUMN_GRAPHICS,
                FeedReaderContract.FeedEntry.COLUMN_MEMORY
        };

        //"WHERE" portion of the query
        String selection = FeedReaderContract.FeedEntry.COLUMN_ID + " = ?";
        Log.v(TAG, "The rowID is: " + rowID);
        String[] selectionArgs = { rowID };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry._ID + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
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
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_ID));
            itemIds.add(itemId);
//            Log.v(TAG, "Adding " + itemId + " to itemIds");
            itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_MODEL));
            itemIds.add(itemId);
//            Log.v(TAG, "Adding " + itemId + " to itemIds");
            itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_PROCESSOR));
            itemIds.add(itemId);
//            Log.v(TAG, "Adding " + itemId + " to itemIds");
            itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_GRAPHICS));
            itemIds.add(itemId);
//            Log.v(TAG, "Adding " + itemId + " to itemIds");
            itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_MEMORY));
            itemIds.add(itemId);
//            Log.v(TAG, "Adding " + itemId + " to itemIds");
        }
        cursor.close();
        //Check for reading all elements in the list
        for (int i=0;i < itemIds.size();i++)
        {
            Log.i("Value of element "+i,itemIds.get(i));
        }

        //Sets logo image based on rowID
        //Note: add the column "brand" into table
        ImageView img = (ImageView) findViewById(R.id.brandLogo);
        if (rowID.equalsIgnoreCase("1") || rowID.equalsIgnoreCase("2") || rowID.equalsIgnoreCase("3")) {
            img.setImageResource(R.mipmap.lenovo_logo2);
        }
        else {
            img.setImageResource(R.mipmap.apple);
        }

        TextView textModel = (TextView) findViewById(R.id.modelName);
        TextView textProcessor = (TextView) findViewById(R.id.processorName);
        TextView textGraphics = (TextView) findViewById(R.id.graphicsName);
        TextView textMemory = (TextView) findViewById(R.id.memoryName);
        textModel.setText(itemIds.get(1));
        textProcessor.setText(itemIds.get(2));
        textGraphics.setText(itemIds.get(3));
        textMemory.setText(itemIds.get(4));
        db.close();

        //      Button Functions
        homeButton();
        countButton(rowID);
        lowCountButton();
    }



}