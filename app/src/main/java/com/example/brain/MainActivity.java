package com.example.brain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Animation anim;
    Button b1,b2;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.signup_button);
        b2=findViewById(R.id.login_button);

        fAuth=FirebaseAuth.getInstance();

        /* checking if user is already signed-in or not:
            if signed in show welcome message and take user to the quizmain page
            else show the buttons for sign-up and log-in */

        if(fAuth.getCurrentUser()!= null){
            // condition true means user is signed-in, therefore display welcome message and go to next page
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,"Welcome!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,quizmain.class));
                    finish();
                }
            },5000);
        }else{
            // condition false means user is not signed-in, therefore, show sign-up or log-in buttons
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
                    b1.startAnimation(anim);
                    b2.startAnimation(anim);
                }
            },2000);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,signup.class);
                startActivity(i);
                finish();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
