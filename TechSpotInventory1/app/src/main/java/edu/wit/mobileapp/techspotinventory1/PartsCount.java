package edu.wit.mobileapp.techspotinventory1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * PartsCount class
 * Displays the PartsInventory.db's row values depending on Laptop Model
 * Row values may be edited in this Activity
 */

public class PartsCount extends AppCompatActivity {
    private final String TAG = "Inventory App";
    String rowID = "0";
    int mPCount = 0;
    int mGCount = 0;
    int mMCount = 0;


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
                Log.i(TAG, "HomeButton clickedfrom PartsCount");
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
                Log.i(TAG, "CountButton clicked from Parts Count");
                Toast.makeText(getApplicationContext(), "You're already in Parts Count!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    private void lowCountButton(String rID){
        rowID = rID;
        //Wire up the lowCount button
        ImageButton btnLowCount = (ImageButton) findViewById(R.id.lowCountButton);
        //set click listener (set what happens when it clicks)
        btnLowCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "lowCount Button from PartsCount clicked");

                //Launch the PartsCount Activity
                Intent intent = LowCount.makeIntent(PartsCount.this);
                Bundle bundle = new Bundle();
                bundle.putString("rowID", rowID);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void increaseIntegerP(View view) {
        mPCount = mPCount + 1;
        displayP(mPCount);
    }public void increaseIntegerG(View view) {
        mGCount = mGCount + 1;
        displayG(mGCount);
    }public void increaseIntegerM(View view) {
        mMCount = mMCount + 1;
        displayM(mMCount);
    }

    public void decreaseIntegerP(View view) {
        mPCount = mPCount - 1;
        displayP(mPCount);
    }public void decreaseIntegerG(View view) {
        mGCount = mGCount - 1;
        displayG(mGCount);
    }public void decreaseIntegerM(View view) {
        mMCount = mMCount - 1;
        displayM(mMCount);
    }

    private void displayP(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.modP);
        displayInteger.setText("" + number);
    }private void displayG(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.modG);
        displayInteger.setText("" + number);
    }private void displayM(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.modM);
        displayInteger.setText("" + number);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parts_count);
        //      Bundle receive
        Bundle bundle = getIntent().getExtras();
        rowID = bundle.getString("rowID");

        //      Button Functions
        homeButton();
        countButton();
        lowCountButton(rowID);

        // Set the path and database name
        String path = "/data/data/" + getPackageName() + "/laptopInventory.db";
        // Open the database. If it doesn't exist, create it.
        final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);

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
        // Check for reading all elements in the list
        for (int i=0;i < itemIds.size();i++)
        {
            Log.i("Value of element "+i,itemIds.get(i));
        }


        TextView textModel = (TextView) findViewById(R.id.modelName);
        TextView textProcessorCount = (TextView) findViewById(R.id.processorCount);
        TextView textGraphicsCount = (TextView) findViewById(R.id.graphicsCount);
        TextView textMemoryCount = (TextView) findViewById(R.id.memoryCount);
        textModel.setText(itemIds.get(1));
        textProcessorCount.setText(itemIds.get(2));
        textGraphicsCount.setText(itemIds.get(3));
        textMemoryCount.setText(itemIds.get(4));

        //Wire up the commitCount button
        Button btnCommit = (Button) findViewById(R.id.commitCount);
        //set click listener (set what happens when it clicks)
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "CommitCount clicked from PartsCount");
                Toast.makeText(getApplicationContext(), "Modify Counts Commited!", Toast.LENGTH_SHORT).show();

                //Stating the processor variables to add/subtract
                //then Get the String and convert to Int
                TextView modPText = (TextView)findViewById(R.id.modP);
                TextView countPText = (TextView)findViewById(R.id.processorCount);
                int modP = Integer.valueOf(modPText.getText().toString());
                int countP = Integer.valueOf(countPText.getText().toString());

                //Stating the Graphics variables to add/subtract
                //then Get the String and convert to Int
                TextView modGText = (TextView)findViewById(R.id.modG);
                TextView countGText = (TextView)findViewById(R.id.graphicsCount);
                int modG = Integer.valueOf(modGText.getText().toString());
                int countG = Integer.valueOf(countGText.getText().toString());

                //Stating the Memory variables to add/subtract
                //then Get the String and convert to Int
                TextView modMText = (TextView)findViewById(R.id.modM);
                TextView countMText = (TextView)findViewById(R.id.memoryCount);
                int modM = Integer.valueOf(modMText.getText().toString());
                int countM = Integer.valueOf(countMText.getText().toString());

                //Simple addition
                //Then update values to partsInventory.db
                countP = countP + modP;
                countG = countG + modG;
                countM = countM + modM;
                ContentValues newValues = new ContentValues();
                newValues.put("processor", countP);
                newValues.put("graphics",countG);
                newValues.put("memory",countM);
                String[] args = {rowID};
                db.update("partsInventory", newValues, "_id=?", args);

                // After values are finalized/updated to the database
                // "Refresh" the activity screen to display Values
                finish();
                startActivity(getIntent());
            }
        });



    }//closes onCreate
}
