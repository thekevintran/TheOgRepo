package edu.wit.mobileapp.techspotinventory1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ModelApple extends AppCompatActivity{
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private final String TAG = "Tech Spot Inventory App";

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
                Toast.makeText(getApplicationContext(), "Launching Apple Model Specifications!", Toast.LENGTH_SHORT).show();

                //Radio group, buttons
                radioGroup = (RadioGroup) findViewById(R.id.radioGroupApple);
                int radiobtn_id = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radiobtn_id);
                String modelSelectionBtn = radioButton.getText().toString();

                //Launch the LaptopSpec Activity
                //Bundle up model selection into intent
                Intent intent = LaptopSpec.makeIntent(ModelApple.this);
                Bundle bundle = new Bundle();
                bundle.putString("modelSelection", modelSelectionBtn);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
