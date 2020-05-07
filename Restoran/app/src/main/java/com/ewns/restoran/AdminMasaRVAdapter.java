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

public class AdminMasaRVAdapter extends RecyclerView.Adapter<AdminMasaRVAdapter.AdminCardViewTasarimNesneleriniTutucu> {
    private Context mContext;
    public List<Masalar> masaisimleriList;


    public AdminMasaRVAdapter(Context mContext, List<Masalar> masaisimleriList) {
        this.mContext = mContext;
        this.masaisimleriList = masaisimleriList;
    }

    public class AdminCardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder{

        public TextView adminmasa_txt;
        public CardView AdminMasaCardView;

        public AdminCardViewTasarimNesneleriniTutucu(View view){
            super(view);


            adminmasa_txt=view.findViewById(R.id.adminmasa_txt);
            AdminMasaCardView=view.findViewById(R.id.AdminMasaCardView);

        }
    }


    @NonNull
    @Override
    public AdminCardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_tasarim_admin_masa,viewGroup,false);


        return new AdminCardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminCardViewTasarimNesneleriniTutucu holder, int i) {

        final Masalar m=masaisimleriList.get(i);
        holder.adminmasa_txt.setText(m.getMasa_ismi());
        holder.AdminMasaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, Admin_masa_detay.class);
                intent.putExtra("nesne",m);
                mContext.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return masaisimleriList.size();
    }




}
