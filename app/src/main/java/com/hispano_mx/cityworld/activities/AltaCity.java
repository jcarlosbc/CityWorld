package com.hispano_mx.cityworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.hispano_mx.cityworld.R;
import com.hispano_mx.cityworld.models.Ciudad;

import io.realm.Realm;

/**
 * Created by jcblas on 3/9/2017.
 */

public class AltaCity extends AppCompatActivity {
    private Realm realm;

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
    }

    private void createNewCity(String name, String url, String desc, float rating) {
        realm.beginTransaction();
        Ciudad cd = new Ciudad(name,desc,url,rating);
        realm.copyToRealm(cd);
        realm.commitTransaction();
    }
}
