package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText jelszoEditText, felhEditText;
    private Button loginBtn, regBtn;

    public static MyDbHelper helper;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        regBtn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(myIntent);

            }
        });

        loginBtn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    Cursor felh = helper.select(felhEditText.getText().toString(), MainActivity.db, MainActivity.this);
                    if(felh.getCount() == 0){
                        Toast.makeText(MainActivity.this, "Sikertelen belépés!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(felhEditText.getText().equals("") || TextUtils.isEmpty(felhEditText.getText())){
                        Toast.makeText(MainActivity.this, "Nem adott meg felhasználónevet!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(jelszoEditText.getText().equals("") || TextUtils.isEmpty(jelszoEditText.getText())){
                        Toast.makeText(MainActivity.this, "Nem adott meg jelszót!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    felh.moveToFirst();
                    if(!felh.getString(3).equals(jelszoEditText.getText().toString())){
                        Toast.makeText(MainActivity.this, "Sikertelen belépés!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(MainActivity.this,"Sikeres bejelentkezés!", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, LoggedInActivity.class);
                    myIntent.putExtra("fullname",felh.getString(4).toString());
                    MainActivity.this.startActivity(myIntent);
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init(){
        this.jelszoEditText = findViewById(R.id.jelszoEditText);
        this.felhEditText   = findViewById(R.id.felhEditText);
        this.loginBtn       = findViewById(R.id.loginBtn);
        this.regBtn         = findViewById(R.id.regBtn);
        this.db             = openOrCreateDatabase("database.db",MODE_PRIVATE, null);
        this.helper         = new MyDbHelper(MainActivity.this);
        helper.onCreate(this.db);
    }
}
