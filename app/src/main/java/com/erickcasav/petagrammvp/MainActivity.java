package com.erickcasav.petagrammvp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.erickcasav.petagrammvp.adapter.PageAdapter;
import com.erickcasav.petagrammvp.fragment.MascotasListado;
import com.erickcasav.petagrammvp.fragment.PerfilMascota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new MascotasListado());
        fragments.add(new PerfilMascota());

        return fragments;
    }

    private  void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.mAbout:
                Intent intentDesarrollador = new Intent(this, BioDesarrollador.class);
                startActivity(intentDesarrollador);
                break;
            case R.id.mContact:
                Intent intentContacto = new Intent(this, Contacto.class);
                startActivity(intentContacto);
                break;
            case R.id.mavFavorites:

                try
                {
                    Intent intent = new Intent(this, Favoritos.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Log.d("Error",e.getMessage().toString());
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
