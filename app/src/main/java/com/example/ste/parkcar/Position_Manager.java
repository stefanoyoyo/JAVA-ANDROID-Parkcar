package com.example.ste.parkcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * gestisci posizione activity
 */
public class Position_Manager extends AppCompatActivity {
    ActivityMemorize ActivityMemorize = MainActivity.ActivityMemorize;

    public Position_Manager () {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position__manager);

        try {
            ActivityMemorize.setClasses (this);
            System.err.println("CLASSI SETTATE ! " );

        } catch (Exception exc) {
            ActivityMemorize.setActualClass(this);
            System.err.println("ERRORE. SETTO QUESTA CLASSE COME CLASSE CORRENTE ! " );
        }

             /*
             * SE PREMO HOME, TORNO ALLA ACTIVITY DEL MENU PRINCIPALE
             */
        Button schermataPrincipale = (Button) findViewById(R.id.button3);
        TextView MemorizzaPosizioneAuto = (TextView) findViewById(R.id.textView15);
        TextView TornaAuto = (TextView) findViewById(R.id.textView16);

        //-----------------------------------------
        MemorizzaPosizioneAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemorizzaPosizioneAuto.class);
                finish();
                startActivity(intent);
            }
        });
        //----------------------------------------
        schermataPrincipale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        //----------------------------------------
        TornaAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Torna_all_auto.class);
                finish();
                startActivity(intent);
            }
        });
        //----------------------------------------
        Button elencoPosizioniMemorizzate = (Button) findViewById(R.id.button4);
        elencoPosizioniMemorizzate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Posizioni_memorizzate.class);
                finish();
                startActivity(intent);
            }
        });

    }
}
