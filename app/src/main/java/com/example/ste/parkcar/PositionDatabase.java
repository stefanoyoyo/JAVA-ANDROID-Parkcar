package com.example.ste.parkcar;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

/**
 * Created by ament on 11/08/2017.
 *
 * MODEL OF DATABASE NEEDED
 *
 * PARKCAR_DATABASE (Id int primary key AUTOINCREMENT, posizioni_memorizzate TEXT, data DATE, ora DATETIME);
 *
 * TEORIA:  - per eseguire delle query senza ritorno di valori, è sufficiente scrivere dei comandi SQL all'interno
 *          di db.execSQL(QUERY_TO_RUN).
 *          - Se invece fosse necessario avere dei valori di ritorno, come nelle query,
 *          sarebbe necessario usare un oggetto di tipo cursor, nel quale inserire il risultato della query fatta
 *          mediante  Cursor res = db.rawQuery("select * from "+table_name,null);.
 *          - Per inserire all'interno di una
 *          tabella, occorre inserire tramite il metodo insert, un oggetto di tipo ContentValue in cui inserire i
 *          valori, uno per colonna tramite il metodo put.
 *
 *          NB: è possibile fare delle query sia mediante l'utilizzo di java, che mediante l'utilizzo di SQL!
 *
 * COME CREARE UN DATABASE: - creare una classe che estende SQLiteOpenHelper e implementa i suoi metodi astratti
 *                          - richimare il super nel construttore con 4 parametri
 *                            ES: super(context, "PARKCAR_DATABASE.db", null, 1);
 *                          - Nella classe che utilizza il database (creando un'istanza di quello che lo crea),
 *                            occorre utilizzare (sull'istanza) il metodo .getWritableDatabase();
 *                            ES: nella classe MemorizzaPosizioneAuto
 *
 *                                PositionDatabase db = new PositionDatabase(getApplicationContext());
 *                                ...
 *                                db.getWritableDatabase();
 */

public class PositionDatabase extends SQLiteOpenHelper {

    Context context;
    private String database_name = "PARKCAR_DATABASE.db";
    private String table_name = "POSITIONS_TABLE";
    private static String how_to_order = "data";                    // This variable is initialized to "data". It allows to order the
                                                                    // result of the query specifing how to order.

    public PositionDatabase(Context context) {
        super(context, "PARKCAR_DATABASE.db", null, 1);
  //    SQLiteDatabase database = this.getWritableDatabase();       // This line allows the program to view and access the database
                                                                    // HO COMMENTATO QUESTA LINEA DI CODICE PERCHE TANTO LA INSERICO
                                                                    // NEL METODO INSERT!
    }
    //-----------------------------------------

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
            String CREATE_POSITIONS_TABLE = "CREATE TABLE " + table_name + "( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "posizioni_memorizzate TEXT, " +
                    "data DATE," +
                    "ora DATETIME )";
            // create books table
            db.execSQL(CREATE_POSITIONS_TABLE);

    }
    //-----------------------------------------

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String update = "DROP TABLE IF EXISTS " + table_name;
        onCreate(sqLiteDatabase);
    }
    //-----------------------------------------

    /**
     * This method can be used to update the values contained into the row of a database table
     *
     * @param id to update
     * @param name to update
     * @param surname to update
     * @param marks to update
     * @return true if update happened succesfully
     */
    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("posizioni_memorizzate",name);
        contentValues.put("data",surname);
        contentValues.put("ora",marks);
        db.update(table_name, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    //-----------------------------------------
    /**
     * This method deletes the rows equal to the id value. Id is a primary key so just one row will be deleted
     *
     * @param id to delete fro the table
     * @return the number of rows deleted
     */
    public Integer onDeleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table_name, "ID = ?",new String[] {id});
    }
    //-----------------------------------------

    public void deleteAll () {
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete All
        db.execSQL("delete from "+ table_name);
        // 3. close
        db.close();
    }
    //-----------------------------------------
    public boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    //-----------------------------------------

    /**
     * This method adds the parameter elements into a row of the table
     *
     * NO ID WILL BE ADDED, BECAUSE IT AUTOINCREMENTS ITSELF!
     * @param posizioni_memorizzate second column
     * @param data third column
     * @param ora fourth column
     * @return false if .insert method from SQLiteOpenHelper inserts the value into the table and return -1, true otherwise.
     */
    public boolean insertData (String posizioni_memorizzate, String data, String ora) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("posizioni_memorizzate", posizioni_memorizzate);
        contentValues.put("data",data);
        contentValues.put("ora",ora);
        long result = database.insert(table_name,null,contentValues);
        if (result != 1) {
            return true; }
            else {
                return false; }
    }

    //--------------- QUERIES ON DATABASE ------------------------

    /**
     * This method is used to get all thr data from the table.
     *
     * @return a method contaning the data required. Its containement can't be shown, it needs to be
     *         tranformed into a stirng buffer, so another method which does it is required.
     */
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name,null);
        return res;
    }
    //-----------------------------------------

    /**
     * This method is used to show the content of the table_name table. It does  query to the
     * database_name by using the getAllData() method. Finally, it translates the values contained
     * the values of a row to a String value.
     *
     * @param c to get the context from the activity. No getApplicationContext() can be used here, but just "name_activity.class"!
     *
     * THIS METHOD IS USED TO SHOW THE CONTAINMENT OF THE TABLE
     */
    public void viewAll(Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id pos: "+ res.getString(0)+"\n");
            buffer.append("Data: "+ res.getString(2)+"\n");
            buffer.append("Ora: "+ res.getString(3)+"\n");
            buffer.append("Pos: "+ res.getString(1)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString(),c);
    }
    //-----------------------------------------

    /**
     * This method is used to get the max value of a SQLIte colums
     *
     * @param parameter_to_select to get from the table
     * @return The data
     */
    public Cursor selectMAX (String parameter_to_select) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT MAX(" + parameter_to_select + ") FROM " + table_name,null) ;
        return res;
    }
    //-----------------------------------------
    public void viewLastPos (Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = getAllData();;
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToLast();
        buffer.append("Id pos: "+ res.getString(0)+"\n");
        buffer.append("Data: "+ res.getString(2)+"\n");
        buffer.append("Ora: "+ res.getString(3)+"\n");
        buffer.append("Pos: "+ res.getString(1)+"\n\n");

        // Show all data
        showMessage("LAST POSITION DATA \n",buffer.toString(),c);
    }
    //-----------------------------------------
        public String selectJustLastPosition (Context c) {
            SQLiteDatabase myDb = this.getWritableDatabase();
            Cursor res = getAllData();;
            if(res.getCount() == 0) {
                // show message
                showMessage("Error","Nothing found",c);
                return null;
            }
            StringBuffer buffer = new StringBuffer();
            res.moveToLast();
            buffer.append("Pos: "+ res.getString(1)+"\n\n");

            // Show all data
          //   showMessage("LAST POS. ",buffer.toString(),c);

            return res.getString(1);
        }
    //-----------------------------------------

    public void viewMax (Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = selectMAX("id");
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("MAX ID "+ res.getString(0)+"\n");
        }

        // Show all data
        showMessage("MAX ID ",buffer.toString(),c);
    }

    //-----------------------------------------

    /**
     * This method is used to show a message into an alertDialog windows
     *
     * @param title to set for the window
     * @param Message to show inside the window
     */
    public void showMessage(String title,String Message,Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    //-----------------------------------------
    public void dropAndRemakeTable () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE "+ table_name);
        System.err.println("TABLE DROPPED SUCCESFULLY!");
        onCreate(db);
        System.err.println("TABLE CREATED SUCCESFULLY!");
        db.close();
    }
    //-----------------------------------------
    public boolean isDatabaseEmpty () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + table_name, null);
        Boolean rowExists;
        if (mCursor.moveToFirst())
        {
            // DO SOMETHING WITH CURSOR
            rowExists = true;
        } else
        {
            // I AM EMPTY
            rowExists = false;
        }
        return rowExists;
    }
    //-----------------------------------------
}
