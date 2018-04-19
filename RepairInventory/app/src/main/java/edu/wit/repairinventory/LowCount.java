package edu.wit.repairinventory;

/**
 * Created by Kevin Tran on 2/24/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LowCount extends AppCompatActivity {
    private final String TAG = "Tech Spot Inventory App";
    String rowID = "";
    String model ="";
    int countP = 0;
    int countG = 0;
    int countM = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.low_count);

        //ArrayList (data)
        ArrayList<String> listData = new ArrayList<String>();

        //ListView
        ListView lowCountList = (ListView) findViewById(R.id.lowCountList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listData);

        //Assign adapter to list
        lowCountList.setAdapter(adapter);

        //      Bundle receive
        Bundle bundle = getIntent().getExtras();
        rowID = bundle.getString("rowID");
        model = bundle.getString("model");
        countP = bundle.getInt("countP");
        countG = bundle.getInt("countG");
        countM = bundle.getInt("countM");

        //Button Function Calls
        homeButton();
        countButton(rowID);
        lowCountButton();

        // If parts are less than 4. display on listView
        if(countP < 4){
            listData.add("Processor: " + countP);
        }
        if(countG < 4){
            listData.add("Graphics: " + countG);
        }
        if(countM < 4){
            listData.add("Processor: " + countM);
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

        textModel.setText(model);





    }//End of onCreate

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