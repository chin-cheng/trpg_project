package com.example.user.trpg_project_ver01;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateGameActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference("story");
    private String userUID;
    EditText create_storyname;
    EditText create_author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
         userUID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        create_storyname=findViewById(R.id.create_storyname_edit);
        create_author=findViewById(R.id.create_author_edit);

    }
    public void nextpage(View v){

        myRef.child(create_storyname.getText().toString()).child("author").setValue(create_author.getText().toString());
        new AlertDialog.Builder(this)
                .setTitle("對話視窗")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("新增成功")//設定顯示的文字
                .setPositiveButton("關閉視窗",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })//設定結束的子視窗
                .show();//呈現對話視窗
    }
}
