package com.ewns.restoran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MasaEkle extends AppCompatActivity {


    private Toolbar actionbarMasaEkle;
    private Button eklebtn;
    private EditText ekle_edittxt;
    public String masaismi;



    FirebaseDatabase database;
    DatabaseReference myRef;


    public void topbar() {
        actionbarMasaEkle = findViewById(R.id.actionbarMasaEkle);
        setSupportActionBar(actionbarMasaEkle);
        getSupportActionBar().setTitle("Masa Ekle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masa_ekle);
        topbar();

        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Masalar").child(StartActivity.kullanici);

        eklebtn=findViewById(R.id.ekle_btn);
        ekle_edittxt=findViewById(R.id.editTextMasaekle);

        eklebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masaismi=ekle_edittxt.getText().toString().trim();
                myRef = FirebaseDatabase.getInstance().getReference().child("Masalar").child(StartActivity.kullanici);


                Masalar masa=new Masalar("",masaismi,"BOŞ");

                myRef.push().setValue(masa).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            ekle_edittxt.setText("");
                            Toast.makeText(MasaEkle.this,"Masa Başarıyla eklendi.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MasaEkle.this,Admin_panel.class));
                            finish();
                        }
                        else{
                            ekle_edittxt.setText("");
                            Toast.makeText(MasaEkle.this,"Masa Oluştururken bir sorun oluştu.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }
}
