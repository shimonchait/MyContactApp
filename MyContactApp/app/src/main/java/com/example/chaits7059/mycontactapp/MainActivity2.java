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
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editSearch1;
    EditText editSearch2;
    EditText editSearch3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        Intent intent = getIntent();

        myDb = new DatabaseHelper(this);

        editSearch1 = (EditText) findViewById(R.id.editText3);
        editSearch2 = (EditText) findViewById(R.id.editText4);
        editSearch3 = (EditText) findViewById(R.id.editText5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchData(View view){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {

            Toast.makeText(MainActivity2.this, "ERROR: No Data found in database",
                    Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){

            if(editSearch1.getText().toString().toLowerCase().equals(res.getString(1).toLowerCase()) && editSearch1.getText().toString().length()>0){
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
                buffer.append("\n\n");
            }

            if(editSearch2.getText().toString().equals(res.getString(2)) && editSearch2.getText().toString().length()>0 && editSearch1.getText().toString().length() == 0){
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
                buffer.append("\n\n");
            }

            if(editSearch3.getText().toString().toLowerCase().equals(res.getString(3).toLowerCase()) && editSearch3.getText().toString().length()>0 && editSearch1.getText().toString().length() == 0 && editSearch2.getText().toString().length() == 0){
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
                buffer.append("\n\n");
            }

        }
        if(buffer.length() == 0){
            buffer.append("No Contact Found");
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

    public void end(View v){
        finish();
    }
}
