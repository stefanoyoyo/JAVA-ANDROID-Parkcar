package com.example.ste.parkcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Utility_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_);

        TextView textView15distanzadaPercorrere = (TextView) findViewById(R.id.textView15distanzadaPercorrere);
        TextView litridicarburanteconsumati = (TextView) findViewById(R.id.textView16litridicarburanteconsumati);
        TextView textView17preventivocosti = (TextView) findViewById(R.id.textView17preventivocosti);


        try {
        textView15distanzadaPercorrere.setOnClickListener(new View.OnClickListener() {
            @Override
                /*
                 * IF distanzadaPercorrere BUTTON IS CLICKED, AN ACTIVITY CONTAINING THE TOOL
                 * TO USE FOR MISURING THE DISTANCE WILL BE RUN
                 */
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DistanzaDaPercorrere_Activity.class);
                startActivity(intent);
            }
        });
    }  catch (Exception exc) {
        System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
    }

        //----------------------------------------

        try {
            litridicarburanteconsumati.setOnClickListener(new View.OnClickListener() {
                @Override
                /*
                 * IF distanzadaPercorrere BUTTON IS CLICKED, AN ACTIVITY CONTAINING THE TOOL
                 * TO USE FOR MISURING THE DISTANCE WILL BE RUN
                 */
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Litri_carburante_spesi.class);
                    startActivity(intent);
                }
            });
        }  catch (Exception exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
        }
    //----------------------------------------

        try {
            textView17preventivocosti.setOnClickListener(new View.OnClickListener() {
                @Override
                /*
                 * IF distanzadaPercorrere BUTTON IS CLICKED, AN ACTIVITY CONTAINING THE TOOL
                 * TO USE FOR MISURING THE DISTANCE WILL BE RUN
                 */
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), prezzo_selfservice_benzina.class);
                    startActivity(intent);
                }
            });
        }  catch (Exception exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
        }

        //----------------------------------------
        /*
        * SE PREMO HOME TORNO ALLA MAIN ACTIVITY (SCHERMATA PRINCIPALE)
        */

        Button schermataPrincipale = (Button) findViewById(R.id.button5);
        schermataPrincipale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        //----------------------------------------
 /*
        * SE PREMO GUIDA TORNO ALLA ACTIVITY DEDICATA ALLA GUIDA
        */
        Button button4guida  = (Button) findViewById(R.id.button6);
        button4guida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        //----------------------------------------


    }
}
