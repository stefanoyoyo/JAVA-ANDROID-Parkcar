package com.example.ste.parkcar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static ActivityMemorize ActivityMemorize = new ActivityMemorize ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* INIZIA IL PROGRAMMA */
        String TestFile = "TestFile" ;

        TextView textView13toUtility = (TextView)findViewById(R.id.textView13ActivityMainaccediutility);
        TextView textView12ActivityMaindiscoorario = (TextView) findViewById(R.id.textView12ActivityMaindiscoorario);
        TextView textView9mainActivityPosizione = (TextView) findViewById(R.id.textView9mainActivityPosizione);
        TextView textView25setup = (TextView) findViewById(R.id.textView25);

        Button button4mainActivity = (Button) findViewById(R.id.button4mainActivity); // esci
        Button button3mainActivity = (Button) findViewById(R.id.button3mainActivity); // guida
        Intent Welcome = new Intent(getApplicationContext(),Welcome.class);
        String pathTestFile = getCacheDir()+"/"+TestFile;
        boolean alreadyExists =  new File(pathTestFile).exists();

       // Toast toast = Toast.makeText(this, " IL FILE " + pathTestFile + " NON ESISTE ! ", Toast.LENGTH_SHORT); /* TEMP */
       // Toast toast1 = Toast.makeText(getApplicationContext(), " IL FILE " + pathTestFile + " GIA' ESISTE ! ", Toast.LENGTH_SHORT); /* TEMP */

        try {
            ActivityMemorize.setActualClass(this);
            System.err.println(this + " classe SETTATA COME CLASSE CORRENTE ! " );
        } catch (Exception exc) {
            System.err.println("ERRORE: --> CLASSI non SETTATE ! " );
        }

        try {
            // boolean alreadyExists = new File(this.getFilesDir(),TestFile).exists();
            if (!alreadyExists) {
                /*
                  All'avvio dell'applicazione, l'activity verificherà
                  l'esistenza di un file utilizzato per determinare se
                  mostrare o meno l'activity Welcome.java. Se esiste,
                  significa che l'app è già stata aperta precedentemente.
                  Se non esiste, significa che non ho ancora mai aperto
                  l'applicazione. Verrà quindi mostrata all'utente
                  un'activiry di benvenuto atta ad illustrare il
                  funzionamento dell'applicazione. Verrà poi mostrata
                  l'activity principale a seguito di un tap sul tasto 'OK'.
                  Per mantenere la modularità del codice, verrà controllata
                  l'esistenza del file dell'activity corrente e nel caso in cui
                  non dovesse esistere, verrà creato da qui, lasciando che Welcome
                  mostri solamente una schermata introduttiva per l'utente.
                */
              //  toast.show();
                File file = new File(pathTestFile);
                file.createNewFile();
                startActivity(Welcome);
            } else if (alreadyExists) {
              // toast1.show();
            }
        } catch (Exception exc) {
            System.err.println("Exception Occured. This is a " + exc.getMessage());
        }
        //----------------------------------------
/*
        * SE PREMO GESTISCI POSIZIONE, VADO ALL'ACTIVITY DEDICATA ALLA GESTIONE DEL DISCO ORARIO
        */
        try {
            textView9mainActivityPosizione.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Position_Manager.class);
                    startActivity(intent);
                }
            });
        } catch (Exception exc) {
            System.err.println("Exception Occured. This is a " + exc.getMessage());
        }
        //----------------------------------------
        /*
        * SE PREMO GESTISCI DISCO ORARIO, VADO ALL'ACTIVITY DEDICATA ALLA GESTIONE DEL DISCO ORARIO
        */
        try {
            textView12ActivityMaindiscoorario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Gestisci_disco_orario.class);
                    startActivity(intent);
                }
            });
        } catch (Exception exc) {
            System.err.println("Exception Occured. This is a " + exc.getMessage());
        }


        //----------------------------------------
        /*
        * SE PREMO UTILITY, VADO ALL'ACTIVITY DEDICATA ALLE UTILITY
        */
        try {
        textView13toUtility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Utility_Activity.class);
                startActivity(intent);
            }
        });
        } catch (Exception exc) {
            System.err.println("Exception Occured. This is a " + exc.getMessage());
        }
        //----------------------------------------
        textView25setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Setup.class);
                finish();
                startActivity(intent);
            }
        });
        //----------------------------------------
        /*
        * SE PREMO ESCI, SI CHIUDE L'APPLICAZIONE
        */
        try {
        button4mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        } catch (Exception exc) {
            System.err.println("Exception Occured. This is a " + exc.getMessage());
        }
        //----------------------------------------
        /*
        * SE PREMO GUIDA, VADO ALL'ACTIVITY DEDICATA ALLE GUIDE
        */
        try {
        button3mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Guide.class);
                startActivity(intent);
            }
        });
        } catch (Exception exc) {
            System.err.println("Exception Occured. This is a " + exc.getMessage());
        }
        //----------------------------------------

      //  textView9mainActivityPosizione

    }
}
/*
* IDEE: -  Locate Car: questa applicazione può utilizzare un intent per l'invio di un comando
*          di un SMS contenente il comando eseguibile da un arduino contenuto nell'auto auto in
*          grado di riceverlo, interpretarlo. Una volta ricevuto, arduino invierà le coordinate
*          geografiche allo smartphone via sms. L'aaplicazione che sarà in grado di leggere il
*          contenuto dell'sms,guiserà l'utente verso l'auto.
*
*
*/

/*
*  APPUNTI: - Se dichiaro una toggle botton nel filem XML senza dichiararlo in java, l'applicazione
*             crasha.
*           - Android studio è cosi buggato che se elimino un elemento grafico dal file di layout e
*             faccio l'upload dell'APK sul telefono, potrebbe non partire l'app crushando all'apertura.
*             Per sistemare il problema, andare su file --> Invalidate Chaches / restart e premere just restart.
*             se non dovesse funzionare nemmeno questo, premere su invalidate and restart.
*           - Android studio è cosi buggato da cancellare da solo riche di codice a volte quando
*             ne cancello una io. Se l'app crasha senza motivo, controllare di aver inserito tutte
*             le classi JAVA nel file manifest!
*
*
* DA APPROFONDIRE:
*
* GESTIONE DISCO ORARIO
* - Pending activity
* - getBroadcast
*
*/