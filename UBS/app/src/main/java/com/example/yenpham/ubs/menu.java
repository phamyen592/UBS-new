package com.example.yenpham.ubs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class menu extends AppCompatActivity {



    private GoogleApiClient mGoogleApiClient;


    protected Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent log =getIntent();


        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signOut();

            }
        });
    }


    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        SharePref sharePref;

                        sharePref= SharePref.getInstance();
                        SharedPreferences.Editor editor= sharePref.sharepreferences.edit();
                        if (sharePref.getISLogged_IN(menu.this)) {

                            Intent wel = new Intent(getApplicationContext(), welcom_class.class);
                            startActivity(wel);
                            editor.clear();

                            finish();
                        }else{

                            Intent intent =new Intent(menu.this, login_gg.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }

                    }
                });
    }

}
