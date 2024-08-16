package com.example.brain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    TextView t1;
    EditText etmail,etpwd;
    Button b;
    ProgressBar pb;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etmail=findViewById(R.id.login_mail);
        etpwd=findViewById(R.id.login_pwd);
        t1=findViewById(R.id.tv4);
        pb=findViewById(R.id.progressBar2);
        b=findViewById(R.id.login_button);
        fAuth=FirebaseAuth.getInstance();

        // fetching values user entered
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=etmail.getText().toString().trim();
                String pwd=etpwd.getText().toString().trim();

                // checking if email is entered or not, if not then show error to user
                if(TextUtils.isEmpty(mail)){
                    etmail.setError("Email is Required");
                    return;
                }

                // checking if password is entered or not, if not then show error to user
                if(TextUtils.isEmpty(pwd)){
                    etpwd.setError("Password is Required");
                    return;
                }

                // checking length of password (is greater than 6 or not), if not then show error to user
                if(pwd.length()<6){
                    etpwd.setError("Password should contain 6 or more characters.");
                    return;
                }

                //progress bar
                pb.setVisibility(View.VISIBLE);

                // validating user information, if found then user is logged in, else error is shown
                fAuth.signInWithEmailAndPassword(mail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(login.this,"Welcome!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(login.this,quizmain.class));
                            finish();
                        }else {
                            Toast.makeText(login.this,"Error occured: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this,signup.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void forgotPwd(View v){
        final EditText reset=new EditText(v.getContext());
        AlertDialog.Builder pwdresetbuilder=new AlertDialog.Builder(v.getContext());
        pwdresetbuilder.setTitle("Forgot Password?");
        pwdresetbuilder.setMessage("Enter your E-mail to receive the password reset Link.");
        pwdresetbuilder.setView(reset);
        pwdresetbuilder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m=reset.getText().toString();
                fAuth.sendPasswordResetEmail(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(login.this,"Reset link sent to your Mail.",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this,"Mail not send: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        pwdresetbuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        pwdresetbuilder.create().show();
    }
}
