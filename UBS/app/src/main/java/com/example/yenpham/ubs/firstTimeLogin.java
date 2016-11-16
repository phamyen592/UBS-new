package com.example.yenpham.ubs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class firstTimeLogin extends AppCompatActivity {

    private EditText fName;
    private EditText lName;
    private EditText phone;
    private EditText UTAEmail;
    private EditText mavID;
    private EditText BOB;
    private EditText major;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_login);
        fName = (EditText)findViewById(R.id.fName);
        lName =(EditText)findViewById(R.id.lName);
        phone =(EditText)findViewById(R.id.phone);
        UTAEmail =(EditText)findViewById(R.id.utaEmail);
        mavID =(EditText)findViewById(R.id.mavId);
        BOB =(EditText)findViewById(R.id.dob);
        major =(EditText)findViewById(R.id.major);
        submit= (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.v("", fName.getText().toString());
                Log.v("", lName.getText().toString());
                Log.v("", phone.getText().toString());
                Log.v("", UTAEmail.getText().toString());
                Log.v("", mavID.getText().toString());
                Log.v("", BOB.getText().toString());
                Log.v("EditText", major.getText().toString());
                String email= UTAEmail.getText().toString();


                if (!isUTAEmail(email)){



                }


            }
        });
    }

    private boolean isUTAEmail( String email){
        boolean result = true;
        String temp[] = email.split("@");
        if
        return result;
    }

    @SuppressLint("LongLogTag")
    protected void sendEmail(int code,String email) {
        Log.i("Send email", "");
        String[] TO = {email};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "UBS Verification Code");
        String s = String.format("Hi,\n Your Verification Code is %d", code);
        emailIntent.putExtra(Intent.EXTRA_TEXT, s);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(firstTimeLogin.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
