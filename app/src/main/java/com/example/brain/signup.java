package com.example.brain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class signup extends AppCompatActivity {

    EditText etmail,etuser,etpwd,etpwd1;
    RadioGroup rg;
    RadioButton rbm,rbf,rbo;
    Button b1,b2;
    TextView tv,tvgender;
    FirebaseAuth fAuth;
    ProgressBar pb;
    FirebaseFirestore fStore;

    DatePickerDialog picker;
    String userID,gender,text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etmail=findViewById(R.id.user_id);
        etuser=findViewById(R.id.user_name);
        etpwd=findViewById(R.id.user_pwd);
        etpwd1=findViewById(R.id.user_pwd1);
        b2=findViewById(R.id.btn_dob);
        b1=findViewById(R.id.signup_button);
        pb=findViewById(R.id.progressBar);
        tv=findViewById(R.id.display_dob);
        rg=findViewById(R.id.rg);
        rbm=findViewById(R.id.rbMale);
        rbf=findViewById(R.id.rbFemale);
        rbo=findViewById(R.id.rbOther);
        tvgender=findViewById(R.id.gender);

        fStore=FirebaseFirestore.getInstance();
        fAuth=FirebaseAuth.getInstance();

        //getting date of birth using calender- date picker

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(signup.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        text=dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        tv.setText(text);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbMale:
                        gender="Male";
                        break;
                    case R.id.rbFemale:
                        gender="Female";
                        break;
                    case R.id.rbOther:
                        gender="Other";
                    default:
                        tvgender.setError("Gender is Required");
                        return;
                }
            }
        });

        // on click of button fetching the details entered by user

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail=etmail.getText().toString().trim();
                final String uname=etuser.getText().toString().trim();
                String pwd=etpwd.getText().toString().trim();
                String pwd1=etpwd1.getText().toString().trim();

                //checking if mail is entered or not, if not then show error to user
                if(TextUtils.isEmpty(mail)){
                    etmail.setError("Email is Required");
                    return;
                }

                //checking if name is entered or not, if not then show error to user
                if(TextUtils.isEmpty(uname)){
                    etuser.setError("Name is Required");
                    return;
                }

                //checking if password is entered or not, if not then show error to user
                if(TextUtils.isEmpty(pwd)){
                    etpwd.setError("Password is Required");
                    return;
                }

                // checking that length of password is greater than 6 or not
                if(pwd.length()<6){
                    etpwd.setError("Password should contain 6 or more characters.");
                    return;
                }

                //checking if password is re-entered or not, if not then show error to user
                if(TextUtils.isEmpty(pwd1)){
                    etpwd1.setError("Required");
                    return;
                }

                // checking if both password matches or not
                if(!pwd.equals(pwd1)){
                    etpwd1.setError("Password does not match");
                    return;
                }

                //progress bar
                pb.setVisibility(View.VISIBLE);

                // authenticating user and storing his information into firestore
                fAuth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signup.this,"Registration succesful",Toast.LENGTH_SHORT).show();
                            userID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fStore.collection("user").document(userID);
                            Map<String,Object> user=new HashMap<>();
                            user.put("e_mail",mail);
                            user.put("u_name",uname);
                            user.put("gender",gender);
                            user.put("u_dob",text);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG","onSuccess: User profile is created for "+userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG","onFailure: "+e.toString());
                                }
                            });
                            startActivity(new Intent(signup.this,quizmain.class));
                            finish();
                        }else{
                            Toast.makeText(signup.this,"Error occured: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }
}
