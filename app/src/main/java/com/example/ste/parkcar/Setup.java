package com.example.ste.parkcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Setup extends AppCompatActivity {

    ActivityMemorize ActivityMemorize = MainActivity.ActivityMemorize;
    static int numero = 0; /* MOM: google maps only available by default. QUANDO CI SARANNO ANCHE LE MAPPE INTERNE, PORRE numero = -1;  !!   */
    Button home;
    Button indietro;
    ToggleButton TogglegoogleMaps;
    ToggleButton ToggleInMaps;
    TextView freccietta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        home =     (Button) findViewById(R.id.button3);
        indietro = (Button) findViewById(R.id.button4);
        TogglegoogleMaps = (ToggleButton) findViewById(R.id.ToggleGoogleMaps);
        ToggleInMaps = (ToggleButton) findViewById(R.id.ToggleInMaps);
        freccietta = (TextView) findViewById(R.id.freccietta);
        freccietta.setText("--> INAPP MAPS");
        freccietta.setText("--> GOOGLE MAPS");      // MOMENTANEO


        try {
            ActivityMemorize.setClasses (this);
            System.err.println("CLASSI SETTATE ! " );

        } catch (Exception exc) {
            ActivityMemorize.setActualClass(this);
            System.err.println("ERRORE. SETTO QUESTA CLASSE COME CLASSE CORRENTE ! " );
        }

        //----------- LISTENERS -----------------------------
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        //----------------------------------------
        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                System.err.println("ActivityMemorize.getpreviousClass() -->  " + ActivityMemorize.getpreviousClass());
                Intent intent = new Intent(getApplicationContext(), (Class<?>)  ActivityMemorize.getpreviousClass().getClass());
                /* PER POTER TORNARE ALLA CLASSE PRECEDENTE, HO DOVUTO PER FORZA CREARE UN OGGETTO STATICO
                 * DI TIPO ActivityMemorize NEL QUALE MEMORIZZARE I DATI. */
                finish();
                startActivity(intent);
                } catch (Exception exc) {
                    System.err.println("ERRORE --> " + exc.getMessage());
                }
            }
        });
        //----------------------------------------
        ToggleInMaps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    numero = 1;     /*   numero = 1;  per mostrare le mappe interne numero = 1 */
                    TogglegoogleMaps.setChecked(false);
                    freccietta.setText("--> INAPP MAPS");
                    freccietta.setText("--> GOOGLE MAPS");      // MOMENTANEO
                //  Toast.makeText(getApplication(),"NUMERO " + numero, Toast.LENGTH_SHORT).show();
                } else  {
                    numero = 1;
                //  Se spengo il tasto INAPP, si setteranno per default le mappe interne
                    freccietta.setText("--> INAPP MAPS");
                //  Toast.makeText(getApplication(),"NUMERO " + numero, Toast.LENGTH_SHORT).show();
                }
                         //--------------MOMENTANEO--------------------------
                /*
                * MOMENTANEO: SE numero == 1, riattivo automaticamente le google maps e mostro
                *             un toast di spiegazione all'utente.
                *             QUANDO AVRO' SISTEMTO IL PROBLEMA, SARA' SUFFICIENTE CANCELLARE
                *             LA PARTE TEMPORANEA DI CODICE DELIMITATA DAI DUE //--..MOMENTANEO..--
                * */
                if (numero == 1) {
                    ToggleInMaps.setChecked(false);
                    numero = 0;
              //    Toast.makeText(getApplication(),"NUMERO " + numero +"\n Mappe interne ancora non disponibili", Toast.LENGTH_SHORT).show();
                }
                         //--------------MOMENTANEO--------------------------
            }
        });
        //----------------------------------------
        TogglegoogleMaps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ToggleInMaps.setChecked(false);
                    numero = 0;
                    freccietta.setText("--> GOOGLE MAPS");
              //    Toast.makeText(getApplication(),"NUMERO " + numero, Toast.LENGTH_SHORT).show();
                } else {
                    numero = 1;
                    ToggleInMaps.setChecked(true);
                    freccietta.setText("--> INAPP MAPS");
                    freccietta.setText("--> GOOGLE MAPS");      // MOMENTANEO
                    Toast.makeText(getApplication(),"NUMERO " + numero, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
         //---------------METODI-------------------------

    /**
     * THis methos allows to user to decide if to use the internal maps or the google maps application
     *
     * @return a nummber rapresenting the chioce the user did. 0 = google maps ; 1 = internal maps;
     *
     * MOMENTANEO: IL METODO TORNERA' SEMRE 1, IN MODO DA USARE SEMPRE GOOGLE MAPS FIN QUANDO NON
     *             SISTEMO LE MAPPE INTERNE
     *
     */
         public int whatTypeOfMapsAreUsed () {
             return numero;
         }
}
