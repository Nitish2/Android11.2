package com.example.hp.autocomplete_textview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    // Declaring variables
    AutoCompleteTextView autoComplete_view ;
    private Database database ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        // Creating object and initializing value by ID
        autoComplete_view = (AutoCompleteTextView) findViewById(R.id.autoComplete_view);
        database = new Database(MainActivity.this);

        database.openDb(); //Opening the Database
         String[] product = database.itemSearch(); // Calling method


        for (int i = 0; i < product.length; i++) ; // For loop
        {

        }
        // Creating ArrayAdapter to returns a view for each object in a collection of data objects

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.select_dialog_item, product);

        // Changes the list of data used for auto completion.
        autoComplete_view.setAdapter(arrayAdapter);

        /*
        Specifies the minimum number of characters the user has to type in the edit box before the
           drop down list is shown.
         */
        autoComplete_view.setThreshold(1);
        //Sets the listener that will be notified when the user clicks an item in the drop down list.
        autoComplete_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1 , int arg2, long arg3) {
                arg0.getItemAtPosition(arg2);
            }

        });
    }

    // onDestroy() method is just a method that is called based on a certain state.
    public void onDestroy() { //Creating o Method
        super.onDestroy(); // Ir will perform uninitialization and free resource
        database.closeDb(); // Closing the database

    }
    }
