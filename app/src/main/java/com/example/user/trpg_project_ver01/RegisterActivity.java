package com.example.user.trpg_project_ver01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    EditText regedit_mail,regedit_pawd,regedit_pawd_ver;
    Button reg_send;
    int countt;
    private String userUID;
    private String valid_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("註冊會員");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regedit_mail=findViewById(R.id.regedit_mail);
        regedit_pawd=findViewById(R.id.regedit_pawd);
        regedit_pawd_ver=findViewById(R.id.regedit_pawd_ver);
    }
    public void onclickreg(View v){
        String email=regedit_mail.getText().toString();
        String password=regedit_pawd.getText().toString();
        String passwordagain=regedit_pawd_ver.getText().toString();
        String pattern = "^.*@gmail\\.com$";
        countt = password.length();
        if(email.matches(pattern)){
            if(countt > 5){
                if(passwordagain.equals(password)){
                    createUser(email,password);
                }
                else{
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("驗證密碼錯誤")
                            .setIcon(R.drawable.trpglogin)
                            .setMessage("請檢查您的密碼是否輸入相同")
                            .setPositiveButton("確定", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int which){}})
                            .show();
                }
            }
            else{
                new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("密碼錯誤")
                        .setIcon(R.drawable.trpglogin)
                        .setMessage("請檢查您的密碼是否大於六碼")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which){}})
                        .show();
            }
        }else{
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("信箱格式錯誤")
                    .setIcon(R.drawable.trpglogin)
                    .setMessage("請檢查您的信箱是否輸入正確")
                    .setPositiveButton("確定", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which){
                        }})
                    .show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        //auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //auth.removeAuthStateListener(authListener);
        //Log.d("tse","stop");
    }

    private void createUser(final String email, final String password) {
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String message =
                                        task.isComplete() ? "註冊成功" : "註冊失敗";
                                new AlertDialog.Builder(RegisterActivity.this)
                                        .setMessage(message)
                                        .setPositiveButton("OK",  new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int id) {Intent intent=new Intent();intent.setClass(RegisterActivity.this, ProfileActivity.class);
                                                startActivity(intent);

                                            FirebaseDatabase db = FirebaseDatabase.getInstance();
                                            DatabaseReference usersRef = db.getReference("users");
                                            //usersRef.child(userUID).child(email);




                                            userUID=FirebaseAuth.getInstance().getCurrentUser().getUid();//或許未來登出再登入會有問題
                                            usersRef.child(userUID).child("email").setValue(email);
                                            usersRef.child(userUID).child("password").setValue(password);
                                            usersRef.child(userUID).child("useruid").setValue(userUID);
                                            usersRef.child(userUID).child("reg_time").setValue(ServerValue.TIMESTAMP);
                                            usersRef.child(userUID).child("nickname").setValue("");
                                            usersRef.child(userUID).child("realname").setValue("");
                                            usersRef.child(userUID).child("birthday").setValue("");
                                            usersRef.child(userUID).child("phone").setValue("");
                                            usersRef.child(userUID).child("sex").setValue("");

Log.d("uidd",userUID);
                                                finish();
                                                // User cancelled the dialog
                                            }
                                        })
                                        .show();
                            }
                        });
    }






}



