package com.example.ste.parkcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button ok = (Button) findViewById(R.id.button);
        Button guide = (Button) findViewById(R.id.button2);
        File file ;

        try {
            ok.setOnClickListener(new View.OnClickListener() {
                /*
                * IF OK BUTTON IS CLICKED, OK WILL BE CLOSED.
                */
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Welcome.class);

                    finish();
                }
            });
        } catch (Exception exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
        }
        try {
        guide.setOnClickListener(new View.OnClickListener() {
            /*
             * IF GUIDA BUTTON IS CLICKED, A GUIDE WILL BE SHOWN TO THE USER
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Guide.class);
                startActivity(intent);
            }
        });
        } catch (Exception exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
        }
    }

}
