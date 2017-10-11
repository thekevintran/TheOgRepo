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
import android.widget.Toast;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "Inventory App";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brandSelectionButton();
        // Set the path and database name
        String path = "/data/data/" + getPackageName() + "/laptopInventory.db";
        // Open the database. If it doesn't exist, create it.
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);;

        //Check for table existence, if exists = skip table creation
        String TABLE_NAME = "laptopSpec";
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"
                + TABLE_NAME + "'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) { //table already exists
                //show toast
                Toast.makeText(this, "bam", Toast.LENGTH_SHORT).show();
                cursor.close();
                return;
            }
            cursor.close();
        }

        //create table and insert normally
        else{

        // Create table: laptopSpec
        String sql = "CREATE TABLE IF NOT EXISTS laptopSpec" +
         "(_id INTEGER PRIMARY KEY AUTOINCREMENT, model TEXT, processor TEXT, graphics TEXT, memory TEXT);";
        db.execSQL(sql);
        // Add Data

        ContentValues values = new ContentValues();
        values.put("model", "W541");
        values.put("processor", "i3 Processor");
        values.put("graphics", "GeForce 1070");
        values.put("memory", "4GB ");
        db.insert("laptopSpec", null, values);

        values.put("model", "P50");
        values.put("processor", "i7 Processor");
        values.put("graphics", "GeForce 1080Ti");
        values.put("memory", "32GB ");
        db.insert("laptopSpec", null, values);

        values.put("model", "P40");
        values.put("processor", "i5 Processor");
        values.put("graphics", "GeForce 970");
        values.put("memory", "16GB ");
        db.insert("laptopSpec", null, values);

        values.put("model", "MacBook 15\"2017");
        values.put("processor", "i7 Processor");
        values.put("graphics", "GeForce 1080Ti");
        values.put("memory", "32GB ");
        db.insert("laptopSpec", null, values);

        values.put("model", "MacBook 13\"2017");
        values.put("processor", "i7 Processor");
        values.put("graphics", "GeForce 1080Ti");
        values.put("memory", "32GB ");
        db.insert("laptopSpec", null, values);

        values.put("model", "MacBook 15\"Late 2015");
        values.put("processor", "i7 Processor");
        values.put("graphics", "GeForce 1080Ti");
        values.put("memory", "32GB ");
        db.insert("laptopSpec", null, values);
        }


        //Close the database
        db.close();

    }

    private void brandSelectionButton() {
        //Wire up the Apple button
        Button btnApple = (Button) findViewById(R.id.buttonApple);
        //Set click listener (set what happens when it clicks)
        btnApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "brandSelectionButton clicked");
                Toast.makeText(getApplicationContext(), "Launching Apple Models!", Toast.LENGTH_SHORT).show();

                //Launch the ModelApple Activity
                Intent intent = ModelApple.makeIntent(MainActivity.this);
                startActivity(intent);
                //Kill MainActivity... or not
                //finish();
            }
        });
        //Wire up the Lenovo Button
        Button btnLenovo = (Button) findViewById(R.id.buttonLenovo);
        //Set click listener (set what happens when it clicks)
        btnLenovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "brandSelectionButton clicked");
                Toast.makeText(getApplicationContext(), "Launching Lenovo Models!", Toast.LENGTH_SHORT).show();

                //Launch the ModelApple Activity
                Intent intent = ModelLenovo.makeIntent(MainActivity.this);
                startActivity(intent);
                //Kill MainActivity... or not
                //finish();
            }
        });
    }
    public static Intent makeIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }


}
