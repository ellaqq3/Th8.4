package com.example.th84;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView näkymä;
    SeekBar seekBar;
    Spinner spinner;
    int money = 0;
    BottleDispenser bot = BottleDispenser.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        näkymä = (TextView) findViewById(R.id.textView2);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        spinner = (Spinner) findViewById(R.id.spinner);
        näkymä.setText("Bottle Dispenser");

        ArrayList<String> bottle_list = bot.getBottle_list();

        ////////  Spinneri -> ////////////////////////////////////////////////////////////////////////////////////////////////

             //Luodaan ArrayAdapter//
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bottle_list);
             //Määritellään spinnerin asettelutyyli//
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             //lisätään adapteri spinneriin//
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (parent.getItemAtPosition(position).equals("Valitse pullo")){
                    System.out.println("toimii");
                }
                else {

                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

                String[] items = item.split(" ");

                String name = items[0].trim();
                String sizeS = items[1].trim();
                String priceS = items[3].trim();

                float size = Float.parseFloat(sizeS);
                float price = Float.parseFloat(priceS);

                int num = bot.buyBottle(name, size, price);
                    if (num == 11) {
                       näkymä.setText("Bottle came out of dispenser");
                     }
                    else if (num == 0) {
                        näkymä.setText("Add more money first");
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        ////////  Seekbar -> ////////////////////////////////////////////////////////////////////////////////////////////////

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                Toast.makeText(getApplicationContext(), "Money : " + progress + " €", Toast.LENGTH_SHORT).show();
                money = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void AddMoney(View v) {
        String moneyStr = new Float(money).toString();
        bot.addMoney(money);
        näkymä.setText("Money added, " +moneyStr + "€");
        seekBar.setProgress(0);
    }

    public void ReturnMoney(View v){
        float money;
        money = bot.returnMoney();
        String moneyString = new Float(money).toString();
        näkymä.setText("Money came out! You got " + moneyString+ "€ back\n");
    }
}


