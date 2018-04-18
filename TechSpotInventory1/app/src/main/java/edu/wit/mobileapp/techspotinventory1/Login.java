package edu.wit.mobileapp.techspotinventory1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Kevin Tran on 10/19/2017.
 */

public class Login extends AppCompatActivity{
    private final String TAG = "Tech Spot Inventory App";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        startButton();

    }

    private void startButton() {
        Button btnApple = (Button) findViewById(R.id.buttonStart);
        //Set click listener (set what happens when it clicks)
        btnApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "StartButton Clicked!!!!");

                //Launch the ModelApple Activity
                Intent intent = MainActivity.makeIntent(Login.this);
                startActivity(intent);
                //Kill MainActivity... or not
                finish();
            }
        });
    }
}

