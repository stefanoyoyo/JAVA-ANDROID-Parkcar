package com.example.ste.parkcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Litri_carburante_spesi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litri_carburante_spesi);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        final EditText editTextnumgiorni = (EditText) findViewById(R.id.editTextnumgiorni);  String numeroGiorni = null;
        String text = editTextnumgiorni.getText().toString();
        final EditText editTextnumkm = (EditText) findViewById(R.id.editTextnumkm);  String numeroKM = null;
        String text1 = editTextnumkm.getText().toString();
        final EditText editTextnumviaggi = (EditText) findViewById(R.id.editTextnumviaggi) ;
        String text2 = editTextnumviaggi.getText().toString();



        final ToggleButton toggleButtonandata = (ToggleButton) findViewById(R.id.toggleButtonandata);
        final ToggleButton toggleButtonandataeritorno = (ToggleButton) findViewById(R.id.toggleButtonandataeritorno);
        final TextView textView9distanzadapercorrere = (TextView) findViewById(R.id.textView9distanzadapercorrere);
        final Utility utility = new Utility ();

        final Toast toast1 = Toast.makeText(this,toggleButtonandataeritorno.getText().toString(), Toast.LENGTH_SHORT);
        final Toast toast = Toast.makeText(this,toggleButtonandata.getText().toString(), Toast.LENGTH_SHORT);

        final TextView textView9numgg = (TextView) findViewById(R.id.textView9numgg);

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
                Intent intent = new Intent(getApplicationContext(), Utility_Activity.class);
                finish();
                startActivity(intent);
            }
        });

        try {
            /*
             * SE PREMO ANDATA, SCRIVO LA STRINGA ANDATA NEL METODO RICHIAMATO DA UTILITY
             */
            toggleButtonandata.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                CharSequence tipoviaggio = null;
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String tipo_viaggio = textView9distanzadapercorrere.getText().toString();
                    Double numero = 0.0;
                    boolean a = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  (editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean b = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  !(editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean c = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && !(editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  (editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean d = !(editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  (editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean e = !(editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && !(editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  (editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean f = !(editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  !(editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean g = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && !(editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  !(editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean h = (!(editTextnumgiorni.getText().toString() == null) || !(editTextnumgiorni.getText().toString().equals("")))
                            && (!(editTextnumkm.getText().toString() == null) || !(editTextnumkm.getText().toString().equals("")))
                            &&  (!(editTextnumviaggi.getText().toString() == null) || !(editTextnumviaggi.getText().toString().equals("")));

                    if (a || b || c || d || f || g) {
                        numero = utility.litri_spesi(0,0,0,"andata");
                        if (isChecked) {
                            toast.show();
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOn(tipoviaggio);
                            String num = numero.toString();
                            textView9distanzadapercorrere.setText("0 L");
                        } else if (!isChecked) {
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOff(tipoviaggio);
                            toggleButtonandataeritorno.setChecked(false);
                            textView9distanzadapercorrere.setText("");
                            toggleButtonandata.setTextOff("andata");
                        }

                    } else if (e) {
                        numero = utility.litri_spesi(Double.parseDouble(editTextnumgiorni.getText().toString()),Double.parseDouble(editTextnumkm.getText().toString()),13.0,"andata");
                        if (isChecked) {
                            toast.show();
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOn(tipoviaggio);
                            toggleButtonandata.setChecked(true);
                            String num = numero.toString();
                            textView9distanzadapercorrere.setText(num + " L");
                        } else if (!isChecked) {
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOff(tipoviaggio);
                            numero = utility.litri_spesi(Double.parseDouble(editTextnumgiorni.getText().toString()),Double.parseDouble(editTextnumkm.getText().toString()),0,"andata");
                            try {
                                textView9distanzadapercorrere.setText("0 L");
                            } catch (NullPointerException exc) {
                                System.err.println("Ooohh what a fatal null pointer exception here! ");
                            }
                            toggleButtonandataeritorno.setChecked(false);
                            toggleButtonandata.setTextOff("andata");
                        }
                    }


                    else if (h ) {
                        numero = utility.litri_spesi(Double.parseDouble(editTextnumgiorni.getText().toString()),Double.parseDouble(editTextnumkm.getText().toString()),Double.parseDouble(editTextnumviaggi.getText().toString()),"andata") * Double.parseDouble(editTextnumgiorni.getText().toString());
                        if (isChecked) {
                            toast.show();
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOn(tipoviaggio);
                            String num = numero.toString();
                            textView9distanzadapercorrere.setText(num + " L");
                        } else if (!isChecked) {
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOff(tipoviaggio);
                            textView9distanzadapercorrere.setText("");
                            toggleButtonandataeritorno.setChecked(false);
                            toggleButtonandata.setTextOff("andata");
                        }
                    }
                }

            });
        }  catch (Exception exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
        }
        //----------------------------------------
        try {
            /*
             * SE PREMO ANDATA e RITORNO, SCRIVO LA STRINGA ANDATA e RITORNO NEL METODO RICHIAMATO DA UTILITY
             */
            toggleButtonandataeritorno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                CharSequence tipoviaggio = null;
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String tipo_viaggio = "andata e ritorno";
                    Double numero = 0.0;
                    boolean a = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  (editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean b = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  !(editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean c = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && !(editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  (editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean d = !(editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  (editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean e = !(editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && !(editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  (editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean f = !(editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && (editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  !(editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean g = (editTextnumgiorni.getText().toString() == null || editTextnumgiorni.getText().toString().equals(""))
                            && !(editTextnumkm.getText().toString() == null || editTextnumkm.getText().toString().equals(""))
                            &&  !(editTextnumviaggi.getText().toString() == null || editTextnumviaggi.getText().toString().equals(""));
                    boolean h = (!(editTextnumgiorni.getText().toString() == null) || !(editTextnumgiorni.getText().toString().equals("")))
                            && (!(editTextnumkm.getText().toString() == null) || !(editTextnumkm.getText().toString().equals("")))
                            &&  (!(editTextnumviaggi.getText().toString() == null) || !(editTextnumviaggi.getText().toString().equals("")));

                    boolean x0 = (!editTextnumgiorni.getText().toString().equals("0"));
                    boolean x1 = (!editTextnumgiorni.getText().toString().equals("1"));
                    boolean x2 = (!editTextnumgiorni.getText().toString().equals("2"));
                    boolean x3 = (!editTextnumgiorni.getText().toString().equals("3"));
                    boolean x4 = (!editTextnumgiorni.getText().toString().equals("4"));
                    boolean x5 = (!editTextnumgiorni.getText().toString().equals("5"));
                    boolean x6 = (!editTextnumgiorni.getText().toString().equals("6"));
                    boolean x7 = (!editTextnumgiorni.getText().toString().equals("7"));
                    boolean x8 = (!editTextnumgiorni.getText().toString().equals("8"));
                    boolean x9 = (!editTextnumgiorni.getText().toString().equals("9"));

                    boolean z ;

                    if (a || b || c || d || f || g ) {
                        numero = utility.litri_spesi(0,0,0,"andata e ritorno");
                        if (isChecked) {
                            toast.show();
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOn(tipoviaggio);
                            toggleButtonandata.setChecked(true);
                            String num = numero.toString();
                            textView9distanzadapercorrere.setText("0 L");
                        } else if (!isChecked) {
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOff(tipoviaggio);
                            toggleButtonandataeritorno.setChecked(false);
                            textView9distanzadapercorrere.setText("0 L");
                            toggleButtonandata.setTextOff("andata");
                        }

                    } else if (e) {
                        numero = utility.litri_spesi(Double.parseDouble(editTextnumgiorni.getText().toString()),Double.parseDouble(editTextnumkm.getText().toString()),13.0,"andata e ritorno");
                        if (isChecked) {
                            toast.show();
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOn(tipoviaggio);
                            toggleButtonandata.setChecked(true);
                            String num = numero.toString();
                            textView9distanzadapercorrere.setText(num + " L");
                        } else if (!isChecked) {
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOff(tipoviaggio);
                            numero = utility.litri_spesi(Double.parseDouble(editTextnumgiorni.getText().toString()),Double.parseDouble(editTextnumkm.getText().toString()),0.0,"andata");
                            try {
                                textView9distanzadapercorrere.setText(numero.toString() + " L");
                            } catch (NullPointerException exc) {
                                System.err.println("Ooohh what a fatal null pointer exception here! ");
                            }
                            toggleButtonandataeritorno.setChecked(false);
                            toggleButtonandata.setTextOff("andata");
                        }
                    }

                    else if (h) {
                        numero = utility.litri_spesi(Double.parseDouble(editTextnumgiorni.getText().toString()),Double.parseDouble(editTextnumkm.getText().toString()),Double.parseDouble(editTextnumviaggi.getText().toString()),"andata e ritorno") * Double.parseDouble(editTextnumgiorni.getText().toString());
                        if (isChecked) {
                            toast.show();
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOn(tipoviaggio);
                            toggleButtonandata.setChecked(true);
                            String num = numero.toString();
                            textView9distanzadapercorrere.setText(num + " L");
                        } else if (!isChecked) {
                            tipoviaggio = "andata";
                            toggleButtonandata.setTextOff(tipoviaggio);
                            numero = utility.litri_spesi(Double.parseDouble(editTextnumgiorni.getText().toString()),Double.parseDouble(editTextnumkm.getText().toString()),Double.parseDouble(editTextnumviaggi.getText().toString()),"andata") ;
                            try {
                                textView9distanzadapercorrere.setText(numero.toString() + " L");
                            } catch (NullPointerException exc) {
                                System.err.println("Ooohh what a fatal null pointer exception here! ");
                            }
                            toggleButtonandataeritorno.setChecked(false);
                            toggleButtonandata.setTextOff("andata");
                        }
                    }
                }

            });
        }  catch (Exception exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
        }
        //----------------------------------------






    }


    public String checkString (String stringToCheck) {
        /*
        * METODO PER EVITARE CHE L'APP CRUSHI SE LA STRINGA PASSATA
        * COME PARAMETRO AL METODO km_da_percorrere PRELEVATA DALLA
        * EDITTEEXT FOSSE VUOTA
        */
        try {
            Toast invalidString = Toast.makeText(this, "specifica la tipologia di viaggio", Toast.LENGTH_SHORT);
            if (stringToCheck == null || stringToCheck.equals("")) {
                invalidString.show();
                System.err.println("\n\n\n\n" + "Hai inserito una stringa vuota!" + "\n\n\n\n");
            }
        } catch (NumberFormatException exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());


        }
        return stringToCheck;
    }







}
 /*
           try {

        }  catch (Exception exc) {
            System.err.println("Exception OCCURRED! This is a " + exc.getMessage());
        }

 */