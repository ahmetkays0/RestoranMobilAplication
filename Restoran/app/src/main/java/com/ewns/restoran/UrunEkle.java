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

public class UrunEkle extends AppCompatActivity {


    private Toolbar actionbarYemekEkle;
    private Button yemekekle_btn;

    public String yemekismi;
    public String yemekfiyat;

    EditText yemekekle_edittxt,fiyatekle_edittxt;


    FirebaseDatabase database;
    DatabaseReference myRef;


    public void topbar() {
        actionbarYemekEkle = findViewById(R.id.actionbarYemekEkle);
        setSupportActionBar(actionbarYemekEkle);
        getSupportActionBar().setTitle("Yemek Ekle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekle);
        topbar();

        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Menüler").child(StartActivity.kullanici);

        yemekekle_btn=findViewById(R.id.yemekekle_btn);
        yemekekle_edittxt=findViewById(R.id.editTextYemekekle);
        fiyatekle_edittxt=findViewById(R.id.editTextYemekFiyatekle);


        yemekekle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yemekismi=yemekekle_edittxt.getText().toString().trim();
                yemekfiyat=fiyatekle_edittxt.getText().toString().trim();
                myRef = FirebaseDatabase.getInstance().getReference().child("Yemekler").child(StartActivity.kullanici);


                Yemekler yemek=new Yemekler("",yemekismi,Integer.parseInt(yemekfiyat));// Integer.parseInt(yemekfiyat);

                myRef.push().setValue(yemek).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(UrunEkle.this,Admin_panel.class));
                            finish();
                        }
                        else{
                            yemekekle_edittxt.setText("");
                            fiyatekle_edittxt.setText("");
                            Toast.makeText(UrunEkle.this,"Masa Oluştururken bir sorun oluştu.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}
