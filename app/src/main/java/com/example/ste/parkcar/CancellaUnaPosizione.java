package com.example.ste.parkcar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CancellaUnaPosizione extends AppCompatActivity {
    ActivityMemorize ActivityMemorize = MainActivity.ActivityMemorize;
    TextView cosavuoicancellare;
    private String elementToDelete = "";
    EditText editText;
    Button elimina;
    Button indietro;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancella_una_posizione);
        home = (Button) findViewById(R.id.button3);
        indietro = (Button) findViewById(R.id.button4);
        editText = (EditText) findViewById(R.id.editText);
        elimina = (Button) findViewById(R.id.button);
        cosavuoicancellare =(TextView)findViewById(R.id.t1);

        //----------------LISTENERS-------------------------
        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elementToDelete = editText.getText().toString();
                PositionDatabase db = new PositionDatabase(getApplicationContext());
                Integer deletedRows = db.onDeleteData(editText.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(CancellaUnaPosizione.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CancellaUnaPosizione.this,"Data not Deleted", Toast.LENGTH_LONG).show();
            }
        });
        //----------------LISTENERS-------------------------
        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityMemorize.setBothClasses(Position_Manager.class,this);
                Intent intent = new Intent(getApplicationContext(), Posizioni_memorizzate.class);
                finish();
                startActivity(intent);
            }
        });
        //----------------------------------------
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
