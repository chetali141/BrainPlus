package com.example.brain;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class result extends AppCompatActivity {

    TextView tv_bolly1,tv_bolly2,tv_comp1,tv_comp2,tv_cric1,tv_cric2,tv_gk1,tv_gk2,tv_india1,tv_india2,tv;
    TableRow tRow1,tRow2,tRow3,tRow4,tRow5,tr;
    TableLayout tableLayout;
    ImageView iv;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId,topic1,topic2,topic3,topic4,topic5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tRow1=findViewById(R.id.tr1);
        tv_bolly1=findViewById(R.id.tView1);
        tv_bolly2=findViewById(R.id.tView2);

        tRow2=findViewById(R.id.tr2);
        tv_comp1=findViewById(R.id.tView3);
        tv_comp2=findViewById(R.id.tView4);

        tRow3=findViewById(R.id.tr3);
        tv_cric1=findViewById(R.id.tView5);
        tv_cric2=findViewById(R.id.tView6);

        tRow4=findViewById(R.id.tr4);
        tv_gk1=findViewById(R.id.tView7);
        tv_gk2=findViewById(R.id.tView8);

        tRow5=findViewById(R.id.tr5);
        tv_india1=findViewById(R.id.tView9);
        tv_india2=findViewById(R.id.tView10);

        tv=findViewById(R.id.no_rslt);
        tableLayout=findViewById(R.id.tl1);
        tr=findViewById(R.id.tr);
        iv=findViewById(R.id.no_rslt_img);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        userId=fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference1=fStore.collection("Result_bollywood").document(userId);
        documentReference1.addSnapshotListener(result.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                topic1=documentSnapshot.getString("topic");
                String marks=documentSnapshot.getString("marks");
                if(topic1!=null){
                    tv.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.INVISIBLE);
                    tr.setVisibility(View.VISIBLE);
                    tRow1.setVisibility(View.VISIBLE);
                    tv_bolly1.setText(topic1);
                    tv_bolly2.setText(marks);
                }
            }
        });

        final DocumentReference documentReference2=fStore.collection("Result_Computer").document(userId);
        documentReference2.addSnapshotListener(result.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                topic2=documentSnapshot.getString("topic");
                String marks=documentSnapshot.getString("marks");
                if(topic2!=null){
                    tv.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.INVISIBLE);
                    tr.setVisibility(View.VISIBLE);
                    tRow2.setVisibility(View.VISIBLE);
                    tv_comp1.setText(topic2);
                    tv_comp2.setText(marks);
                }
            }
        });

        final DocumentReference documentReference3=fStore.collection("Result_cricket").document(userId);
        documentReference3.addSnapshotListener(result.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                topic3=documentSnapshot.getString("topic");
                String marks=documentSnapshot.getString("marks");
                if(topic3!=null){
                    tv.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.INVISIBLE);
                    tr.setVisibility(View.VISIBLE);
                    tRow3.setVisibility(View.VISIBLE);
                    tv_cric1.setText(topic3);
                    tv_cric2.setText(marks);
                }
            }
        });

        final DocumentReference documentReference4=fStore.collection("Result_gk").document(userId);
        documentReference4.addSnapshotListener(result.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                topic4=documentSnapshot.getString("topic");
                String marks=documentSnapshot.getString("marks");
                if(topic4!=null){
                    tv.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.INVISIBLE);
                    tr.setVisibility(View.VISIBLE);
                    tRow4.setVisibility(View.VISIBLE);
                    tv_gk1.setText(topic4);
                    tv_gk2.setText(marks);
                }
            }
        });

        final DocumentReference documentReference5=fStore.collection("Result_India").document(userId);
        documentReference5.addSnapshotListener(result.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                topic5=documentSnapshot.getString("topic");
                String marks=documentSnapshot.getString("marks");
                if(topic5!=null){
                    tv.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.INVISIBLE);
                    tr.setVisibility(View.VISIBLE);
                    tRow5.setVisibility(View.VISIBLE);
                    tv_india1.setText(topic5);
                    tv_india2.setText(marks);
                }
            }
        });

        if(topic1 == null && topic2 == null && topic3 == null && topic4 == null && topic5 == null){
            tv.setVisibility(View.VISIBLE);
            iv.setVisibility(View.VISIBLE);
        }
    }
}
