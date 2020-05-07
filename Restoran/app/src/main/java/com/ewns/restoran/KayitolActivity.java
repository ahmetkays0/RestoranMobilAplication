package com.ewns.restoran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.HashMap;

public class KayitolActivity extends AppCompatActivity {

    private Toolbar actionbarKayitol;
    private EditText edit_ad,edit_email,edit_sifre;
    private Button btn_olustur;
      FirebaseAuth yetki;

      DatabaseReference yol;

   ProgressDialog pd;


    public void init(){
        actionbarKayitol=findViewById(R.id.actionbarKayitol);
        setSupportActionBar(actionbarKayitol);
        getSupportActionBar().setTitle("Hesap Oluştur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitol);
        init();

        edit_ad=findViewById(R.id.edit_ad);
        edit_email=findViewById(R.id.edit_email);
        edit_sifre=findViewById(R.id.edit_sifre);
        btn_olustur=findViewById(R.id.btn_olustur);

        yetki=FirebaseAuth.getInstance();

        btn_olustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd=new ProgressDialog(KayitolActivity.this);
                pd.setMessage("Lütfen Bekleyin...");
                pd.show();

                String str_ad=edit_ad.getText().toString();
                String str_email=edit_email.getText().toString();
                String str_sifre=edit_sifre.getText().toString();

                if(TextUtils.isEmpty(str_ad)||TextUtils.isEmpty(str_email)||TextUtils.isEmpty(str_sifre)){
                    pd.dismiss();
                    Toast.makeText(KayitolActivity.this,"Lütfen bütün alanları doldurunuz.",Toast.LENGTH_LONG).show();
                }
                else if(str_sifre.length()<6){
                    pd.dismiss();
                    Toast.makeText(KayitolActivity.this,"Şifreniz minimum 6 karakter olmalıdır.",Toast.LENGTH_LONG).show();
                }
                else {

                    Kayitet(str_ad,str_email,str_sifre);
                }
            }
        });

    }

    private void Kayitet(final String ad, final String email, final String sifre){

        yetki.createUserWithEmailAndPassword(email,sifre)
                .addOnCompleteListener(KayitolActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser kullanici=yetki.getCurrentUser();
                            String kullaniciID= kullanici.getUid();
                            yol=FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(kullaniciID);

                            HashMap<String,Object> hashMap=new HashMap<>();
                            hashMap.put("id",kullaniciID);
                            hashMap.put("ad",ad);
                            hashMap.put("email",email);
                            yol.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        pd.dismiss();
                                        StartActivity.kullanici=yetki.getCurrentUser().getUid();;
                                        Intent kayit=new Intent(KayitolActivity.this, Admin_panel.class);
                                        kayit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(kayit);
                                    }
                                }
                            });
                        }

                        else{
                            pd.dismiss();
                            Toast.makeText(KayitolActivity.this,"Kayit olma başarısız lütfen tekrar deneyiniz.",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
