package com.ewns.restoran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Admin_masa_detay extends AppCompatActivity {

    private Toolbar actionbarAdminMasaDetay;
    private Masalar masa;

    private EditText guncelle_edit;

    private FirebaseDatabase database;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_masa_detay);

        guncelle_edit=findViewById(R.id.guncellemasa_edittxt);


        database= FirebaseDatabase.getInstance();
        myRef=database.getReference("Masalar").child(StartActivity.kullanici);

        masa = (Masalar) getIntent().getSerializableExtra("nesne");

        guncelle_edit.setText(masa.getMasa_ismi());
        topbar();
    }

    public void topbar() {
        actionbarAdminMasaDetay = findViewById(R.id.actionbarAdminMasaDetay);
        setSupportActionBar(actionbarAdminMasaDetay);
        getSupportActionBar().setTitle(masa.getMasa_ismi());
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
                Snackbar.make(actionbarAdminMasaDetay,"Masa Silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        myRef.child(masa.getMasa_id()).removeValue();

                        startActivity(new Intent(Admin_masa_detay.this,Admin_panel.class));
                        finish();
                    }
                }).show();
                return true;

            case R.id.action_guncelle:

                Map<String,Object> bilgiler=new HashMap<>();

                bilgiler.put("masa_ismi",guncelle_edit.getText().toString().trim());
                myRef.child(masa.getMasa_id()).updateChildren(bilgiler);

                startActivity(new Intent(Admin_masa_detay.this,Admin_panel.class));
                finish();
                return true;
            default:
                return false;
        }
    }
}


