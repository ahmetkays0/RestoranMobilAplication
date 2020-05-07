package com.ewns.restoran;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UrunFragment extends Fragment {


    private FloatingActionButton fab;


    private RecyclerView fragment_rv;
    private ArrayList<Yemekler> yemeklerList;
    private AdminMenuRVAdapter adapter;

     FirebaseDatabase database;
     DatabaseReference myRef2;

    public UrunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =inflater.inflate(R.layout.fragment_urun, container, false);

        fab=view.findViewById(R.id.fab_menu);


        database= FirebaseDatabase.getInstance();
        myRef2=database.getReference("Yemekler").child(StartActivity.kullanici);

        fragment_rv=view.findViewById(R.id.fragment_menurv);
        fragment_rv.setHasFixedSize(true);


        fragment_rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));


        yemeklerList =new ArrayList<>();
        adapter=new AdminMenuRVAdapter(getActivity().getApplication(), yemeklerList);

        fragment_rv.setAdapter(adapter);
        tumMenuler();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().startActivity(new Intent(getActivity().getApplication(),UrunEkle.class));

            }
        });


        return view;
    }




    public void tumMenuler(){

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yemeklerList.clear();

                for(DataSnapshot d:dataSnapshot.getChildren()){

                    Yemekler yemek=d.getValue(Yemekler.class);
                    yemek.setYemek_id(d.getKey());
                    yemeklerList.add(yemek);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
