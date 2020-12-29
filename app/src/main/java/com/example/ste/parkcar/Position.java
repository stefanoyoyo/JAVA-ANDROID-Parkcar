package com.example.ste.parkcar;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by ament on 31/07/2017.
 *
 * This class is used to manipulate the position data.
 * It allows to:
 * -> Generate a unique String contaning latitude and longitude
 * -> Copy a String to the clipboard
 *
 */

public class Position {

    private String latitude;
    private String longitude;
    private String StringOfPosition;

    public Position (String lat,String lon) {
        latitude = lat;
        longitude = lon;
    }

    public Position() {

    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    //----------------------------------------

    /**
     * This method sets the variable StringOfPosition
     *
     * @return a string containing langitude e longitude separated by a comma
     */
    public String createStringOfPosition () {
        StringOfPosition = latitude + " , " + longitude;
        return StringOfPosition;
    }
    //----------------------------------------7
    public String getStringOfPosition () {
        return StringOfPosition;
    }
    //----------------------------------------
    public void simulatePosition () {
        /**
         * This method simulates the position of duomo di milano
         */
        latitude = "45.464211";
        longitude = "9.191383";
    }
    //----------------------------------------
        public void setBothPosition (String pos) {
            ArrayList <Character> w1 = new ArrayList <Character> ();
            ArrayList <Character> w2 = new ArrayList <Character> ();

            boolean comma = false;
            if (pos.contains(",")) {
                comma = true;
            }
            if (comma) {
                int posComma = 0;
                // Aggiungo tutte le lettere prima della virgola nella
                // prima ArrayList, che simboleggia la prima parola
                for (int i = 0; pos.charAt(i) != ','; i++) {
                    w1.add(pos.charAt(i));
                    posComma = i;   // finche non c'è la virgola, continuo ad inserire
                                    // il valore di i all'interno di posComma; quando
                                    // verrà trovata la virgola, l'algoritmo smetterà
                                    // di farlo (cioè, verra salvato il num. fino alla
                                    // posizione prima di quella della virgola, sarà
                                    // quindi sufficiente incrementarla di uno fuori
                                    // fuori dal for per sapere la pos. della vergola!
                }
                    posComma = posComma +1; // posizione della virgola

                // Aggiungo tutte le lettere dopo della virgola nella
                // prima ArrayList, che simboleggia la seconda parola
                for (int i = posComma +1; i< pos.length(); i++) {
                    w2.add(pos.charAt(i));
                }
            }

            // Trasformo le liste in
            latitude = arrayListToString(w1);   show("a -> " + latitude);
            longitude = arrayListToString(w2);   show("b -> " + longitude);

        }
    //----------------------------------------
        public String arrayListToString (ArrayList <Character> a) {
            StringBuffer s = new StringBuffer();
            for (int i = 0; i< a.size(); i++) {
                s.append(a.get(i));
            }
           return s.toString();
        }
    //----------------------------------------
    public void show (Object o) {
        System.err.println(o);
    }
    //----------------------------------------


    /**
     * This method allows the user to copy a text into the clip board
     *
     * @param context to set as " this "
     * @param text to copy into the clipboard
     */
    public void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }
}
