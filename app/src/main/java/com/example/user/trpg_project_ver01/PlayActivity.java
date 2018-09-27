package com.example.user.trpg_project_ver01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlayActivity extends AppCompatActivity {
    private String userUID;
    TextView test;
    Button choice1,choice2;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    private DatabaseReference mDatabase;
    int count=0;
    int endcount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // userUID =  user.getUid();
        userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        choice1=findViewById(R.id.choice1);
        choice2=findViewById(R.id.choice2);
        test = findViewById(R.id.test);
        test.setText(userUID);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("story/adventure/content/floor0/chapter0");
            Log.d("TA6", "key: " + myRef.getKey());


            myRef.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    Poststory value = dataSnapshot.getValue(Poststory.class);
                    choice1.setText(value.ans1);
                    choice2.setText(value.ans2);

                    test.append(value.story);
                    Log.d("T", "ans1" + value.ans1);
                    Log.d("TA", "ans2" + value.ans2);
                    Log.d("TAG", "br" + value.branch);
                    Log.d("TAgh", "ch" + value.chapter);
                    Log.d("TAGj", "stiry" + value.story);
                    Log.d("TAGk", "title" + value.title);


                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("AG", "Failed to read value.", error.toException());
                }
            });


        }


    int chapternum1=1;
    int chapternum2=1;
    int floor=1;

        public void end(View v){
            FirebaseDatabase database = FirebaseDatabase.getInstance();



            DatabaseReference myRef = database.getReference("story/adventure/content/floor"+chapternum1+"/chapter"+chapternum1+"-"+chapternum2);
            Log.d("TA6", "key: " + myRef.getKey());


            myRef.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    Poststory value = dataSnapshot.getValue(Poststory.class);
                    test.append(value.story);
                    choice1.setText(value.ans1);
                    choice2.setText(value.ans2);

                    //test.setText(userUID);
                   // test.append(value.ans1);
                    //test.append(value.ans2);

                    //test.append(value.title);


                    Log.d("T", "ans1" + value.ans1);
                    Log.d("TA", "ans2" + value.ans2);
                    Log.d("TAG", "br" + value.branch);
                    Log.d("TAgh", "ch" + value.chapter);
                    Log.d("TAGj", "stiry" + value.story);
                    Log.d("TAGk", "title" + value.title);


                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("AG", "Failed to read value.", error.toException());
                }
            });
            if(v.getId()==R.id.choice1)
            {
                chapternum1++;
            chapternum2=1;
            }else{
                chapternum1++;
                chapternum2=2;
            }

        }

}
