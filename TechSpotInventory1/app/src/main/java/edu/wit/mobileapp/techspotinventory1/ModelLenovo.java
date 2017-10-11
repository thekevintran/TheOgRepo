package edu.wit.mobileapp.techspotinventory1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by trank on 10/4/2017.
 */

public class ModelLenovo extends AppCompatActivity{
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private final String TAG = "Inventory App";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_lenovo);
        goButton();


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

                //Radio group, buttons
                radioGroup = (RadioGroup) findViewById(R.id.radioGroupLenovo);
                int radiobtn_id = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radiobtn_id);
                String modelSelectionBtn = radioButton.getText().toString();

                //Launch the LaptopSpec Activity
                //Bundle up model selection into intent
                Intent intent = LaptopSpec.makeIntent(ModelLenovo.this);
                Bundle bundle = new Bundle();
                bundle.putString("modelSelection", modelSelectionBtn);
                intent.putExtras(bundle);
                startActivity(intent);

                //Kill Model Lenovo... or not
                //finish();
            }
        });
    }
}