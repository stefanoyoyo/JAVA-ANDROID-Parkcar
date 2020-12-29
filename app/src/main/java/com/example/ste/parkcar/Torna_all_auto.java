package com.example.ste.parkcar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.ste.parkcar.MemorizzaPosizioneAuto.getActualLatitude;
import static com.example.ste.parkcar.MemorizzaPosizioneAuto.getActualLongitude;

/**
 *  SCRIVERO' IL TESTO IN ITALIANO:
 *
 *  Questa classe viene utilizzata per richiamare le mappe. Per poterlo fare, ho dovuto provvedere alla creazione
 *  di un nuovo progetto sul sito https://console.cloud.google.com/ e richiedere successivamente un codice
 *  alfanumerico (API_KEY) da poi inserire all'interno del file manifest. Ho poi provveduto a richiamare nel
 *  metodo onCreate() il metodo onMapReady() facendone l'override dalla sua classe. Quel metodo a sua volta, richiama
 *  un metodo utilizzato per scegliere se richiamare il metodo relativo alla visualizzazione della mappa di google
 *  maps, oppure quello relativo alla visualizzazione delle mappe interne (la decisione viene presa nell'activity
 *  setup mediante l'opportuna pressione dei corretti ToggleButton). Ho poi provveduto, nell'implementazione del metodo
 *  delle mappe interne, sul sito https://console.cloud.google.com/ ad abilitare la funzionalità che mi permette di disegnare
 *  un cammino sulle mappe interne dopo l'inserimento di 2 coordinate geografiche (nel mio caso, una coordinata sarà quella
 *  del posto in cui l'utente ha parcheggiato l'auto, mentre l'altra sarà quella della sua posizione corrente).
 *
 */
public class Torna_all_auto extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Position position;
    private Setup setup;
    private GpsTracker GpsTracker;
    private static final int PERMISSION_REQUEST_CODE = 15442;
    private Torna_all_auto_DATABASE db_1 = new Torna_all_auto_DATABASE (this);

    public Torna_all_auto() {
        position = new Position();
        setup = new Setup ();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torna_all_auto);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

//----------------------------------------

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Toast.makeText(getApplication(),"NUMERO " + setup.whatTypeOfMapsAreUsed(), Toast.LENGTH_SHORT).show();
        chooseMapView (setup.whatTypeOfMapsAreUsed());          /*   setup.whatTypeOfMapsAreUsed()   */
                                    //   Per ora uso il parametro 0 per usare SEMPRE le google maps.
                                    //   Inserire come parametro setup.whatTypeOfMapsAreUsed() per
                                    //   poter scegliere dalle impostazioni le mappe volute.


    }
    //----------------------------------------
    public void useGoogleMapsForNavigation () {
        PositionDatabase db = new PositionDatabase(getApplicationContext());
        try {
            boolean permission = checkPermission();          // Controllo che vi siano i permessi all'accesso GPS
            if (!permission) {
                requestPermission ();
            }

        //    GPS_enable();

            Position p = new Position();
            if (db.isDatabaseEmpty ()) {
                /**
                 * This method return false if the database is empty, true otherwise
                 */
                String s = db.selectJustLastPosition(Torna_all_auto.this);
                p.setBothPosition(s); // le posizioni vengono splittate in lat e long


                MemorizzaPosizioneAuto.setActualLatitude(p.getLatitude());
                MemorizzaPosizioneAuto.setActualLongitude(p.getLongitude());

                p.setLatitude(null);
                p.setLongitude(null);


            LatLng myActualPosition = new LatLng(Double.parseDouble(getActualLatitude()), Double.parseDouble(getActualLongitude()));
            mMap.addMarker(new MarkerOptions().position(myActualPosition).title("Posizione ATTUALE"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myActualPosition, 15));
            mMap.setMyLocationEnabled(true);
            Toast.makeText(getApplicationContext(),"GOOGLE MAPS",Toast.LENGTH_SHORT).show();

            } else {
                throw new Exception();
            }

        } catch (Exception exc) {
            Toast.makeText(getApplicationContext(), "Non hai localizzato l'auto precedentemente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MemorizzaPosizioneAuto.class);
            finish();
            startActivity(intent);


            System.err.println("LATITUDE -> " + getActualLatitude()); // è null. rimediare.  HO RIMEDIATO CREANDO DUE VARIABIL STATICHE NELLE QUALI INSERIRE LE POSIZIONI
            System.err.println("LONGITUDE -> " + getActualLongitude()); // è null. rimediare. HO RIMEDIATO CREANDO DUE VARIABIL STATICHE NELLE QUALI INSERIRE LE POSIZIONI

            mMap.setMyLocationEnabled(true);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        }
    }
    //----------------------------------------
    public void useInternalMapsForNavigation () {
        try {
            LatLng myActualPosition = new LatLng(Double.parseDouble(getActualLatitude()), Double.parseDouble(getActualLongitude()));
            mMap.addMarker(new MarkerOptions().position(myActualPosition).title("Posizione DESIDERATA"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myActualPosition, 15));
            mMap.setMyLocationEnabled(true);
            Toast.makeText(getApplicationContext(),"INAPP MAPS",Toast.LENGTH_SHORT).show();


        } catch (Exception exc) {
            Toast.makeText(getApplicationContext(), "Non hai localizzato l'auto precedentemente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MemorizzaPosizioneAuto.class);
            finish();
            startActivity(intent);


            System.err.println("LATITUDE -> " + getActualLatitude()); // è null. rimediare.  HO RIMEDIATO CREANDO DUE VARIABIL STATICHE NELLE QUALI INSERIRE LE POSIZIONI
            System.err.println("LONGITUDE -> " + getActualLongitude()); // è null. rimediare. HO RIMEDIATO CREANDO DUE VARIABIL STATICHE NELLE QUALI INSERIRE LE POSIZIONI

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);

            /*
            *  DA COMPLETARE SUCCESSIVAMENTE, ORA FARO' SI CHE SI APRANO SOLO LE MAPPE DI GOOGLE!
            *
            */
        }
    }
    //----------------------------------------

    /**
     * This method allows the user to decide if to use the app integrated map or the google maps application for his navigation
     *
     * @param n number to use for choosing the correct map to view:
     *          n == 0: The user chooses the GOOGLE MAPS APPLICATION
     *          n == 1: The user chooses the INTEGRATED INAPP MAPS
     *          n == -1: No choice has been taken. By default, INTEGRATED INAPP MAPS will be used
     *
     *  HOW TO USE IT:  this method receives an integer rapresenting the map type to show.
     *                  it must be called in the onMapReady() method. The parameter of the
     *                  chooseMapView() method is taken by using an object from the setup class
     *                  using the whatTypeOfMapsAreUsed variable. That variable is modified in the
     *                  setup class after the pression of the relative ToogleButton
     */
    public void chooseMapView (int n) {
        if (n == 0) {
            useGoogleMapsForNavigation ();
        } else if (n == 1 || n == -1) {
            useInternalMapsForNavigation ();
        } else {
            Toast.makeText(getApplicationContext(),"Scelta tipo mappa non effettuata; di default userai le mappe INTERNE di ParkCar",Toast.LENGTH_LONG).show();
            useInternalMapsForNavigation ();
        }
    }
    //----------------------------------------
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    //----------------------------------------
    private void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(getApplicationContext(),"Per favore, abilita il GPS nelle impostazioni",
            Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
        System.err.println("END OF -> requestPermission");

    }


    private void GPS_enable () {
        /**
         * A QUANTO PARE FUNZIONA SOLO SU TELEFONI CON TPERMESSI DI ROOT ATTIVI
         */
        Intent intent=new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        sendBroadcast(intent);
        System.err.println("END OF -> GPS_enable");
    }
    //----------------------------------------
    /*
    public void onRequestPermissionsResult(int requestCode, String permissions[],int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Snackbar.make(view,
                            "Permission Granted, Now you can access location data.",
                            Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(view,
                            "Permission Denied, You cannot access location data.",
                            Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
    */
}
