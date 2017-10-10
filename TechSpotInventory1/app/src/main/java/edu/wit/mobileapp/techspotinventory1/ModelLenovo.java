package edu.wit.mobileapp.techspotinventory1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by trank on 10/4/2017.
 */

public class ModelLenovo extends AppCompatActivity {
    private final String TAG = "Inventory App";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_lenovo);
        goButton();

        // Set the path and database name
        String path = "/data/data/" + getPackageName() + "/laptopInventory.db";
        // Open the database. If it doesn't exist, create it.
        SQLiteDatabase db;
        db = db.getReadableDatabase();

        String[] projection = {
                db.,
                FeedEntry.COLUMN_NAME_TITLE,
                FeedEntry.COLUMN_NAME_SUBTITLE
        };




    }
    public static Intent makeIntent(Context context) {
        return new Intent(context, ModelLenovo.class);
    }
    private void goButton() {
        //Wire up the Lenovo Go Button
        Button btnLenovo = (Button) findViewById(R.id.buttonModelLenovo);
        //set click listener (set what happens when it clicks)
        btnLenovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "ModelLenovo GoButton clicked");
                Toast.makeText(getApplicationContext(), "Launching Lenovo Model Specifications!", Toast.LENGTH_SHORT).show();

                //Launch the LaptopSpec Activity
                Intent intent = LaptopSpec.makeIntent(ModelLenovo.this);
                startActivity(intent);
                //Kill Model Lenovo... or not
                //finish();
            }
        });
    }
}