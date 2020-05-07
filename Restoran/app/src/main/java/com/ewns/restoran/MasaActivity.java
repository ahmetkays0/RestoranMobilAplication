package com.ewns.restoran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MasaActivity extends AppCompatActivity {


    private Toolbar actionbarmasa;
    FirebaseDatabase database;
    DatabaseReference myRef;



    public void init(){
        actionbarmasa=findViewById(R.id.actionbarMasalar);
        setSupportActionBar(actionbarmasa);
        getSupportActionBar().setTitle("Masalar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private RecyclerView rv;
    private ArrayList<Masalar> masalarList;
    private MasaRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Masalar").child(StartActivity.kullanici);

        rv=findViewById(R.id.masarv);
        rv.setHasFixedSize(true);


        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));


    masalarList=new ArrayList<>();
        adapter=new MasaRVAdapter(this,masalarList);

        rv.setAdapter(adapter);
       tumMasalar();


    }

public void tumMasalar(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                masalarList.clear();

                for(DataSnapshot d:dataSnapshot.getChildren()){

                    Masalar masa=d.getValue(Masalar.class);
                    masa.setMasa_id(d.getKey());
                    masalarList.add(masa);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

}

}

