package com.example.ste.parkcar;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import pl.droidsonroids.gif.GifImageView;

/**
 * This activity shows the position acquirement screen used to memorize the car's position
 */
public class MemorizzaPosizioneAuto extends AppCompatActivity {


    private LocationManager locationManager;
    private LocationListener locationListener;
    private Position position;
    private static String actualLatitude;
    // DATABASES
    private PositionDatabase PositionDatabase =  new PositionDatabase (this);
    private Torna_all_auto_DATABASE db_1 = new Torna_all_auto_DATABASE (this);

    ActivityMemorize ActivityMemorize = MainActivity.ActivityMemorize;
    private  GregorianCalendar gcalendar = new GregorianCalendar();
    private String months[] = {"gennaio", "febbraio", "marzo",
                                "aprile", "maggio", "giugno",
                                "luglio", "agosto", "settembre",
                                "ottobre", "novembre", "dicembre"};
    String data = gcalendar.get(Calendar.DATE)+" " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + "";
    String ora = gcalendar.get(Calendar.HOUR) + ":" + gcalendar.get(Calendar.MINUTE) + ":" + gcalendar.get(Calendar.SECOND);

    double lati = 0.0;
    double loni = 0.0;

    private static String actualLongitude;
    GifImageView picture;
    String latitude = null;
    String longitude = null;

    public MemorizzaPosizioneAuto () {
        position = new Position();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorizza_posizione_auto);

        try {
            ActivityMemorize.setClasses (this);
            System.err.println("CLASSI SETTATE ! " );

        } catch (Exception exc) {
            ActivityMemorize.setActualClass(this);
            System.err.println("ERRORE. SETTO QUESTA CLASSE COME CLASSE CORRENTE ! " );
        }

        boolean found_location = false;
        final TextView lat = (TextView) findViewById(R.id.textView22);
        final TextView lon = (TextView) findViewById(R.id.textView20);
        TextView titolo_ricerca_posizione = (TextView) findViewById(R.id.textView21); // Titolo per la ricerca in corso
        TextView titolo_ricerca_posizione_copy = titolo_ricerca_posizione;            // Copia della stringa " ricerca in corso "
        Button copiaDati = (Button) findViewById(R.id.button10);                      // Bottone per la copia dei dati di posizione
        Button aggiornaPosizione = (Button) findViewById(R.id.button11);              // Bottone da premere per aggioranre la posizione
        Button home = (Button) findViewById(R.id.button3);
        Button indietro = (Button) findViewById(R.id.button4);
        picture = (GifImageView) findViewById(R.id.imageView6);


        getPosizione (picture, lat, lon );

        //----------------------------------------
        copiaDati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos = new Position(latitude, longitude).createStringOfPosition();
                Position position = new Position();
                position.setClipboard(getApplicationContext(), pos);
                Toast.makeText(getApplicationContext(), "GPS Lat = " + latitude + "\n lon = " + longitude, Toast.LENGTH_SHORT).show();

            }
        });
        //----------------------------------------
        aggiornaPosizione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPosizione (picture, lat, lon );
            }
        });
        //----------------------------------------
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            });
        //----------------------------------------
        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityMemorize.setBothClasses(Position_Manager.class,this);
                Intent intent = new Intent(getApplicationContext(), (Class<?>) ActivityMemorize.getpreviousClass());
                finish();
                startActivity(intent);
            }
        });
        //-------SIMULA POSIZIONE BUTTON---------------------------------
        Button simulaPosizione = (Button) findViewById(R.id.button12);
        simulaPosizione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position.simulatePosition();
                lati = Double.parseDouble(position.getLatitude());
                loni = Double.parseDouble(position.getLongitude());
                latitude = lati+"";
                longitude = loni+"";
                position.setLatitude(latitude);         // Inserisco la latitudine all'interno del campo latidudine della classe Position
                position.setLongitude(longitude);       // Inserisco la longitudine all'interno del campo longitudine della classe Position
                String actualPosition = position.createStringOfPosition();      // Creo una stringa di posizioni in cui latitudine e longitudine sono separate da virgola
                picture.setImageResource(R.drawable.ok);
                Toast.makeText(getApplicationContext(), "COORDINATE GPS \nLat = " + lati + "\n lon = " + loni, Toast.LENGTH_SHORT).show();
                lat.setText("LATITUDINE: " + latitude);
                lon.setText("LONGITUDINE " + longitude);
                actualLatitude = position.getLatitude();
                actualLongitude = position.getLongitude();
                String actualPos = position.getStringOfPosition();

                //-----TEMPORANEO-----
                PositionDatabase db = new PositionDatabase(getApplicationContext());
                Torna_all_auto_DATABASE db_1 = new Torna_all_auto_DATABASE (getApplicationContext());

                db.getWritableDatabase();
                db_1.getWritableDatabase();

                boolean b = db.doesDatabaseExist(getApplicationContext(), "PARKCAR_DATABASE.db" );
                boolean bb = db_1.doesDatabaseExist(getApplicationContext(), "Torna_all_auto_DB.db" );;
                System.err.println("Vediamo se il database esiste " + b);
                System.err.println("Vediamo se il db_1 esiste " + bb);

                System.err.println("PositionDatabase.selectMAX(\"id\").toString() -> " +PositionDatabase.selectMAX("id").toString());     // OK, Trova la posizione corretta

                show ("IL DATABASE CONTIENE COSE? --> " + db.isDatabaseEmpty() ) ; // Verifico se il database contiene qualcosa: se la contiene,
                                                                                   // all'apertura di torna all'auto, si tornerà alla posizione
                                                                                   // riferita dal database. Se invece è vuoto, si richiedera di
                                                                                    // memorizzare la posiione

                boolean mem = memorizeAutoPosition(actualPosition, data, ora );
                System.err.println("Vediamo se ha memorizzato nella tabella i valori " +  mem);

                boolean mem_1 = memorizeTornaAllAutoPosition(actualPos);
                System.err.println(actualPos);
                System.err.println("Vediamo se ha memorizzato nella tabella i valori " +  mem_1);
                /**
                 * PER APRIRE UNA FINESTRA DI DIALOGO, OCCORRE UTILIZZARE IL NOME DELLA CLASSE SEGUITO DA .this
                 * ALTRIMENTI NON FUNZIONA!
                 */
       //       db_1.viewAllAutoPosition(MemorizzaPosizioneAuto.this);
       //       db.viewLastPos(MemorizzaPosizioneAuto.this);
                db.selectJustLastPosition(MemorizzaPosizioneAuto.this);



            }
        });
        //----------------------------------------
    /*
    * END OF onCreate() METHOD
    */
    }


        //---------------GPS POSITION RESEARCH-------------------------

    /**
     * This method is used to find the position of the user.
     *
     * @param picture to change depending on the result obtained by the gps sensor
     * @param lat latitude to stamp
     * @param lon longitude to stamp
     */
    public void getPosizione (GifImageView picture, TextView lat, TextView lon ) {
        ActivityCompat.requestPermissions(MemorizzaPosizioneAuto.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        GpsTracker gt = new GpsTracker(getApplicationContext());
        Location l = gt.getLocation();
        if (l == null) {
            Toast.makeText(getApplicationContext(), "GPS Disattivato o ricezione segnale assente", Toast.LENGTH_SHORT).show();
            picture.setImageResource(R.drawable.x);
        } else {
             lati = l.getLatitude();
             loni = l.getLongitude();

            latitude = lati+"";
            longitude = loni+"";
            picture.setImageResource(R.drawable.ok);
            Toast.makeText(getApplicationContext(), "COORDINATE GPS \nLat = " + lati + "\n lon = " + loni, Toast.LENGTH_SHORT).show();

            lat.setText("LATITUDINE: " + latitude);
            lon.setText("LONGITUDINE " + longitude);

            position.setLatitude(latitude);         // Inserisco la latitudine all'interno del campo latidudine della classe Position
            position.setLongitude(longitude);       // Inserisco la longitudine all'interno del campo longitudine della classe Position
            String actualPosition = position.createStringOfPosition();      // Creo una stringa di posizioni in cui latitudine e longitudine sono separate da virgola

            actualLatitude = position.getLatitude();
            actualLongitude = position.getLongitude();

            System.err.println("ACTUAL POSITION -> " + position.getStringOfPosition());     // OK, Trova la posizione corretta

            //-----TEMPORANEO-----
            PositionDatabase db = new PositionDatabase(getApplicationContext());
            boolean mem = memorizeAutoPosition(actualPosition, data, ora );
            System.err.println("Vediamo se ha memorizzato nella tabella i valori " +  mem);

            show (db.selectJustLastPosition(MemorizzaPosizioneAuto.this) );

        }
    }
    //-----------GETTERS AND SETTERS -----------------------------
    public static String getActualLatitude() {
        return actualLatitude;
    }
    //----------------------------------------

    public static void setActualLatitude(String actualLatitude) {
        MemorizzaPosizioneAuto.actualLatitude = actualLatitude;
    }

    //----------------------------------------

    public static String getActualLongitude() {
        return actualLongitude;
    }

    //----------------------------------------

    public static void setActualLongitude(String actualLongitude) {
        MemorizzaPosizioneAuto.actualLongitude = actualLongitude;
    }
    //----------------------------------------

    public boolean memorizeAutoPosition (String posizioni_memorizzate, String data, String time) {
        boolean isInserted  = PositionDatabase.insertData(posizioni_memorizzate,data, time);
        if(isInserted == true)
            Toast.makeText(MemorizzaPosizioneAuto.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MemorizzaPosizioneAuto.this,"Data not Inserted",Toast.LENGTH_LONG).show();
        return isInserted;
    }

    //----------------------------------------

    /**
     * This method is used to save the actual car's position into
     * the db_01 position, so the user will be able to accees to it
     * when he'll wish to return to his auto by quering the database.
     *
     * @param posizioni_memorizzate posizione memorizzata dell'auto
     * @return un booleano per capire se l'auto è stata aggiunta o meno
     */
    public boolean memorizeTornaAllAutoPosition (String posizioni_memorizzate) {
        boolean isInserted  = db_1.insertData(posizioni_memorizzate);
        if(isInserted == true)
            Toast.makeText(MemorizzaPosizioneAuto.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MemorizzaPosizioneAuto.this,"Data not Inserted",Toast.LENGTH_LONG).show();
        return isInserted;
    }
    //----------------------------------------

    public void show (Object o) {
        System.err.println(o);
    }

}

