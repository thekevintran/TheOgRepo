package edu.wit.mobileapp.techspotinventory1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ModelApple extends AppCompatActivity{
    private final String TAG = "Inventory App";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_apple);
        goButton();
    }
    public static Intent makeIntent(Context context) {
        return new Intent(context, ModelApple.class);
    }
    private void goButton() {
        //Wire up the Apple Go Button
        Button btnApple = (Button) findViewById(R.id.buttonModelApple);
        //set click listener (set what happens when it clicks)
        btnApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "ModelApple GoButton clicked");
                Toast.makeText(getApplicationContext(), "Launching Lenovo Model Specifications!", Toast.LENGTH_SHORT).show();

                //Launch the LaptopSpec Activity
                Intent intent = LaptopSpec.makeIntent(ModelApple.this);
                startActivity(intent);
                //Kill Model Apple... or not
                //finish();
            }
        });
    }
}
