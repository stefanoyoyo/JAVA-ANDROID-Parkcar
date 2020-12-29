package com.example.ste.parkcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuidaIntroduzione extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guida_introduzione);
        Button home = (Button) findViewById(R.id.button4intro);
        Button indietro = (Button) findViewById(R.id.button3intro);
        //--------------------------------------------------
            /*
            * VADO ALLA HOME SE QUALCUNO FA UN TAP SU 'HOME' E CHIUDO QUESTA
            */
        try {
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        } catch (Exception exc) {
            System.err.println("Exception Occured. This is a " + exc.getMessage());
        }

        //--------------------------------------------------
            try {
                /*
                 * VADO ALLA ACTIVITY PRECEDENTE SE QUALCUNO FA UN TAP SU 'HOME' E CHIUDO QUESTA
                 */
        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Guide.class);
                startActivity(intent);
                finish();
            }
        });
            } catch (Exception exc) {
                System.err.println("Exception Occured. This is a " + exc.getMessage());
            }
    }
        //--------------------------------------------------

}
