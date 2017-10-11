package edu.wit.mobileapp.bodycalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

//      Bundle receive
        Bundle bundle = getIntent().getExtras();
        String gender = bundle.getString("gender");
        double feet = bundle.getDouble("feet");
        double inches = bundle.getDouble("inches");

//      Log.v("Lab3", "Received: Gender: " + gender + " " + feet + "ft " + inches + "in");
        double idealBodyweight = 0;

        if (gender != null && gender.equals("Male")){
            idealBodyweight = (50.0 + (2.3 * (((feet * 12) + inches) - 60.0)));
//          Log.v("Lab3", "Male Ideal body weight:" + idealBodyweight + "kg");
        }
        else {
            idealBodyweight = (45.5 + (2.3 * (((feet * 12) + inches) - 60.0)));
//            Log.v("Lab3", "Female Ideal body weight:" + idealBodyweight + "kg");
        }

        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView2.setText("You are " + gender);
        textView3.setText("Your height is " + (int) feet + "\'" + (int) inches + "\"");
        textView4.setText("The Standard Weight is: " + idealBodyweight + "kg");




    }
//    public static Intent makeIntent(Context context) {
//        return new Intent(context, Result.class);
//    }
}
