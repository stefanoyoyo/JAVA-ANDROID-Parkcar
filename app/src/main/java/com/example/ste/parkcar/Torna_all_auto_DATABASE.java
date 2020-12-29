package com.example.ste.parkcar;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

/**
 * Created by ament on 27/08/2017.
 *
 * This class is meant to create a database to be used as memorizer of the
 * car's actual position.
 *
 * - DATABASE CREATION: this database will be created at the beginning
 *                      of the mainActivity class.
 *
 * - USAGE:             this database will be used to save the actual position
 *                      of the car. This information will be stored permanently
 *                      until the user won't decide to delete it.
 */

public class Torna_all_auto_DATABASE extends SQLiteOpenHelper {

    Context context;
    private String database_name = "Torna_all_auto_DB.db";
    private String table_name = "POSITION_TABLE";
    private static String how_to_order = "id";                    // This variable is initialized to "data". It allows to order the
                                                                  // result of the query specifing how to order.

    public Torna_all_auto_DATABASE(Context context) {
        super(context, "Torna_all_auto_DB.db", null, 1);
        //   SQLiteDatabase database = this.getWritableDatabase();       // This line allows the program to view and access the database
                                                                          // HO COMMENTATO QUESTA LINEA DI CODICE PERCHE TANTO LA INSERICO
                                                                          // NEL METODO INSERT!
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POSITIONS_TABLE = "CREATE TABLE " + table_name + "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "posizione_memorizzata TEXT)";
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

    public void deleteAll () {
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete All
        db.execSQL("delete from "+ table_name);
        // 3. close
        db.close();
    }

    //-----------------------------------------

    /**
     * This method adds the parameter elements into a row of the table
     *
     * NO ID WILL BE ADDED, BECAUSE IT AUTOINCREMENTS ITSELF!
     * @param posizioni_memorizzate second column
     * @return false if .insert method from SQLiteOpenHelper inserts the value into the table and return -1, true otherwise.
     */
    public boolean insertData (String posizioni_memorizzate) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("posizione_memorizzata", posizioni_memorizzate);
        long result = database.insert(table_name,null,contentValues);
        if (result != 1) {
            return true; }
        else {
            return false; }
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
    public boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
    //-----------------------------------------
    public Cursor selectAutoActualPosition () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name ,null);
        return res;
    }
    //-----------------------------------------
    public void viewAllAutoPosition (Context c) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = selectAutoActualPosition();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found",c);
            return;
        }
        StringBuffer buffer = new StringBuffer();

            res.moveToLast();
            buffer.append("ID: "+ res.getString(0)+"\n");
            buffer.append("POS.: "+ res.getString(1)+"\n\n");


        showMessage("Data",buffer.toString(),c);
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
}
