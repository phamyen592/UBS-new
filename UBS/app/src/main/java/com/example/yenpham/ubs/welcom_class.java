package com.example.yenpham.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Yenpham on 11/9/16.
 */

public class welcom_class extends AppCompatActivity {



    public Button exist;
    public Button newUser;

    public void login(){
        exist = (Button)findViewById(R.id.existing);
        exist.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent gglog= new Intent(welcom_class.this, login_gg.class);
                startActivity(gglog);
            }

        });

    }
    public void registration (){
        newUser = (Button)findViewById(R.id.newUser);
        newUser.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent reg= new Intent(welcom_class.this, firstTimeLogin.class);
                startActivity(reg);
            }

        });
    }

    @Override
    protected void onCreate (Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        login();
        registration ();
    }
}
