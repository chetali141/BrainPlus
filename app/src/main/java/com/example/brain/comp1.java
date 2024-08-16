package com.example.brain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class comp1 extends AppCompatActivity {

    Button b1,b2,b3,b4,btn;
    TextView tv,tv_time,tv_no,tv_correct,tv_total,tv_score,tv_msg;
    int sec=1,marks=0,no=1;
    ImageView iv;
    String msg,userID,rightAnswer;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    LinearLayout l,l1;
    MediaPlayer mediaPlayer;
    Animation anim;
    static final int QUES_COUNT=10;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][]= {
            // order: ques,answer,choice1,choice2,choice3
            {"Which day is celebrated as Computer Literary Day?", "December 2", "January 10", "May 22", "August 15"},
            {"Longhorn was the code name of?", "Windows Vista", "Windows 7", "Windows 10", "Windows XP"},
            {"Who is known as the Human Computer of India?", "Shakunthala Devi", "Srinivasa Ramanujan", "Ramjit Raghav", "Aryabhata"},
            {"World's first Mircoprocessor is?", "Intel 4004", "TMX 1795", "TMS 1802NC", "RCA 1802"},
            {"Which IT company's nickname is 'The Big Blue'?", "IBM", "Microsoft", "Google", "Facebook"},
            {"The first mechanical computer designed by Charles Babbage was called?", "Analytical Engine", "Abacus", "Calculator", "PARAM"},
            {"Find the odd one out.", "Java", "Google", "Altavista", "Lycos"},
            {"Whose trademark is the operating system UNIX?", "BELL Laboratories", "Motorola", "Ashton Tata", "Microsoft"},
            {"Who is considered to be the Father of Artificial Intelligence ?", "John McCarthy", "Alan Turing", "George Boole", "Allen Newell"},
            {"Speed of second generation computer was in ____ seconds ?", "micro", "nano", "pico", "milli"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp1);

        tv=findViewById(R.id.ques);
        tv_time=findViewById(R.id.counter);
        tv_no=findViewById(R.id.no);
        b1=findViewById(R.id.option1);
        b2=findViewById(R.id.option2);
        b3=findViewById(R.id.option3);
        b4=findViewById(R.id.option4);
        l=findViewById(R.id.l4);
        l1=findViewById(R.id.l5);
        tv_correct=findViewById(R.id.dispCorrect);
        tv_total=findViewById(R.id.dispTotal);
        tv_score=findViewById(R.id.dispMarks);
        tv_msg=findViewById(R.id.dispMsg);
        btn=findViewById(R.id.btn_menu);
        iv=findViewById(R.id.rslt_img);

        fStore=FirebaseFirestore.getInstance();
        fAuth=FirebaseAuth.getInstance();

        for (int i=0;i<quizData.length;i++){
            ArrayList<String> tempArray= new ArrayList<>();
            tempArray.add(quizData[i][0]);
            tempArray.add(quizData[i][1]);
            tempArray.add(quizData[i][2]);
            tempArray.add(quizData[i][3]);
            tempArray.add(quizData[i][4]);

            quizArray.add(tempArray);
        }

        mediaPlayer=MediaPlayer.create(comp1.this,R.raw.sound);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        showNextQues();

    }

    public void showNextQues(){
        tv_no.setText(no+"/10");
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b1.setTextColor(Color.BLACK);
        b2.setTextColor(Color.BLACK);
        b3.setTextColor(Color.BLACK);
        b4.setTextColor(Color.BLACK);
        b1.setBackgroundColor(Color.WHITE);
        b2.setBackgroundColor(Color.WHITE);
        b3.setBackgroundColor(Color.WHITE);
        b4.setBackgroundColor(Color.WHITE);

        Random random=new Random();
        int randomNum=random.nextInt(quizArray.size());
        ArrayList<String> quiz=quizArray.get(randomNum);

        tv.setText(quiz.get(0));

        rightAnswer=quiz.get(1);

        quiz.remove(0);
        Collections.shuffle(quiz);
        b1.setText(quiz.get(0));
        b2.setText(quiz.get(1));
        b3.setText(quiz.get(2));
        b4.setText(quiz.get(3));

        quizArray.remove(randomNum);

        new CountDownTimer(15000, 1000){
            public void onTick(long millisUntilFinished){
                tv_time.setText("Time: "+String.valueOf(sec));
                sec++;

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String txt=b1.getText().toString();
                        if(txt.equals(rightAnswer)) {
                            b1.setTextColor(Color.WHITE);
                            b1.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                            marks++;
                            b2.setEnabled(false);
                            b3.setEnabled(false);
                            b4.setEnabled(false);
                        }else{
                            b1.setTextColor(Color.WHITE);
                            b1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                            b2.setEnabled(false);
                            b3.setEnabled(false);
                            b4.setEnabled(false);
                        }
                        sec=0;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cancel();
                                if(no==QUES_COUNT){
                                    displayResult();
                                }else{
                                    no++;
                                    showNextQues();
                                }
                            }
                        },1000);
                    }
                });

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String txt=b2.getText().toString();
                        if(txt.equals(rightAnswer)) {
                            b2.setTextColor(Color.WHITE);
                            b2.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                            marks++;
                            b1.setEnabled(false);
                            b3.setEnabled(false);
                            b4.setEnabled(false);
                        }else{
                            b2.setTextColor(Color.WHITE);
                            b2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                            b1.setEnabled(false);
                            b3.setEnabled(false);
                            b4.setEnabled(false);
                        }
                        sec=0;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cancel();
                                if(no==QUES_COUNT){
                                    displayResult();
                                }else{
                                    no++;
                                    showNextQues();
                                }
                            }
                        },1000);
                    }
                });

                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String txt=b3.getText().toString();
                        if(txt.equals(rightAnswer)) {
                            b3.setTextColor(Color.WHITE);
                            b3.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                            marks++;
                            b2.setEnabled(false);
                            b1.setEnabled(false);
                            b4.setEnabled(false);
                        }else{
                            b3.setTextColor(Color.WHITE);
                            b3.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                            b2.setEnabled(false);
                            b1.setEnabled(false);
                            b4.setEnabled(false);
                        }
                        sec=0;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cancel();
                                if(no==QUES_COUNT){
                                    displayResult();
                                }else{
                                    no++;
                                    showNextQues();
                                }
                            }
                        },1000);
                    }
                });

                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String txt=b4.getText().toString();
                        if(txt.equals(rightAnswer)) {
                            b4.setTextColor(Color.WHITE);
                            b4.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                            marks++;
                            b2.setEnabled(false);
                            b3.setEnabled(false);
                            b1.setEnabled(false);
                        }else{
                            b4.setTextColor(Color.WHITE);
                            b4.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                            b2.setEnabled(false);
                            b3.setEnabled(false);
                            b1.setEnabled(false);
                        }
                        sec=0;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cancel();
                                if(no==QUES_COUNT){
                                    displayResult();
                                }else{
                                    no++;
                                    showNextQues();
                                }
                            }
                        },1000);
                    }
                });

            }
            public  void onFinish(){
                sec=0;
                if(no==QUES_COUNT){
                    displayResult();
                }else{
                    no++;
                    showNextQues();
                }
            }
        }.start();
    }

    void displayResult(){
        b1.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
        b3.setVisibility(View.GONE);
        b4.setVisibility(View.GONE);
        tv_time.setVisibility(View.GONE);
        tv_no.setVisibility(View.GONE);
        l.setVisibility(View.GONE);
        tv.setVisibility(View.GONE);
        l1.setVisibility(View.VISIBLE);
        tv_correct.setText("You answered "+marks+" questions correctly.");
        tv_total.setText("Total questions were 10.");
        tv_score.setText("Your score is: "+marks);
        if (marks > 7){
            msg="Excellent";
            iv.setImageResource(R.drawable.balloons);
            iv.setVisibility(View.VISIBLE);
            anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.image_translate);
            iv.startAnimation(anim);
            iv.setVisibility(View.GONE);
        }else if (marks > 3){
            msg="You can do Better";
            iv.setImageResource(R.drawable.thumb);
            iv.setVisibility(View.VISIBLE);
            anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.image_translate);
            iv.startAnimation(anim);
            iv.setVisibility(View.GONE);
        }else if (marks < 4){
            msg="Oops .... Poor Performance";
            iv.setImageResource(R.drawable.sad);
            iv.setVisibility(View.VISIBLE);
            anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.image_translate);
            iv.startAnimation(anim);
            iv.setVisibility(View.GONE);
        }
        tv_msg.setText(msg);

        final String mark= Integer.toString(marks);

        userID=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fStore.collection("Result_Computer").document(userID);
        Map<String,Object> user=new HashMap<>();
        user.put("topic","Computer Quiz");
        user.put("marks",mark);
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("TAG","onSuccess: Result stored "+userID);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG","onFailure: "+e.toString());
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(comp1.this,quizmain.class));
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}