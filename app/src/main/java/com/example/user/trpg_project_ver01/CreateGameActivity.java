package com.example.user.trpg_project_ver01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Thread.sleep;

public class CreateGameActivity extends BaseActivity {
   // FirebaseAuth auth;

    FirebaseAuth.AuthStateListener authListener;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference story = db.getReference("story");
    private String userUID;
    EditText create_storyname,create_storyintro;
    EditText create_style;
    String author="",style="",title="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("創造故事");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
         userUID= getUid();
       // auth= FirebaseAuth.getInstance();
        DatabaseReference userdata = FirebaseDatabase.getInstance().getReference("users/"+userUID+"");

        create_storyname=findViewById(R.id.create_storyname_edit);
        create_style=findViewById(R.id.create_style_edit);
        create_storyintro=findViewById(R.id.create_storyintro_edit);

       // Toast.makeText(CreateGameActivity.this, userUID, Toast.LENGTH_LONG).show();
       // Toast.makeText(CreateGameActivity.this, "auth"+auth, Toast.LENGTH_LONG).show();
    }
    public void nextpage(View v) throws InterruptedException {

        String key=story.push().getKey();

        story.child(key).child("key").setValue(key);
        story.child(key).child("author_uid").setValue(userUID);
        story.child(key).child("style").setValue(create_style.getText().toString());
        story.child(key).child("title").setValue(create_storyname.getText().toString());
        story.child(key).child("storyintro").setValue(create_storyintro.getText().toString());

        Toast.makeText(CreateGameActivity.this, key, Toast.LENGTH_LONG).show();


        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("key",key);

        intent.putExtras(bundle);
        intent.setClass(CreateGameActivity.this, ChoiceChapterActivity.class);
        startActivity(intent);
        finish();
/*
        myRef.child(create_storyname.getText().toString()).child("author").setValue(create_author.getText().toString());//寫入作者
*/
        /*new AlertDialog.Builder(this)
                .setTitle("對話視窗")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("新增成功")//設定顯示的文字
                .setPositiveButton("關閉視窗",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // finish();
                    }
                })//設定結束的子視窗
                .show();//呈現對話視窗*/


    }
    @Override
    protected void onStart() {
        super.onStart();
      //  FirebaseUser currentUser = auth.getCurrentUser();
        //updateUI(currentUser);
    }
}
