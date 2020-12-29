package com.example.ste.parkcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CancellaTutteLePosizioni extends AppCompatActivity {
    ActivityMemorize ActivityMemorize = MainActivity.ActivityMemorize;
    PositionDatabase db;
    Button elimina;
    Button indietro;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancella_tutte_le_posizioni);
        db = new PositionDatabase(getApplicationContext());
        home = (Button) findViewById(R.id.button3);
        indietro = (Button) findViewById(R.id.button4);
        elimina = (Button) findViewById(R.id.button);

        //----------------LISTENERS-------------------------
        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         //     db.deleteAll();                 // Funziona ma cancella tutti i dati senza resettare il valore della colonna id, ripartendo poi cosi successivamente
                                                // a inserire i dati dal valore vecchio di id, senza partire da 0
                db.dropAndRemakeTable ();
                Toast.makeText(getApplicationContext(),"Dati cancellati",Toast.LENGTH_SHORT).show();

            }
        });
        //----------------------------------------
        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Posizioni_memorizzate.class);
                finish();
                startActivity(intent);
            }
        });
        //----------------------------------------
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
