package edu.wit.mobileapp.bodycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Calculator extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        calculatebtn();
    }
    private void calculatebtn() {
        Button btn = (Button) findViewById(R.id.mainButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//      Log.v("Lab3 App", "Calculate Button has been pressed.");

                //Switch!!!
                Intent intent = new Intent();
                intent.setClass(Calculator.this, Result.class);
                Log.v("Lab3 App", "After intent setclass");

                //Radio group, buttons
                radioGroup = (RadioGroup) findViewById(R.id.radiobtnGroup);
                int radiobtn_id = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radiobtn_id);
                String mainGender = radioButton.getText().toString();
//                Log checks
//                Log.v("Lab3 App", "RadioPass:" + mainGender);
//                Toast.makeText(Calculator.this,
//                        mainGender, Toast.LENGTH_SHORT).show();



                //Edittext to int for the input fields
                EditText editText = (EditText) findViewById(R.id.mainFeet);
                double mainFeet = Integer.parseInt( editText.getText().toString() );
                editText = (EditText) findViewById(R.id.mainInches);
                double mainInches = Integer.parseInt( editText.getText().toString() );

//              Bundle up feet/inches and send off
                Bundle bundle = new Bundle();
                bundle.putString("gender", mainGender);
                bundle.putDouble("feet", mainFeet);
                bundle.putDouble("inches", mainInches);
//              Log.v("Lab3 App", "Gender:" + mainGender + " Feet:" + mainFeet + " Inches: " + mainInches);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
