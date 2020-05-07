package com.ewns.restoran;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.*;


public class StartActivity extends AppCompatActivity {

    EditText edit_email_giris,edit_sifre_giris;
    Button girisBTN;
    TextView kayitTV;
    public static  String kullanici;

    FirebaseAuth girisYetkisi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        edit_email_giris=findViewById(R.id.EmailET);
        edit_sifre_giris=findViewById(R.id.sifreET);
        girisBTN=findViewById(R.id.girisyapBTN);
        kayitTV=findViewById(R.id.kayitTV);


        girisYetkisi=FirebaseAuth.getInstance();


        girisBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pdgiris=new ProgressDialog(StartActivity.this);
                pdgiris.setMessage("Giriş Yapılıyor...");
                pdgiris.show();


                //Firebase kodları

                String str_emailgiris=edit_email_giris.getText().toString();
                String str_sifregiris=edit_sifre_giris.getText().toString();
                if(TextUtils.isEmpty(str_emailgiris)|| TextUtils.isEmpty(str_sifregiris)){
                    Toast.makeText(StartActivity.this,"Boş alanları doldurunuz...",Toast.LENGTH_LONG).show();
                }
                else
                {
                    //Giriş YAPMA KODLARI




                    girisYetkisi.signInWithEmailAndPassword(str_emailgiris,str_sifregiris)
                            .addOnCompleteListener(StartActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        kullanici=girisYetkisi.getCurrentUser().getUid();
                                        final DatabaseReference yolGiris= FirebaseDatabase.getInstance().getReference()
                                                .child("Kullanicilar").child(girisYetkisi.getCurrentUser().getUid());

                                        yolGiris.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                pdgiris.dismiss();



                                                Intent intent =new Intent(StartActivity.this, Admin_panel.class);

                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                finish();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                pdgiris.dismiss();
                                            }
                                        });
                                    }

                                    else
                                    {
                                        pdgiris.dismiss();
                                        Toast.makeText(StartActivity.this, "Kullanıcı adı veya şifre hatalı lütfen tekrar deneyiniz...", Toast.LENGTH_LONG).show();


                                    }
                                }
                            });

                }


            }
        });

        kayitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartActivity.this,KayitolActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
