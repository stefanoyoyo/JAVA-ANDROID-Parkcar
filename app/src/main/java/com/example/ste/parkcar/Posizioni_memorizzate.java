package com.example.ste.parkcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Posizioni_memorizzate extends AppCompatActivity {
    ActivityMemorize ActivityMemorize ;
    TextView posizionimemorizzate ;
    TextView Cancellatutteleposizioni ;
    TextView Cancellaunaposizione ;

    public Posizioni_memorizzate () {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posizioni_memorizzate);
        ActivityMemorize = MainActivity.ActivityMemorize;
        posizionimemorizzate =(TextView)findViewById(R.id.t1);
        Cancellatutteleposizioni =(TextView)findViewById(R.id.t2);
        Cancellaunaposizione =(TextView)findViewById(R.id.t3);


        Button schermataPrincipale = (Button) findViewById(R.id.button3);
        Button indietro = (Button) findViewById(R.id.button4);

        try {
            ActivityMemorize.setClasses (this);
            System.err.println("CLASSI SETTATE ! " );

        } catch (Exception exc) {
            ActivityMemorize.setActualClass(this);
            System.err.println("ERRORE. SETTO QUESTA CLASSE COME CLASSE CORRENTE ! " );
        }


        //----------------LISTENERS-------------------------
        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityMemorize.setBothClasses(Position_Manager.class,this);
                Intent intent = new Intent(getApplicationContext(), (Class<?>) ActivityMemorize.getpreviousClass());
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
        //------------TEXTVIEWS----------------------------
        posizionimemorizzate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PositionDatabase db = new PositionDatabase(getApplicationContext());
                boolean b = db.doesDatabaseExist(getApplicationContext(), "PARKCAR_DATABASE.db" );
                System.err.println("Vediamo se il database esiste " + b);

                db.viewAll(Posizioni_memorizzate.this); // Per creare una finestra di dialogo
                                                        // serve una Activity context, la
                                                        // getApplicationContext non va bene.
            }
        });
        //----------------------------------------
        Cancellatutteleposizioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PositionDatabase db = new PositionDatabase(getApplicationContext());
                boolean b = db.doesDatabaseExist(getApplicationContext(), "PARKCAR_DATABASE.db" );
                System.err.println("Vediamo se il database esiste " + b);
                Intent intent = new Intent(getApplicationContext(), CancellaTutteLePosizioni.class);
                finish();
                startActivity(intent);
            }
        });
        //----------------------------------------
        Cancellaunaposizione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PositionDatabase db = new PositionDatabase(getApplicationContext());
                boolean b = db.doesDatabaseExist(getApplicationContext(), "PARKCAR_DATABASE.db" );
                System.err.println("Vediamo se il database esiste " + b);
                Intent intent = new Intent(getApplicationContext(), CancellaUnaPosizione.class);
                finish();
                startActivity(intent);
            }
        });
        //----------------------------------------
    }
}
