package com.example.ste.parkcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Guide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        Button avanti = (Button) findViewById(R.id.button4guida);
        Button home = (Button) findViewById(R.id.button3guida);

        TextView GuidaIntroduzione = (TextView) findViewById(R.id.GuidaIntroduzione);


        try{
            /*
            * VADO ALLA PRIMA PAGINA DELLA GUIDA SE QUALCUNO PREME AVANTI
            */
            avanti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),GuidaIntroduzione.class);
                    startActivity(intent);
                    finish();
                }
            });
        } catch (Exception exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
        }
        //--------------------------------------------------

        try {
            /*
            * VADO ALLA HOME SE QUALCUNO FA UN TAP SU 'HOME' E CHIUDO QUESTA
            */
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } catch (Exception exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
        }
        //--------------------------------------------------
        try {
            /*
            * VADO ALLA PRIMA PAGINA DELLA GUIDA SE QUALCUNO PREME SU GuidaIntroduzione
            */
        GuidaIntroduzione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GuidaIntroduzione.class);
                startActivity(intent);
                finish();
            }
        });
        } catch (Exception exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
        }
        //--------------------------------------------------



    }
}
