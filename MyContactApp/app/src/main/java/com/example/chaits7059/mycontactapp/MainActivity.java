package com.example.chaits7059.mycontactapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
    }

    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString());
        if (isInserted == true) {
            Log.d("MyContactt", "Data insertion successful");
            //Create toast message to user indicating data inserted correctly.
        }

        else {
            Log.d("MyContactt", "Data insertion NOT successful");
            //Create toast message to User indicating data inserted incorrectly.
        }

    }

}
