package com.example.limsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tuloste;
    TextView balanssi;
    SeekBar sb;
    TextView rahaplus;
    int addM;
    String koko;
    int kokoI = 0;
    Context context = null;
    String viimeisinosto = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        balanssi = (TextView) findViewById(R.id.balanssi);
        tuloste = (TextView) findViewById(R.id.teksti);
        rahaplus = (TextView) findViewById(R.id.rahanlisays);
        sb = (SeekBar)findViewById(R.id.sb_seekBar);
        Spinner koko = findViewById(R.id.spinneri);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinneri, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        koko.setAdapter(adapter);
        koko.setOnItemSelectedListener(this);
        context = MainActivity.this;

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            int progresschange = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresschange = progress;
                rahaplus.setText(String.valueOf("+"+progresschange+"€"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                addM = progresschange;
            }
        });
    }


    public void addMoney(View v){
        BottleDispenser automaatti = BottleDispenser.getInstance();
        if(addM < 1){
            tuloste.setText("Add atleast 1€.");

        }else{
            balanssi.setText(String.valueOf(automaatti.addMoney(addM))+"€");
            sb.setProgress(0);
            addM = 0;
            tuloste.setText("Klink! Added more money!");
        }
    }

    public void returnMoney(View v){
        BottleDispenser automaatti = BottleDispenser.getInstance();
        if(automaatti.getMoney()>0){
            tuloste.setText("Klink klink. Money came out! You got "+automaatti.getMoney()+"€ back");
            balanssi.setText(String.valueOf(automaatti.returnMoney())+"€");
        } else{
            tuloste.setText("Klink klink!! All money gone!");
        }
    }



    public void buyBottle(View v){
        BottleDispenser automaatti = BottleDispenser.getInstance();
        if(kokoI == 0) {
            tuloste.setText("Select a Bottle first.");
        } else{
            tuloste.setText(automaatti.buyBottle(kokoI));
            balanssi.setText(String.valueOf(automaatti.getMoney()+"€"));
            viimeisinosto = ("Receipt:\n       "+automaatti.getNimi(kokoI)+" "+automaatti.getSize(kokoI)+"" +
                    "l\n       ____________________\n       Total discount: "+automaatti.getPrice(kokoI)+"€\n       alv: 24% ");
        }
    }




    public void receipt(View v){
        String kuitti = "kuitti.txt";
        if(viimeisinosto == null){
            tuloste.setText("Buy something to get receipt.");
        } else{
            try {
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(kuitti, Context.MODE_PRIVATE));
                String s = viimeisinosto;
                osw.write(s);
                osw.close();
            } catch (IOException e){
                Log.e("IOException", "Virhe syötteessä.");
            } finally {
                tuloste.setText("Receipt printed.");

            }
        }
    }


    public void setPepsi(View v){
        BottleDispenser automaatti = BottleDispenser.getInstance();
        if(koko.equals("0.5l")){kokoI = 1;}
        else if(koko.equals("1.5l")){kokoI = 2;}
        double price = automaatti.getPrice(kokoI);
        tuloste.setText("Pepsi Max, "+koko+" selected. Price: "+ price);

    }

    public void setCoke(View v){
        BottleDispenser automaatti = BottleDispenser.getInstance();
        if(koko.equals("0.5l")){kokoI = 3;}
        else if(koko.equals("1.5l")){kokoI = 4;}
        double price = automaatti.getPrice(kokoI);
        tuloste.setText("Coca-Cola Zero, "+koko+" selected. Price: "+ price);
    }
    public void setFanta(View v){
        BottleDispenser automaatti = BottleDispenser.getInstance();
        if(koko.equals("0.5l")){kokoI = 5;}
        else if(koko.equals("1.5l")){kokoI = 6;}
        double price = automaatti.getPrice(kokoI);
        tuloste.setText("Fanta Zero, "+koko+" selected. Price: "+ price);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        koko = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}