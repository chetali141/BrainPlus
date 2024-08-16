package com.example.brain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class faq extends AppCompatActivity {

    TextView tv_ans1,tv_ans2,tv_ans3,tv_ans4,tv_ans5,tv_ans6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        tv_ans1=findViewById(R.id.ans1);
        tv_ans2=findViewById(R.id.ans2);
        tv_ans3=findViewById(R.id.ans3);
        tv_ans4=findViewById(R.id.ans4);
        tv_ans5=findViewById(R.id.ans5);
        tv_ans6=findViewById(R.id.ans6);
    }

    public void showAnswer(View v){
        if(tv_ans1.getVisibility()==View.VISIBLE){
            tv_ans1.setVisibility(View.GONE);
        }else {
            tv_ans1.setVisibility(View.VISIBLE);
        }
    }

    public void showAnswer1(View v){
        if(tv_ans2.getVisibility()==View.VISIBLE){
            tv_ans2.setVisibility(View.GONE);
        }else {
            tv_ans2.setVisibility(View.VISIBLE);
        }
    }

    public void showAnswer2(View v){
        if(tv_ans3.getVisibility()==View.VISIBLE){
            tv_ans3.setVisibility(View.GONE);
        }else {
            tv_ans3.setVisibility(View.VISIBLE);
        }
    }

    public void showAnswer3(View v){
        if(tv_ans4.getVisibility()==View.VISIBLE){
            tv_ans4.setVisibility(View.GONE);
        }else {
            tv_ans4.setVisibility(View.VISIBLE);
        }
    }

    public void showAnswer4(View v){
        if(tv_ans5.getVisibility()==View.VISIBLE){
            tv_ans5.setVisibility(View.GONE);
        }else {
            tv_ans5.setVisibility(View.VISIBLE);
        }
    }

    public void showAnswer5(View v){
        if(tv_ans6.getVisibility()==View.VISIBLE){
            tv_ans6.setVisibility(View.GONE);
        }else {
            tv_ans6.setVisibility(View.VISIBLE);
        }
    }
}