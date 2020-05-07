package com.ewns.restoran;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAdapter extends FragmentPagerAdapter {

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                masaFragment masaFragment=new masaFragment();
                return masaFragment;

            case 1:
                UrunFragment urunFragment =new UrunFragment();
                return urunFragment;


                default:
                    return null;
        }


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int i) {
        switch (i){
            case 0:

                return "Masalar";

            case 1:

                return "Ürünler";



            default:
                return null;
        }

    }
}
