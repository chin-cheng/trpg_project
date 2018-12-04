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

import com.example.user.trpg_project_ver01.models.Post;
import com.example.user.trpg_project_ver01.models.Postchoose;
import com.example.user.trpg_project_ver01.models.Poststory;
import com.example.user.trpg_project_ver01.models.User;
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


    ListView lv_showstory;
    TextView storyintrotext, storynametext, authortext, styletext;
    String[] func = new String[999];
    String[] storyuid = new String[999];
    String[] storyname = new String[999];
    int count = 0;
    int lvlong = 0;
String type="";


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
        setTitle("所有故事");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story_list);
        lv_showstory = findViewById(R.id.lv_showstory);

        Intent intentget=this.getIntent();
        Bundle bundleget=intentget.getExtras();
        type=bundleget.getString("type");

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
                    Log.w(TAG,"choose list"+Snapshot);
                    Postchoose value = Snapshot.getValue(Postchoose.class);
                    final String[] username = {""};
                    username[0] = "應該要是暱稱";
                    Log.w(TAG,"_username"+ username[0] +"\n uid"+value.author_uid);

//要把uid改成nickname的bug未解

//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference getusername = database.getReference("users/"+value.author_uid+"");
//                    // getReference("users/9FS0dg1lZ4cwMdqbnV7d4ejnR8E2");
//
//
//                    getusername.addValueEventListener(new ValueEventListener() {
//
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                          //  for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
//                                Log.w(TAG,"users:"+dataSnapshot);
//                                Post value = dataSnapshot.getValue(Post.class);
//
//                             //   username[0] = value.nickname;
////塞進去
//                                Log.w(TAG,"username!!!"+ username[0]);
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError firebaseError) {
//                            Log.w(TAG,"username error"+ username[0]);
//                        }
//
//                    });

                    Log.w(TAG,"lv add");
                    title1.add(value.title);
                    style1.add(value.style);
                    author1.add(value.author_uid);
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
                Log.w(TAG, "type:"+type);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
if(type.equals("edit")){
    bundle.putString("type", "edit");
    intent.setClass(ShowStoryListActivity.this, ChoiceChapterActivity.class);

}else if(type.equals("play")){
    bundle.putString("type", "play");
    intent.setClass(ShowStoryListActivity.this, PlayVer2Activity.class);

}


                bundle.putString("storyuid", storyuid[position]);
                bundle.putString("key", storyuid[position]);
                Log.w(TAG, "get story uid" + storyname[position]);


                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                // Toast.makeText(MemberActivity.this,"第"+(position+1)+"條",Toast.LENGTH_SHORT).show();
            }
        });


    }


}
