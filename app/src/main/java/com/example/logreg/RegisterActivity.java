package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditTextRegister, felhEditTextRegister, jelszoEditTextRegister, teljesNevEditTextRegister;
    private Button regBtnReg, backBtnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        if(regBtnReg.isClickable()){
            regBtnReg.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(emailEditTextRegister.getText())) Toast.makeText(RegisterActivity.this, "Nem adtál meg E-Mail-t!", Toast.LENGTH_SHORT).show();
                    if(TextUtils.isEmpty(felhEditTextRegister.getText())) Toast.makeText(RegisterActivity.this, "Nem adtál meg felhasználónevet!", Toast.LENGTH_SHORT).show();
                    if(TextUtils.isEmpty(jelszoEditTextRegister.getText())) Toast.makeText(RegisterActivity.this, "Nem adtál meg jelszót!", Toast.LENGTH_SHORT).show();
                    if(TextUtils.isEmpty(teljesNevEditTextRegister.getText())) Toast.makeText(RegisterActivity.this, "Nem adtad meg a teljes neved!", Toast.LENGTH_SHORT).show();
                    if(!emailEditTextRegister.getText().toString().matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")){
                        Toast.makeText(RegisterActivity.this, "Rossz E-Mail cím!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!(teljesNevEditTextRegister.getText().toString().split(" ").length>=2)){
                        Toast.makeText(RegisterActivity.this, "Rossz név!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    boolean isupper = true;
                    for(int i = 0; i < teljesNevEditTextRegister.getText().toString().split(" ").length; i++){
                        char[] c = teljesNevEditTextRegister.getText().toString().split(" ")[i].toCharArray();
                        if(Character.toUpperCase(c[0]) != c[0]){
                            isupper = false;
                        }
                    }
                    if(!isupper){
                        Toast.makeText(RegisterActivity.this, "Rossz név!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!TextUtils.isEmpty(emailEditTextRegister.getText()) &&
                       !TextUtils.isEmpty(felhEditTextRegister.getText())&&
                       !TextUtils.isEmpty(jelszoEditTextRegister.getText())&&
                       !TextUtils.isEmpty(teljesNevEditTextRegister.getText())){
                        MainActivity.helper.insert(emailEditTextRegister.getText(), felhEditTextRegister.getText(), jelszoEditTextRegister.getText(), teljesNevEditTextRegister.getText(), RegisterActivity.this, MainActivity.db);
                        Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
                        RegisterActivity.this.startActivity(myIntent);
                    }
                }
            });
        }


        backBtnReg.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
                RegisterActivity.this.startActivity(myIntent);
                finish();
            }
        });
    }

    private void init(){
        this.emailEditTextRegister      = findViewById(R.id.emailEditTextRegister);
        this.felhEditTextRegister       = findViewById(R.id.felhEditTextRegister);
        this.jelszoEditTextRegister     = findViewById(R.id.jelszoEditTextRegister);
        this.teljesNevEditTextRegister  = findViewById(R.id.teljesNevEditTextRegister);
        this.regBtnReg                  = findViewById(R.id.regBtnReg);
        this.backBtnReg                 = findViewById(R.id.backBtnReg);
    }
}
