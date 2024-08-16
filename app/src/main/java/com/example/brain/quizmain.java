package com.example.brain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class quizmain extends AppCompatActivity {

    Button bcomp,bbolly,bcricket,bgk,bindia,btn_start;
    Animation anim;
    RelativeLayout relativeLayout,rl1;
    TextView tv,tvback;

    StorageReference storageReference;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    CircleImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizmain);

        bcomp=findViewById(R.id.btn_comp);
        bbolly=findViewById(R.id.btn_bolly);
        bcricket=findViewById(R.id.btn_cricket);
        bgk=findViewById(R.id.btn_gk);
        bindia=findViewById(R.id.btn_india);
        relativeLayout=findViewById(R.id.l3);
        tv=findViewById(R.id.tv10);
        tvback=findViewById(R.id.tv_back);
        btn_start=findViewById(R.id.start);
        rl1=findViewById(R.id.rl1);

        storageReference= FirebaseStorage.getInstance().getReference();
        fAuth=FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();

        userId=fAuth.getCurrentUser().getUid();
        iv=findViewById(R.id.imageView2);

        StorageReference ref=storageReference.child(userId+"/"+"profilepic.jpg");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(iv);
            }
        });

        bcomp.setEnabled(true);
        bbolly.setEnabled(true);
        bcricket.setEnabled(true);
        bgk.setEnabled(true);
        bindia.setEnabled(true);

        bbolly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bcomp.setVisibility(View.GONE);
                bcricket.setVisibility(View.GONE);
                bgk.setVisibility(View.GONE);
                bindia.setVisibility(View.GONE);
                rl1.setBackground(getResources().getDrawable(R.drawable.whitebg));
                anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_bolly_up);
                bbolly.startAnimation(anim);

                bbolly.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.setVisibility(View.VISIBLE);
                        tvback.setVisibility(View.VISIBLE);
                        tv.setText(tv.getText()+" This quiz contains questions related to bollywood.");
                    }
                },0500);

                btn_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(quizmain.this,bolly1.class));
                    }
                });
            }
        });

        bcomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbolly.setVisibility(View.GONE);
                bcricket.setVisibility(View.GONE);
                bgk.setVisibility(View.GONE);
                bindia.setVisibility(View.GONE);
                rl1.setBackground(getResources().getDrawable(R.drawable.whitebg));
                anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_comp_up);
                bcomp.startAnimation(anim);

                bcomp.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.setVisibility(View.VISIBLE);
                        tvback.setVisibility(View.VISIBLE);
                        tv.setText(tv.getText()+" This quiz contains questions related to computer.");
                    }
                },0500);

                btn_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(quizmain.this,comp1.class));
                    }
                });
            }
        });

        bcricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbolly.setVisibility(View.GONE);
                bcomp.setVisibility(View.GONE);
                bgk.setVisibility(View.GONE);
                bindia.setVisibility(View.GONE);
                rl1.setBackground(getResources().getDrawable(R.drawable.whitebg));
                anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_cricket_up);
                bcricket.startAnimation(anim);

                bcricket.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.setVisibility(View.VISIBLE);
                        tvback.setVisibility(View.VISIBLE);
                        tv.setText(tv.getText()+" This quiz contains questions related to cricket.");
                    }
                },0500);

                btn_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(quizmain.this,cricket1.class));
                    }
                });
            }
        });

        bgk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbolly.setVisibility(View.GONE);
                bcricket.setVisibility(View.GONE);
                bcomp.setVisibility(View.GONE);
                bindia.setVisibility(View.GONE);
                rl1.setBackground(getResources().getDrawable(R.drawable.whitebg));
                anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_gk_up);
                bgk.startAnimation(anim);

                bgk.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.setVisibility(View.VISIBLE);
                        tvback.setVisibility(View.VISIBLE);
                        tv.setText(tv.getText()+" This quiz contains questions related to G.K.");
                    }
                },0500);

                btn_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(quizmain.this,gk1.class));
                    }
                });
            }
        });

        bindia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbolly.setVisibility(View.GONE);
                bcricket.setVisibility(View.GONE);
                bcomp.setVisibility(View.GONE);
                bgk.setVisibility(View.GONE);
                rl1.setBackground(getResources().getDrawable(R.drawable.whitebg));
                anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_india_up);
                bindia.startAnimation(anim);

                bindia.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.setVisibility(View.VISIBLE);
                        tvback.setVisibility(View.VISIBLE);
                        tv.setText(tv.getText()+" This quiz contains questions related to India.");
                    }
                },0500);

                btn_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(quizmain.this,india1.class));
                    }
                });
            }
        });
    }

    public void backToMenu(View v){
        startActivity(new Intent(quizmain.this,quizmain.class));
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        StorageReference ref=storageReference.child(userId+"/"+"profilepic.jpg");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(iv);
            }
        });
    }

    public void generalInfo(View v){
        startActivity(new Intent(quizmain.this,faq.class));
    }

    public void continueNext(View v){
        startActivity(new Intent(quizmain.this,profile.class));
    }
}
