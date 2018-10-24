package com.example.user.trpg_project_ver01;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseplayActivity extends AppCompatActivity {
    TextView tv, tv1, tv2, tvle;
    private  final String TAG="";
    private FirebaseAuth mAUTH;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseplay);
        mAUTH = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv = findViewById(R.id.tv);
        tvle = findViewById(R.id.tvle);
        DatabaseReference myRef = mDatabase.getReference("story/storyname");
        Log.d("TA6", "key: " + myRef.getKey());
        myRef.addValueEventListener(new ValueEventListener()  {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Postchoose value = dataSnapshot.getValue(Postchoose.class);
                            tvle.setText(value.style);
                            tv1.setText(value.title);
                            tv2.setText(value.author_uid);
                            tv.setText(value.storyintro);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.w("AG", "oncancelled.", databaseError.toException());
                        }
                    });




                }

        }



 /*   String interest[] = new String[]{"小明的故事", "文學與垃圾", "自我介紹"};
    String detail[] = new String[]{"身為一個小明，發生什麼事都很正常的", "文學是門藝術，但不是人人懂", "這是你的故事，你自己看著辦"};
    String author[] = new String[]{"作者A","作者B","作者C"};
    String style[] = new String[]{"類型A","類型B","類型C"};
    ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    SimpleAdapter adapter;*/
// userUID =  user.getUid();

/*   tv2.setText(value.content);*/
               /* Log.d("TAG", "br" + value.);
                Log.d("TAgh", "ch" + value.chapter);
                Log.d("TAGj", "stiry" + value.story);*/


        /*for (int i = 0; i < interest.length; i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("interest", interest[i]);
            item.put("detail", detail[i]);
            item.put("author", author[i]);
            item.put("style", style[i]);
            list.add(item);
        }
        adapter = new SimpleAdapter(
                this,
                list,
                R.layout.activity_chooseplay,
                new String[]{"author","style", "interest", "detail"},
                new int[]{R.id.tvle,R.id.tv2, R.id.tv1, R.id.tv}
        );
        setListAdapter(adapter);
    }
    private ListView.OnItemClickListener listener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view , int position , long id){
            intent = new Intent(getApplicationContext(),PlayActivity.class);
            intent.setClass(getApplicationContext(),PlayActivity.class);
            intent.putExtra("item",position);
            startActivity(intent);

        }*/

