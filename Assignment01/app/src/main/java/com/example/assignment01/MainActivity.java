/*
ASSIGNMENT 01
AYISHA RIAZ
GROUP A3 - AYISHA RIAZ, AMINAH JOHN
 */

/*

 */

package com.example.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText editTextBillValue;
    RadioGroup radioGroupTipPercent;
    TextView textViewCustomSeek, textViewCustomPercentage, textViewTotal, textViewTip;
    SeekBar seekBarTip;

    Profile profile;
    ArrayList<Tip> tips;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Tip Calculator");

        editTextBillValue = findViewById(R.id.editTextBillValue);
        radioGroupTipPercent = findViewById(R.id.radioGroupTipPercent);
        textViewCustomSeek = findViewById(R.id.textViewCustomSeek);
        textViewCustomPercentage = findViewById(R.id.textViewCustomPercentage);
        textViewTotal = findViewById(R.id.textViewTotal);
        textViewTip = findViewById(R.id.textViewTip);
        seekBarTip = findViewById(R.id.seekBarTip);


        findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredBillValue = editTextBillValue.getText().toString();

                try {
                    double bill = Double.valueOf(enteredBillValue);

                    String tip = "10%";
                    if (radioGroupTipPercent.getCheckedRadioButtonId() == R.id.radioButton15Percent) {
                        tip = "15%";
                    } else if (radioGroupTipPercent.getCheckedRadioButtonId() == R.id.radioButton18Percent) {
                        tip = "18%";
                    } else if (radioGroupTipPercent.getCheckedRadioButtonId() == R.id.radioButtonCustomPercent) {
                        tip = "custom";
                    }
                    profile = new Profile(tip, bill);
                    textViewTotal.setText("Total: $" + String.valueOf(bill));
                    editTextBillValue.setText("");
                   if (profile == null) {
                        Toast.makeText(MainActivity.this, "Enter a valid bill value!", Toast.LENGTH_LONG).show();
                    }

                } catch (NumberFormatException ex) {
                    Toast.makeText(MainActivity.this, "Enter a valid bill value!", Toast.LENGTH_LONG).show();
                }
            }
        });

        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                textViewCustomPercentage.setText(String.valueOf(progress) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


       findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double tipSelection = 10;
                int selectedRadioButtonId = radioGroupTipPercent.getCheckedRadioButtonId();

                if (radioGroupTipPercent.getCheckedRadioButtonId() == R.id.radioButton15Percent) {
                        tipSelection = 15;
                    } else if (radioGroupTipPercent.getCheckedRadioButtonId() == R.id.radioButton18Percent) {
                        tipSelection = 18;
                    } else if (radioGroupTipPercent.getCheckedRadioButtonId() == R.id.radioButtonCustomPercent) {
                    double percentage = seekBarTip.getProgress();
                    tipSelection = percentage;
                }
                String enteredBillValue = editTextBillValue.getText().toString();
                double billAmount = Double.parseDouble(enteredBillValue);
                double tipAmount = billAmount * (tipSelection / 100);
                double totalAmount = billAmount + tipAmount;

                textViewTip.setText("Tip: " + df.format(tipAmount));
                textViewTotal.setText("Total: " + df.format(totalAmount));


            }
        });

        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile = null;
                editTextBillValue.setText("");
                radioGroupTipPercent.check(R.id.radioButton10Percent);
                seekBarTip.setProgress(25);
                textViewTotal.setText("Tip: 0.00");
                textViewTip.setText("Total: 0.00");
            }
        });
    }


}