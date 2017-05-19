package com.example.chaits7059.mycontactapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editAddress;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editAddress = (EditText) findViewById(R.id.editText_address);
    }

    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editAddress.getText().toString());
        if (isInserted == true) {
            Toast.makeText(MainActivity.this, "SUCCESS: Your Data Has Been Inserted",
                    Toast.LENGTH_LONG).show();
        }

        else {
            Toast.makeText(MainActivity.this, "FAILURE: Your Data Has Not Been Inserted",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void viewData(View v){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            Toast.makeText(MainActivity.this, "ERROR: No Data found in database",
                    Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            for(int i = 0; i<4; i++) {
                if(i == 0)
                    buffer.append("CONTACT ");
                if(i == 1)
                    buffer.append("NAME: ");
                if(i == 2)
                    buffer.append("AGE: ");
                if(i == 3)
                    buffer.append("ADDRESS: ");
                buffer.append(res.getString(i));
                buffer.append("\n");
            }
            buffer.append("\n");
        }
        showMessage("Contact List", buffer.toString());

    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);  //cancel using back button
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void switch1(View view){
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }

}
