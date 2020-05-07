package com.ewns.restoran;
        import android.content.Context;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import java.util.List;

public class HesapRVAdapter extends RecyclerView.Adapter<HesapRVAdapter.HesapCardViewTasarimNesneleriniTutucu> {
    private Context mContext;
    private List<Hesap> hesapList;

    public HesapRVAdapter(Context mContext, List<Hesap> hesapList) {
        this.mContext = mContext;
        this.hesapList = hesapList;
    }

    public class HesapCardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder{

        public TextView yemekadhesap_txt,adethesap_txt;
        public androidx.cardview.widget.CardView CardView;

        public HesapCardViewTasarimNesneleriniTutucu(View view){
            super(view);

            yemekadhesap_txt=view.findViewById(R.id.yemekadhesap_txt);
            adethesap_txt=view.findViewById(R.id.hesapadet_txt);
            CardView=view.findViewById(R.id.HesapCardView);

        }
    }


    @NonNull
    @Override
    public HesapCardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_tasarim_hesap,viewGroup,false);


        return new HesapCardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HesapCardViewTasarimNesneleriniTutucu holder, int i) {


        final Hesap hesap= hesapList.get(i);
        holder.yemekadhesap_txt.setText(hesap.getHesap_yemek_ismi());
        holder.adethesap_txt.setText(hesap.getHesap_yemek_adet()+" Adet");

    }

    @Override
    public int getItemCount() {
        return hesapList.size();
    }




}



