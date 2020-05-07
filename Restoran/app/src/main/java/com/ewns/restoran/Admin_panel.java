package com.ewns.restoran;

import android.content.Intent;

import com.ewns.restoran.MasaActivity;
import com.ewns.restoran.R;
import com.ewns.restoran.StartActivity;
import com.ewns.restoran.TabsAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Admin_panel extends AppCompatActivity {

    private Toolbar actionbaradmin;
    private TabsAdapter tabsAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;


    public void init(){
        actionbaradmin=findViewById(R.id.actionbarAdmin);
        setSupportActionBar(actionbaradmin);
        getSupportActionBar().setTitle("Admin Panel");


        viewPager=findViewById(R.id.vpMain);
        tabsAdapter=new TabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAdapter);

        tabLayout=findViewById(R.id.tabsMain);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_masa :
                Intent i=new Intent(this, MasaActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
               // Toast.makeText(getApplicationContext(),"Masa",Toast.LENGTH_LONG).show();
            return true;
            case R.id.action_cikis :
                Snackbar.make(actionbaradmin,"Çıkış yapılsın mı?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(Admin_panel.this,StartActivity.class));
                                finish();
                            }
                        }).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

   @Override
    public void onBackPressed() {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
