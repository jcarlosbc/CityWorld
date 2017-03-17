package com.hispano_mx.cityworld.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.hispano_mx.cityworld.R;
import com.hispano_mx.cityworld.models.Ciudad;

import io.realm.Realm;

/**
 * Created by jcblas on 3/9/2017.
 */

public class AltaCity extends AppCompatActivity {
    private Realm realm;
    private ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_activity);

        final EditText city_name = (EditText) findViewById(R.id.edit_name_city);
        final EditText city_url = (EditText) findViewById(R.id.edit_link_city);
        final EditText city_desc = (EditText) findViewById(R.id.edit_description_city);
        final RatingBar city_rating = (RatingBar) findViewById(R.id.rating_city);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_alta);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewCity(city_name.getText().toString(), city_url.getText().toString(), city_desc.getText().toString(), city_rating.getRating());
            }
        });

        btn = (ImageButton) findViewById(R.id.imageButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(city_url.length()>0){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(city_url.getText().toString()));
                    startActivity(intent);
                }
            }
        });
    }

    private void createNewCity(String name, String url, String desc, float rating) {
        realm.beginTransaction();
        Ciudad cd = new Ciudad(name,desc,url,rating);
        realm.copyToRealm(cd);
        realm.commitTransaction();
        Log.d("AltaCity","createNewCity despues de la transaccion");
        onBackPressed();
    }
}
