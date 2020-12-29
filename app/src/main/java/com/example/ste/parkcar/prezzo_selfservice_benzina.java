package com.example.ste.parkcar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class prezzo_selfservice_benzina extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prezzo_selfservice_benzina);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); // NASCONDO LA TASTIERA ALL'APERTURA DELL'ACTIVITY

        final EditText editTextnumgiorni = (EditText) findViewById(R.id.editTextnumgiorni2);
        final EditText editTextnumkm = (EditText) findViewById(R.id.editTextnumkm2);

        final TextView textView9distanzadapercorrere = (TextView) findViewById(R.id.textView9distanzadapercorrere);

        final Button button7 = (Button) findViewById(R.id.button7);
        final Button schermataPrincipale = (Button) findViewById(R.id.button3);
        final Button indietro = (Button) findViewById(R.id.button4);

        final Utility utility = new Utility();

        final boolean a = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""));

        final boolean b = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                && !(editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""));

        final boolean c =! (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""));

        final boolean d = ((editTextnumgiorni.getText().toString() != null) || !(editTextnumgiorni.getText().toString().equals("")))
                && ((editTextnumkm.getText().toString() != null) || !(editTextnumkm.getText().toString().equals("")));

        final boolean z = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""));

            /*
            * VADO ALLA PRIMA PAGINA DELLA GUIDA SE QUALCUNO PREME SU GuidaIntroduzione
            */
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (d) {
                        //Double result = utility.prezzo_selfservice_benzina(num1,num2);
                        Double result = Double.parseDouble(editTextnumgiorni.getText().toString()) * Double.parseDouble(editTextnumkm.getText().toString());
                        textView9distanzadapercorrere.setText(result.toString() + " €");
                    } else if (z) {
                        textView9distanzadapercorrere.setText("0 €");
                    }
                } catch (NumberFormatException exc) {

                }
            }
        });

     //----------------------------------------
     /*
      * SE PREMO HOME TORNO ALLA MAIN ACTIVITY (SCHERMATA PRINCIPALE)
      */
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
      * SE PREMO INDIETRO TORNO ALLA ACTIVITY DEDICATA ALLE UTILITY
      */

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Utility_Activity.class);
                finish();
                startActivity(intent);
            }
        });


    }
}
