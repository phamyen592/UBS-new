package com.example.yenpham.ubs;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;


import java.lang.String;
import java.util.Random;

import static android.R.attr.duration;


public class firstTimeLogin extends AppCompatActivity {

    private EditText fName;
    private EditText lName;
    private EditText phone;
    private EditText UTAEmail;
    private EditText mavID;
    private EditText BOB;
    private EditText major;
    private Button submit;
    private Dialog check;
    private String utemail;
    private int code;
    private Button cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_login);
        cancelRegistration ();
        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        phone = (EditText) findViewById(R.id.phone);
        UTAEmail = (EditText) findViewById(R.id.utaEmail);
        mavID = (EditText) findViewById(R.id.mavId);
        BOB = (EditText) findViewById(R.id.dob);
        major = (EditText) findViewById(R.id.major);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = true;
                String fN = fName.getText().toString();
                if (fN.length() == 0) {
                    fName.setError("Missing Last Name");
                    success = false;
                }

                String lN = lName.getText().toString();
                if (lN.length() == 0) {
                    lName.setError("Missing Last Name");
                    success = false;

                }

                String phoneNum = phone.getText().toString();
                int phoneLeght = phoneNum.length();
                if (phoneLeght == 0) {
                    phone.setError("Missing Phone Number");
                    success = false;

                } else if (check_phone(phoneNum, phoneLeght)) {

                    phone.setError("Invalid Phone Number");
                    success = false;

                }
                 utemail = UTAEmail.getText().toString();
                if (utemail.length() == 0) {
                    UTAEmail.setError("Missing UTA email");
                    success = false;
                }
                 else if (!isUTAEmail(utemail)) {
                    UTAEmail.setError("Invalid UTA email");
                    success = false;

              }
                String mav = mavID.getText().toString();
                if (mav.length() == 0) {
                    mavID.setError("missing mavID");
                    success = false;


                } else if (check_MavID(mav, mav.length())) {
                    mavID.setError("Invalid mavID");
                    success = false;

                }
                String dob = BOB.getText().toString();
                if (dob.length() == 0) {
                    BOB.setError("Missing DOB");
                    success = false;

                }
                String maj = major.getText().toString();

                if (success) {
                     code = generatecode();
                    String message = "Check your UTA Email for verification code";
                    Context context = getApplicationContext();

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.show();
                    new AsyncTask<Object, Object, Void>() {
                        @Override
                        public Void doInBackground(Object... arg) {

                            try {

                                String content = String.format("Your verification code is %d", code);
                                GmailSender sender = new GmailSender("ubs.team9@gmail.com", "abcdefghik");
                                sender.sendMail("Verification Code",
                                        content, "ubs.team9@gmail.com", utemail);

                                Intent vad = new Intent(firstTimeLogin.this, validation.class);
                                vad.putExtra("Ver_Code", code);
                                startActivity(vad);
                            } catch (Exception e) {
                                Log.e("SendMail", e.getMessage(), e);
                            }

                            return null;
                        }
                    }.execute();


                }


            }
        });
    }

    private int generatecode() {
        Random r = new Random();
        int i = r.nextInt(99999 - 10000) + 10000;
        return i;
    }

    private boolean check_phone(String str, int leght) {
        int i = 0;
        boolean result = true;
        int count = 0;
        for (i = 0; i < leght; i++) {
            if (str.substring(i, i + 1).equals(" ")) {
                continue;
            } else if (count == 0 && str.charAt(i) == '+') {
                count++;
            } else if (str.substring(i, i + 1).indexOf("1234567890") != -1 && count < 13) {
                count++;
            } else {
                result = false;
                break;
            }

        }
        if (count < 9) {
            result = false;
        }
        return result;
    }

    private boolean check_MavID(String str, int leght) {
        int i = 0;
        boolean result = true;
        int count = 0;
        for (i = 0; i < leght; i++) {
            if (str.substring(i, i + 1).equals(" ")) {
                continue;
            } else if (str.substring(i, i + 1).indexOf("1234567890") != -1 && count < 10) {
                count++;
            } else {
                result = false;
                break;
            }

        }
        if (count < 9) {
            result = false;
        }
        return result;
    }

    private boolean isUTAEmail(String email) {
        boolean result = false;

        String temp[] = email.split("@");
        if (temp.length >= 2) {


            if (temp[1] != null && temp[1].equals("mavs.uta.edu"))
                result = true;
        }
        return result;
    }

    public void cancelRegistration (){
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent cancelReg= new Intent(firstTimeLogin.this, welcom_class.class);
                startActivity(cancelReg);
            }

        });
    }

}

