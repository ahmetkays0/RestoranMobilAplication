package com.ewns.restoran;

        import android.content.Context;
        import androidx.annotation.NonNull;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;


        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.List;

public class MenuRVAdapter extends RecyclerView.Adapter<MenuRVAdapter.MenuCardViewTasarimNesneleriniTutucu> {
    private Context mContext;
    private List<Yemekler> menuList;

    public int sayac=1;
    DatabaseReference myRef;

    public MenuRVAdapter(Context mContext, List<Yemekler> menuList) {
        this.mContext = mContext;
        this.menuList = menuList;
    }

    public class MenuCardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder{

        public TextView yemekad_txt,adet_txt;
        public Button arti_btn,eksi_btn,ekle_btn;
        public CardView CardView;

        public MenuCardViewTasarimNesneleriniTutucu(View view){
            super(view);

            arti_btn=view.findViewById(R.id.arti_btn);
            eksi_btn=view.findViewById(R.id.eksi_btn);
            ekle_btn=view.findViewById(R.id.ekle_btn);
            yemekad_txt=view.findViewById(R.id.yemekad_txt);
            adet_txt=view.findViewById(R.id.adet_txt);
            CardView=view.findViewById(R.id.menucardView);

        }
    }


    @NonNull
    @Override
    public MenuCardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_tasarim_menu,viewGroup,false);


        return new MenuCardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuCardViewTasarimNesneleriniTutucu holder, int i) {

        final Yemekler yemek= menuList.get(i);



        holder.yemekad_txt.setText(yemek.getYemek_ismi());
        holder.arti_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 sayac = Integer.parseInt(holder.adet_txt.getText().toString());

                sayac+=1;

                holder.adet_txt.setText(Integer.toString(sayac));

            }
        });

        holder.eksi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 sayac = Integer.parseInt(holder.adet_txt.getText().toString());
                if(1!=sayac){
                    sayac-=1;
                    holder.adet_txt.setText(Integer.toString(sayac));
                }



            }
        });

        holder.ekle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              String adet =  holder.adet_txt.getText().toString().trim();

                myRef = FirebaseDatabase.getInstance().getReference().child("Hesap").child(StartActivity.kullanici).child(MasaRVAdapter.masa_id);


                Hesap hesap=new Hesap("",yemek.getYemek_ismi(),Integer.parseInt(adet),yemek.getYemek_fiyat());

                myRef.push().setValue(hesap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                        }
                        else{
                            Toast.makeText(mContext,"Yemek eklenirken bir hata oluştu.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }



    @Override
    public int getItemCount() {
        return menuList.size();
    }




}

