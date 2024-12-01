package com.example.proj1_etr65856;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class WeightCalculator extends AppCompatActivity {
    double rate = 2.2;
    double userW, userH;
    double converted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_calculator);

        //define each textview for user input
        EditText userAddW = findViewById(R.id.userAddW);

        TextView result = findViewById(R.id.tvResult);
        RadioButton lbToKilo = findViewById(R.id.rbLbToKilo);
        RadioButton kiloToLb = findViewById(R.id.rbKiloToLb);

        //disable soft keyboard
        userAddW.setShowSoftInputOnFocus(false);


        //set calculate button
        Button calBt = findViewById(R.id.calculateBt);
        calBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userW=Double.parseDouble(userAddW.getText().toString());

                DecimalFormat tenth = new DecimalFormat("#.#");
                if(lbToKilo.isChecked()) {
                    if (userW<=500)
                    {
                        converted = userW/rate;
                        result.setText(tenth.format(converted)+" kilograms");
                    }
                    else
                    {
                        Toast.makeText(WeightCalculator.this,"Pounds must be less than 500", Toast.LENGTH_LONG).show();
                    }

                }
                if(kiloToLb.isChecked())
                {
                    if(userW<=225) {
                        converted = userW * rate;
                        result.setText(tenth.format(converted) + " pounds");
                    }
                    else
                    {
                        Toast.makeText(WeightCalculator.this,"Kilos must be less than 225", Toast.LENGTH_LONG).show( );
                    }
                }

                userAddW.setText("");

            }
        });

        //return home button
        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeightCalculator.this, MainActivity2.class));
            }
        });
    }
}