package com.ewns.restoran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MasaDetay extends AppCompatActivity {

    //hesap
    public HesapRVAdapter hesapRV;
    private ArrayList<Hesap> hesapList;

    //menü
    private Toolbar actionbarMasaDetay;
    private Masalar masa;
    private RecyclerView menurv;
    private RecyclerView listrv;
    private ArrayList<Yemekler> yemeklerList;
    private MenuRVAdapter rvAdapter;


    FirebaseDatabase database;
    DatabaseReference myRef,myRef2,myRef3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masa_detay);


        masa = (Masalar) getIntent().getSerializableExtra("nesne");


        topbar();

        database= FirebaseDatabase.getInstance();
        myRef=database.getReference("Yemekler").child(StartActivity.kullanici);
        myRef2=database.getReference("Hesap").child(StartActivity.kullanici).child(MasaRVAdapter.masa_id);

        myRef3=database.getReference("Masalar").child(StartActivity.kullanici);



        menurv=findViewById(R.id.menurv);
        menurv.setHasFixedSize(true);
        menurv.setLayoutManager(new LinearLayoutManager(this));

        listrv=findViewById(R.id.listrv);
        listrv.setHasFixedSize(true);
        listrv.setLayoutManager(new LinearLayoutManager(this));



        yemeklerList = new ArrayList<>();
        rvAdapter = new MenuRVAdapter(this, yemeklerList);
        menurv.setAdapter(rvAdapter);
        tumYemekler();


        hesapList=new ArrayList<>();
        hesapRV=new HesapRVAdapter(this,hesapList);
        listrv.setAdapter(hesapRV);

        tumHesaplar();

        Map<String,Object> bilgiler=new HashMap<>();


    }

    public void topbar() {
        actionbarMasaDetay = findViewById(R.id.actionbarMasaDetay);
        setSupportActionBar(actionbarMasaDetay);
        getSupportActionBar().setTitle(masa.getMasa_ismi());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }





    public void tumHesaplar(){

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hesapList.clear();

                for(DataSnapshot d:dataSnapshot.getChildren()){

                    Hesap hesap=d.getValue(Hesap.class);
                    hesap.setHesap_yemek_id(d.getKey());
                    hesapList.add(hesap);

                }

                hesapRV.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void tumYemekler(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yemeklerList.clear();
                for(DataSnapshot d:dataSnapshot.getChildren()){

                    Yemekler yemek=d.getValue(Yemekler.class);
                    yemek.setYemek_id(d.getKey());
                    yemeklerList.add(yemek);
                }

                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.masa_kapa,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.hesap_kapat:
                Snackbar.make(actionbarMasaDetay," Hesap Ödendi mi?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        myRef2.removeValue();

                        startActivity(new Intent(MasaDetay.this,MasaActivity.class));
                        finish();
                    }
                }).show();
                return true;

            default:

                return false;
        }
    }
}


