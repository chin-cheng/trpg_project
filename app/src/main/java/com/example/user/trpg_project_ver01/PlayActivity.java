package com.example.user.trpg_project_ver01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.trpg_project_ver01.models.Postoption;
import com.example.user.trpg_project_ver01.models.Poststory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class PlayActivity extends BaseActivity {
    private String userUID;
    TextView test;
    Button choice1,choice2,start;
    long chapter=0;
    long branch=0;
    long endcheck=0;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    FirebaseDatabase database;
    DatabaseReference plot = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch);
    DatabaseReference option1 = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch+"/option/1");
    DatabaseReference option2 = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch+"/option/2");

    private DatabaseReference mDatabase;
    private ArrayList<Poststory> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//目前問題為讀取時不會依照程式碼先後進行讀取,詳見log的ch及ch2之問題,可能與執行序相關

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // userUID =  user.getUid();
        userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        start=findViewById(R.id.start);//?
        choice1=findViewById(R.id.choice1);
        choice2=findViewById(R.id.choice2);
        test = findViewById(R.id.test);
        //test.setText(userUID);
      //  ListView ls =findViewById(R.id.ls);

        if (endcheck == 999) {//就是endcheck
            readplot(plot);
        } else {
            readplot(plot);
            readoptiontext(option1, choice1);
            readoptiontext(option2, choice2);
        }
        }
//------------------------------

        public void readplot(final DatabaseReference plot){



                        plot.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                Poststory value = snapshot.getValue(Poststory.class);
                                test.append(value.plot);
                                //endcheck=value.endcheck;
                            }
                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                                Log.w("AG", "Failed to read value.", error.toException());
                            }
                        });
                        Log.w("AG", "readplot-ch"+chapter+"br"+branch);




        }

        public void readposition(final DatabaseReference option){
        new Thread(new Runnable(){
public void run(){
        try {
            option.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Postoption value = snapshot.getValue(Postoption.class);
                    //if(endcheck!=1) {
                    Toast.makeText(PlayActivity.this, "yo", Toast.LENGTH_LONG).show();
                        chapter=value.arrive_chapter;
                        branch=value.arrive_branch;
                    Log.w("AG", "readposition inside ch"+chapter+"br"+branch);
                    //問題在這裡
                   // Toast.makeText(PlayActivity.this, "x"+chapter+"x"+value.arrive_chapter, Toast.LENGTH_LONG).show();
                    //}
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("AG", "Failed to read value.", error.toException());
                }
            });

            Log.w("AG", "readposition ch"+chapter+"br"+branch);
        } catch (Exception e) {
            e.printStackTrace();

        }
    System.out.println("子线程执行！");


}
        }).start();

        }

    public void appointpostition(){
        plot=FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch);
        option1 = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch+"/option/1");
        option2 = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch+"/option/2");
        Log.w("AG", "appointpostition-ch"+chapter+"br"+branch);
    }


    public void readendcheck(DatabaseReference option){
        option.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Poststory value = snapshot.getValue(Poststory.class);
                //if(endcheck!=1) {
                endcheck=value.endcheck;
                //}
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AG", "Failed to read value.", error.toException());
            }
        });


    }

    public void readoptiontext(DatabaseReference option, final TextView text) {
        option.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Postoption value = snapshot.getValue(Postoption.class);
                //if(endcheck!=1) {
                    text.setText(value.option_name);
//                        chapter = value.arrive_chapter;
//                        branch = value.arrive_branch;
              //  }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AG", "Failed to read value.", error.toException());
            }
        });
        Log.w("AG", "readoptiontext-ch"+chapter+"br"+branch);
    }
//--------------------------
    public void test(View v){

        DatabaseReference plot = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch);
        DatabaseReference option1 = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch+"/option/1");
        DatabaseReference option2 = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch+"/option/2");
        readplot(plot);
        readoptiontext(option1,choice1);
    }

    public void testbreak(){
        Log.w("AG", "RUN-ch"+chapter+"br"+branch);
        //sleep(500);
        appointpostition();
        readendcheck(plot);

        if (endcheck == 999) {//就是endcheck
            readplot(plot);
        } else if(branch==0&&chapter==0){
            Toast.makeText(PlayActivity.this, "error", Toast.LENGTH_LONG).show();
        }else{
            readplot(plot);
            readoptiontext(option1, choice1);
            readoptiontext(option2, choice2);
        }
        //Toast.makeText(PlayActivity.this, "2", Toast.LENGTH_LONG).show();
}

public void run(View v)  /*throws InterruptedException*/ {
    //appointpostition();

    switch (v.getId()) {
        case R.id.choice1:
            Log.w("AG", "-------btnstart------");
            Log.w("AG", "RUN-ch"+chapter+"br"+branch);
            //sleep(500);

            Thread childrenThread=new Thread(new Runnable(){
                public void run(){
                    try {
                        readposition(option1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程执行！");

                }
            });
            childrenThread.start();
            appointpostition();





            //readendcheck(plot);

            if (endcheck == 999) {//就是endcheck
                readplot(plot);
            } else if(branch==0&&chapter==0){
                Toast.makeText(PlayActivity.this, "error", Toast.LENGTH_LONG).show();
            }else{
                readplot(plot);
                readoptiontext(option1, choice1);
                readoptiontext(option2, choice2);
            }
            //Toast.makeText(PlayActivity.this, "2", Toast.LENGTH_LONG).show();

            break;
        case R.id.choice2:

                    readposition(option2);
                    Log.w("AG", "RUN-ch"+chapter+"br"+branch);
            //sleep(500);
            appointpostition();
            readendcheck(plot);

            if (endcheck == 999) {//就是endcheck
                readplot(plot);
            } else if(branch==0&&chapter==0){
                Toast.makeText(PlayActivity.this, "error", Toast.LENGTH_LONG).show();
            }else{
                readplot(plot);
                readoptiontext(option1, choice1);
                readoptiontext(option2, choice2);
            }
            //Toast.makeText(PlayActivity.this, "2", Toast.LENGTH_LONG).show();

            break;
        default:
            Toast.makeText(PlayActivity.this, "x", Toast.LENGTH_LONG).show();
            break;
    }


}
}
/*
    DatabaseReference plot = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch);
    DatabaseReference option1 = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch + "/option/1");
    DatabaseReference option2 = FirebaseDatabase.getInstance().getReference("story/storyname/content/chapter/" + chapter + "/branch/" + branch + "/option/2");

    plot.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            Poststory value = snapshot.getValue(Poststory.class);
            test.append(value.plot);
            endcheck = value.endcheck;
            Log.e("Get Data", "children+" + String.valueOf(snapshot.getChildren()));
            Log.e("Get Data", "getvalue+" + String.valueOf(snapshot.getValue()));
            Log.e("Get Data", "option+tostring" + value.plot);
            Log.d("TAG", "end" + value.endcheck);
            Log.d("TAgh", "pl" + value.plot);
            Log.d("TAGj", "po" + value.position);
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w("AG", "Failed to read value.", error.toException());
        }
    });
    if (endcheck == 0) {
        option1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                // for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                //<YourClass> post = postSnapshot.getValue(<YourClass>.class)
                //Poststory value = snapshot.getValue(Poststory.class);
                Postoption value = snapshot.getValue(Postoption.class);
                Log.e("Get Data", "arrive_chapter" + value.arrive_chapter.toString());
                choice1.setText(value.option_name);
                //Log.d("T", "branch" + value.branch);
                //Log.d("TA", "chapter" + value.chapter);
                Log.d("TAG", "arrive_chapter" + value.arrive_chapter);
                Log.d("TAG", "arrive_branch" + value.arrive_branch);
                Log.d("TAG", "option_name" + value.option_name);
                Log.d("TAG", "option_position" + value.option_position);
                //Log.d("TAGj", "option" + value.option);
                //Log.d("TAGk", "op" + value.option_name)


                // }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AG", "Failed to read value.", error.toException());
            }
        });


        option2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                // for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                //<YourClass> post = postSnapshot.getValue(<YourClass>.class)
                //Poststory value = snapshot.getValue(Poststory.class);
                Postoption value = snapshot.getValue(Postoption.class);
                Log.e("Get Data", "arrive_chapter" + value.arrive_chapter.toString());
                choice2.setText(value.option_name);
                //Log.d("T", "branch" + value.branch);
                //Log.d("TA", "chapter" + value.chapter);
                Log.d("TAG", "arrive_chapter" + value.arrive_chapter);
                Log.d("TAG", "arrive_branch" + value.arrive_branch);
                Log.d("TAG", "option_name" + value.option_name);
                Log.d("TAG", "option_position" + value.option_position);
                //Log.d("TAGj", "option" + value.option);
                //Log.d("TAGk", "op" + value.option_name)


                // }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AG", "Failed to read value.", error.toException());
            }
        });
    }
}*/









//}

/*
class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        final TextView textViewName;
        final TextView textViewPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.tv_name);
            textViewPhone = (TextView) itemView.findViewById(R.id.tv_phone);
        }

        public void setValues(Poststory contact){
            textViewName.setText(contact.branch.toString());
            textViewPhone.setText(contact.title);
        }
    }
}*/

