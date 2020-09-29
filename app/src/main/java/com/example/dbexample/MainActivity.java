package com.example.dbexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button Insert,View;
    SQLiteDatabase db;
    int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = (EditText) findViewById(R.id.editText);
        Insert = (Button) findViewById(R.id.btnSave);
        View = (Button) findViewById(R.id.btnView);


        db = openOrCreateDatabase("Test", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Test(id INTEGER  ,datas VARCHAR);");

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if (editText.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                x++;
                db.execSQL("INSERT INTO test VALUES('"+x+"','" +editText.getText() + "');");

                Toast.makeText(getApplicationContext(),"Record Added",Toast.LENGTH_SHORT);
                clearText();
            }
        });

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                Intent intent=new Intent(MainActivity.this, RecyclerActivity.class);
                startActivity(intent);

            }

        } );
    }
    public void clearText() {

        editText.setText("");
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}