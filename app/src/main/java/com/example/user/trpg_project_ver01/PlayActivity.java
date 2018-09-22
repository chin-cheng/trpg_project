package com.example.user.trpg_project_ver01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
        userUID=FirebaseAuth.getInstance().getCurrentUser().getUid();
        test=findViewById(R.id.test);
        test.setText(userUID);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("story");
        Log.d("TA", "Value is: "+myRef.getKey());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Poststory value = dataSnapshot.getValue(Poststory.class);


                Log.d("TAG", "Value is: " + value.plot1_1);
            }




            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AG", "Failed to read value.", error.toException());
            }
        });

    }

        public void end(View v){
            endcount++;

        }

}
