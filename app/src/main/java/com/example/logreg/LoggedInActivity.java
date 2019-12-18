package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedInActivity extends AppCompatActivity {

    private Button kijelentkezesBtn;
    private TextView loggedinFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();
        loggedinFullName.setText(getIntent().getExtras().getString("fullname"));
        kijelentkezesBtn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoggedInActivity.this, MainActivity.class);
                LoggedInActivity.this.startActivity(myIntent);

            }
        });
    }

    private void init(){
        this.kijelentkezesBtn = findViewById(R.id.kijelentkezesBtn);
        this.loggedinFullName = findViewById(R.id.loggedinFullName);
    }
}
