package com.example.yenpham.ubs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class validation extends AppCompatActivity {
    public EditText verificationcode;
    public Button submit;
    public Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);
        cancelation ();
    }

    public void cancelation (){
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent move= new Intent(validation.this, firstTimeLogin.class);
                startActivity(move);
            }
        });
    }
}
