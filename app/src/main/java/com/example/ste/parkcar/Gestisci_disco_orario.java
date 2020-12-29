package com.example.ste.parkcar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Gestisci_disco_orario extends AppCompatActivity {

    TimePicker timePicker;
    TextView oranonimpostata;
    AlarmManager alarmManager;
    PendingIntent Pending_intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestisci_disco_orario);

        oranonimpostata = (TextView) findViewById(R.id.textView13);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        final Calendar calendar = Calendar.getInstance();
        final Intent my_intent = new Intent(getApplicationContext(),Alarm_Receiver.class);



        //----------------------------------------
            /*
             * SE PREMO impostaora, CONSERVO IN UNA VARIABILE L'INFORMAZIONE PRESA DALLA TIME PICKER
             */
        Button impostaora = (Button) findViewById(R.id.button8);
        impostaora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // setto le ore e i minuti nel time picker per poi metterne i valori
                    // in due variabili e far partire cosi l'allarme tramite un pending intent
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                    int hour = timePicker.getCurrentHour();
                    int minute = timePicker.getCurrentMinute();
                    String hourString = null; String minuteString= null;

                    if (hour<10) {
                         hourString = "0" + String.valueOf(hour);
                    }
                    if (minute<10) {
                        minuteString = "0" + String.valueOf(minute);
                    } else {
                        hourString = String.valueOf(hour);
                        minuteString = String.valueOf(minute);
                    }
                    Pending_intent = PendingIntent.getBroadcast(Gestisci_disco_orario.this,0,my_intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),Pending_intent);
                    oranonimpostata.setText("Allarme alle " + hourString + ":" + minuteString);
                    Toast.makeText(getApplicationContext(), "Ora impostata! ", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException exc) {
                    Toast.makeText(getApplicationContext(), "Mega eccezione lanciata ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //----------------------------------------
            /*
             * SE PREMO RESET, ELIMINO CIO' CHE C'E' SCRITTO NELLA VARIABILE ASSOCIATA A TIME PICKER
             */
        Button reset = (Button) findViewById(R.id.button9);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager.cancel(Pending_intent);
                oranonimpostata.setText("Ora da reimpostare! ");
                Toast.makeText(getApplicationContext(),"Ora da impostare! ",Toast.LENGTH_SHORT).show();
            }
        });
        //----------------------------------------
            /*
             * SE PREMO RESET, ELIMINO CIO' CHE C'E' SCRITTO NELLA VARIABILE ASSOCIATA A TIME PICKER
             */
        Button schermataPrincipale = (Button) findViewById(R.id.button3);
        schermataPrincipale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        Button indietro = (Button) findViewById(R.id.button4);
        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
}