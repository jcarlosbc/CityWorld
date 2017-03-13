package com.hispano_mx.cityworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hispano_mx.cityworld.R;
import com.hispano_mx.cityworld.adapters.CityMainAdapter;
import com.hispano_mx.cityworld.models.Ciudad;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Realm realm;
    private RealmResults<Ciudad> list_ciudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializa Realm Cities
        realm = Realm.getDefaultInstance();
        list_ciudades = realm.where(Ciudad.class).findAll();
        Log.i("MainActivity","onCreate lista_ciudades size->"+list_ciudades.size());

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CityMainAdapter(this, list_ciudades, R.layout.cities_adapter, new CityMainAdapter.OnJCItemClickListener() {
            @Override
            public void onitemClickListener(Ciudad cd, int position) {

            }
        });

        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AltaCity.class);
            startActivity(intent);
            }
        });

    }
}
