package com.example.user.trpg_project_ver01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.trpg_project_ver01.models.Poststory;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowStoryListActivity extends BaseActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static final String TAG = "ShowStoryListActivity";
    ListView lv_showstory;
    String[] func = new String[999];
    String[] storyuid = new String[999];
    String[] storyname = new String[999];
    int count = 0;
    int lvlong=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story_list);
        lv_showstory = findViewById(R.id.lv_showstory);
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        lv_showstory.setAdapter(adapter);
       // adapter.add("測試一");
        final DatabaseReference dinosaursRef = db.getReference("story");
        dinosaursRef.orderByChild("height").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Poststory dinosaur = dataSnapshot.getValue(Poststory.class);
                //System.out.println(dataSnapshot.getKey() + " was " + dinosaur.title + " meters tall.");
                Log.w(TAG, "key:" + dataSnapshot.getKey() + "        title:" + dinosaur.title);
                adapter.add(dinosaur.title);
                storyuid[count] = dataSnapshot.getKey();
                storyname[count] = dinosaur.title;
                Log.w(TAG, "count:" + count);
                count++;

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            // ...
        });
        lvlong=lv_showstory.getCount();
        lv_showstory.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Log.d(TAG,"total listview long"+lv_showstory.getCount());
                Log.w("lv postition",position+"");
                Intent intent=new Intent();
                intent.setClass(ShowStoryListActivity.this,PlayVer2Activity.class);
                Bundle bundle=new Bundle();
                bundle.putString("storyuid",storyuid[position]);
                Log.w(TAG, "get story uid"+storyname[position]);



                intent.putExtras(bundle);
                startActivity(intent);
                // Toast.makeText(MemberActivity.this,"第"+(position+1)+"條",Toast.LENGTH_SHORT).show();
            }
        });


    }


}
