package com.ewns.restoran;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.*;


import java.util.List;

public class AdminMenuRVAdapter extends RecyclerView.Adapter<AdminMenuRVAdapter.AdminCardViewTasarimNesneleriniTutucu> {
    private Context mContext;
    public List<Yemekler> yemekisimleriList;


    public AdminMenuRVAdapter(Context mContext, List<Yemekler> yemekisimleriList) {
        this.mContext = mContext;
        this.yemekisimleriList = yemekisimleriList;
    }

    public class AdminCardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder{

        public TextView yemek_ad,yemek_fiyat;
        public CardView AdminMenuCardView;

        public AdminCardViewTasarimNesneleriniTutucu(View view){
            super(view);


            yemek_ad =view.findViewById(R.id.yemek_ad);
            yemek_fiyat =view.findViewById(R.id.yemek_fiyat);
            AdminMenuCardView =view.findViewById(R.id.AdminMenuCardView);

        }
    }


    @NonNull
    @Override
    public AdminCardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_tasarim_admin_menu,viewGroup,false);


        return new AdminCardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminCardViewTasarimNesneleriniTutucu holder, int i) {

        final Yemekler yemek= yemekisimleriList.get(i);

        int yemekfiyat=yemek.getYemek_fiyat();
        holder.yemek_ad.setText(yemek.getYemek_ismi());
        holder.yemek_fiyat.setText(Integer.toString(yemekfiyat));
        holder.AdminMenuCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, Admin_yemek_detay.class);
                intent.putExtra("nesne",yemek);
                mContext.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return yemekisimleriList.size();
    }




}
