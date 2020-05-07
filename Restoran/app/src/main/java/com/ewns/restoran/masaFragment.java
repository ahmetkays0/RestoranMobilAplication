package com.ewns.restoran;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.*;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class masaFragment extends Fragment {

    private FloatingActionButton fab;


    private RecyclerView fragment_rv;
    private ArrayList<Masalar> masalarList;
    private AdminMasaRVAdapter adapter;


    FirebaseDatabase database;
    DatabaseReference myRef;



    public masaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_masa, container, false);

        fab=view.findViewById(R.id.fab_button);


        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Masalar").child(StartActivity.kullanici);

        fragment_rv=view.findViewById(R.id.fragment_rv);
        fragment_rv.setHasFixedSize(true);


        fragment_rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));


        masalarList=new ArrayList<>();
        adapter=new AdminMasaRVAdapter(getActivity().getApplication(),masalarList);

        fragment_rv.setAdapter(adapter);
        tumMasalar();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().startActivity(new Intent(getActivity().getApplication(),MasaEkle.class));

            }
        });







        return view;
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
