package edu.wit.mobileapp.techspotinventory1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by trank on 10/13/2017.
 */

public class LowCount extends AppCompatActivity{
    private final String TAG = "Inventory App";
    String rowID = "0";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.low_count);
        //      Bundle receive
        Bundle bundle = getIntent().getExtras();
        String rowID = bundle.getString("rowID");
        //Button Function Calls
        homeButton();
        countButton(rowID);
        lowCountButton();


    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, LowCount.class);
    }

    private void homeButton() {
        //Wire up the Home button
        ImageButton btnHome = (ImageButton) findViewById(R.id.homeButton);
        //set click listener (set what happens when it clicks)
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "HomeButton clicked from LowCount");
                Toast.makeText(getApplicationContext(), "Going Back Home from LowCount!", Toast.LENGTH_SHORT).show();

                //Launch the MainActivity
                Intent intent = MainActivity.makeIntent(LowCount.this);
                startActivity(intent);
                //Kill Parts Count if going back home
                finish();
            }
        });
    }
    private void countButton(String rID){
        rowID = rID;
        //Wire up the count button
        ImageButton btnCount = (ImageButton) findViewById(R.id.countButton);
        //set click listener (set what happens when it clicks)
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "CountButton clicked from LowCount");

                //Launch the PartsCount Activity
                Intent intent = PartsCount.makeIntent(LowCount.this);
                Bundle bundle = new Bundle();
                bundle.putString("rowID", rowID);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
    private void lowCountButton() {
        //Wire up the lowCount button
        ImageButton btnLowCount = (ImageButton) findViewById(R.id.lowCountButton);
        //set click listener (set what happens when it clicks)
        btnLowCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "CountButton clicked from LowCount");
                Toast.makeText(getApplicationContext(), "You're already in Low Count!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
