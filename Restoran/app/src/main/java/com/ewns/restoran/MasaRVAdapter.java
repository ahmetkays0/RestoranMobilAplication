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

public class MasaRVAdapter extends RecyclerView.Adapter<MasaRVAdapter.CardViewTasarimNesneleriniTutucu> {
    private Context mContext;
    public List<Masalar> masaisimleriList;


    public static  String masa_id;



    public MasaRVAdapter(Context mContext, List<Masalar> masaisimleriList) {
        this.mContext = mContext;
        this.masaisimleriList = masaisimleriList;
    }

    public class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder{

        public TextView masaismiTxt,masadurum_txt;
        public CardView CardViewmasa;

        public CardViewTasarimNesneleriniTutucu(View view){
            super(view);


            masaismiTxt=view.findViewById(R.id.masaisim);

            CardViewmasa=view.findViewById(R.id.satirCardView);

        }
    }


    @NonNull
    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_tasarim_masa,viewGroup,false);


        return new CardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimNesneleriniTutucu holder, int i) {

        final Masalar m=masaisimleriList.get(i);

        holder.masaismiTxt.setText(m.getMasa_ismi());


        holder.CardViewmasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             masa_id=m.getMasa_id();

                Intent intent=new Intent(mContext,MasaDetay.class);
                intent.putExtra("nesne",m);
                intent.putExtra("masaismi",m.getMasa_ismi());
                intent.putExtra("nesne_id",m.getMasa_id());
                mContext.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return masaisimleriList.size();
    }




}
