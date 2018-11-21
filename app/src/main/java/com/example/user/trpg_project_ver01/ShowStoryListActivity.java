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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.trpg_project_ver01.models.Postchoose;
import com.example.user.trpg_project_ver01.models.Poststory;
import com.example.user.trpg_project_ver01.models.user;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowStoryListActivity extends BaseActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static final String TAG = "ShowStoryListActivity";
    String username = "";

    ListView lv_showstory;
    TextView storyintrotext, storynametext, authortext, styletext;
    String[] func = new String[999];
    String[] storyuid = new String[999];
    String[] storyname = new String[999];
    int count = 0;
    int lvlong = 0;
    //by kuo
    ArrayList title1 = new ArrayList();
    ArrayList style1 = new ArrayList();
    ArrayList author1 = new ArrayList();
    ArrayList storyintro1 = new ArrayList();
    SimpleAdapter adapter;
    ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

    //by kuo end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story_list);
        lv_showstory = findViewById(R.id.lv_showstory);
//by kuo
        storynametext = findViewById(R.id.storyname);
        storyintrotext = findViewById(R.id.storyintro);
        authortext = findViewById(R.id.author);
        storynametext = findViewById(R.id.storyname);
//by kuo end


        //final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        // lv_showstory.setAdapter(adapter);
        // adapter.add("測試一");
        final DatabaseReference dinosaursRef = db.getReference("story");
        dinosaursRef.orderByChild("height").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Poststory dinosaur = dataSnapshot.getValue(Poststory.class);
                //System.out.println(dataSnapshot.getKey() + " was " + dinosaur.title + " meters tall.");
                Log.w(TAG, "key:" + dataSnapshot.getKey() + "        title:" + dinosaur.title);
                // adapter.add(dinosaur.title);
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

        //by kuo

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("story");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                    Postchoose value = Snapshot.getValue(Postchoose.class);
                    username = "應該要是暱稱";
                    Log.w(TAG,"_username"+username+"\n uid"+value.author_uid+"41325");

//要把uid改成nickname的bug未解
//                    DatabaseReference getusername = FirebaseDatabase.getInstance().getReference("users/" + value.author_uid);
////其實只要用取單筆資料的Listener就好
//                   // getusername = FirebaseDatabase.getInstance().getReference("users/" + value.author_uid + "/useruid");
//                    getusername.addValueEventListener(new ValueEventListener() {
//
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
//                                user value = Snapshot.getValue(user.class);
//                                username = value.nickname;
//                                Log.w(TAG,"username!!!"+username);
//                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError firebaseError) {
//                            Log.w(TAG,"username error"+username);
//                        }
//
//                    });


                    //  a.add(value.title);
                    title1.add(value.title);
                    style1.add(value.style);
                    author1.add(username);
                    storyintro1.add(value.storyintro);
                }
                for (int i = 0; i < title1.size(); i++) {
                    HashMap<String, Object> hm = new HashMap<String, Object>();
                    hm.put("title", title1.get(i));
                    hm.put("style", style1.get(i));
                    hm.put("author_uid", author1.get(i));
                    hm.put("storyintro", storyintro1.get(i));
                    list.add(hm);
                }
                adapter = new SimpleAdapter(
                        ShowStoryListActivity.this,
                        list,
                        R.layout.activity_show_story_list_adapter,
                        new String[]{"title", "author_uid", "style", "storyintro"},
                        new int[]{R.id.storyname, R.id.author, R.id.style, R.id.storyintro});
                lv_showstory.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }

        });

        //by kuo end


        lvlong = lv_showstory.getCount();
        lv_showstory.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "total listview long" + lv_showstory.getCount());
                Log.w("lv postition", position + "");
                Intent intent = new Intent();
                intent.setClass(ShowStoryListActivity.this, PlayVer2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("storyuid", storyuid[position]);
                Log.w(TAG, "get story uid" + storyname[position]);


                intent.putExtras(bundle);
                startActivity(intent);
                // Toast.makeText(MemberActivity.this,"第"+(position+1)+"條",Toast.LENGTH_SHORT).show();
            }
        });


    }


}
