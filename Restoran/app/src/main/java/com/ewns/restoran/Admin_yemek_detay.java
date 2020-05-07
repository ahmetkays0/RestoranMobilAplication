package com.ewns.restoran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Admin_yemek_detay extends AppCompatActivity {

    private Toolbar actionbarAdminYemekDetay;
    private Yemekler yemek;

    private EditText guncelleyemek_edittxt,guncelleyemekfiyat_edittxt;

    private FirebaseDatabase database;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_yemek_detay);

        guncelleyemek_edittxt=findViewById(R.id.guncelleyemek_edittxt);
        guncelleyemekfiyat_edittxt=findViewById(R.id.guncelleyemekfiyat_edittxt);


        database= FirebaseDatabase.getInstance();
        myRef=database.getReference("Yemekler").child(StartActivity.kullanici);

        yemek = (Yemekler) getIntent().getSerializableExtra("nesne");


        guncelleyemek_edittxt.setText(yemek.getYemek_ismi());
        guncelleyemekfiyat_edittxt.setText(Integer.toString(yemek.getYemek_fiyat()));
        topbar();
    }

    public void topbar() {
        actionbarAdminYemekDetay = findViewById(R.id.actionbarAdminYemekDetay);
        setSupportActionBar(actionbarAdminYemekDetay);
        getSupportActionBar().setTitle(yemek.getYemek_ismi());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_masa_detay,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sil:
                Snackbar.make(actionbarAdminYemekDetay,"Yemek Silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        myRef.child(yemek.getYemek_id()).removeValue();

                        startActivity(new Intent(Admin_yemek_detay.this,Admin_panel.class));
                        finish();
                    }
                }).show();
                return true;

            case R.id.action_guncelle:
                String fiyat=guncelleyemekfiyat_edittxt.getText().toString().trim();
                Map<String,Object> bilgiler=new HashMap<>();

                 bilgiler.put("yemek_ismi",guncelleyemek_edittxt.getText().toString().trim());
                 bilgiler.put("yemek_fiyat",Integer.parseInt(fiyat));

                myRef.child(yemek.getYemek_id()).updateChildren(bilgiler);

                startActivity(new Intent(Admin_yemek_detay.this,Admin_panel.class));
                finish();
                return true;
            default:
                return false;
        }
    }
}

