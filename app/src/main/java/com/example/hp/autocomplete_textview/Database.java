package com.example.hp.autocomplete_textview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//SQLiteOpenHelper create a helper object to create, open, and/or manage a database.
public class Database extends SQLiteOpenHelper{ //Creating a class and extends from SQLiteOpenHelper

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

  // Declaring database variables
    private static final int DATABASE_VERSION = 6; // Initializing Database_Version

    private static final String DATABASE_NAME = "Products"; // Initializing Database_Name
    // products table name
    private static final String DATABASE_TABLE = "SearchProduct";  // Initializing Database_Table
    //products Coloumn Name
    private static final String DATABASE_COLUMN_NAME = "item_name";  // Initializing Database_Column

    private static final String CREATE_DATABASE = (" CREATE TABLE " + DATABASE_TABLE +
            " (item_id INTEGER PRIMARY KEY AUTOINCREMENT," + "item_name TEXT NOT NULL)");
    private SQLiteDatabase database = null; // Creating object

    // onCreate is called when the database is created for the first time
    public void onCreate(SQLiteDatabase database) { // Creating method
        database.execSQL(CREATE_DATABASE); // Creating database
        this.database=database;
        //Adding items into the database
        addProduct("Sony TV HD");
        addProduct("Samsung mobiles");
        addProduct("Sony mobiles ");
        addProduct("Philips trimmer ");
        addProduct("Panasonic charger");
        addProduct("Sansui TV");
        addProduct("Philips earphones");
        addProduct("PlayStation 4");

    }


    //onUpgrade method is called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS  "+DATABASE_TABLE);
        onCreate(database);

    }
    // openDB method is called when the database has been opened
    public void openDb() {
        if (this.database == null) {
            this.database = this.getWritableDatabase();
        }
    }
    //close() method is used to close any open database object.
    public void closeDb() {
        if (this.database != null) {
            if (this.database.isOpen())
                this.database.close();
        }

    }

    public long addProduct(String ItemName) { // Creating method to add products
        ContentValues contentValue = new ContentValues();
        contentValue.put(DATABASE_COLUMN_NAME, ItemName);

        return this.database.insert(DATABASE_TABLE, null, contentValue);
    }


    public String[] itemSearch() { // Creating method to search item from the table

        //Cursor exposes results from a query on a SQLiteDatabase.
        Cursor c = this.database.query(DATABASE_TABLE, new String[]
                {DATABASE_COLUMN_NAME}, null, null, null, null, null);

        if (c.getCount() > 0) { // If statement
            String[] string = new String[c.getCount()];
            int i = 0;

            while (c.moveToNext()) { // While loop
                string[i] = c.getString(c.getColumnIndex(DATABASE_COLUMN_NAME));

                i++;
            }
            return string; // Return statement
        } else {
            return new String[]{};

        }

    }
}
