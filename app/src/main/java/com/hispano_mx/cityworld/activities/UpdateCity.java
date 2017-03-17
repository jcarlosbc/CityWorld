package com.hispano_mx.cityworld.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hispano_mx.cityworld.R;
import com.hispano_mx.cityworld.models.Ciudad;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class UpdateCity extends AppCompatActivity{
    private Realm realm;

    private ImageView cd_img;
    private TextView cd_name;
    private TextView cd_url;
    private TextView cd_desc;
    private RatingBar cd_rating;
    private FloatingActionButton btn_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_city);

        Intent intent = getIntent();
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Ciudad cd = realm.where(Ciudad.class).equalTo("id",intent.getIntExtra("idCiudad",-1)).findFirst();
        realm.commitTransaction();

        inicializa(cd);


    }

    private void inicializa(final Ciudad cd ){
        cd_img = (ImageView) findViewById(R.id.update_img);
        Picasso.with(this)
                .load(cd.getUrl_img())
                .error(R.mipmap.ic_launcher)
                .into(cd_img);
        cd_name = (TextView) findViewById(R.id.update_edit_name_city);
        cd_name.setText(cd.getNombre());

        cd_desc = (TextView) findViewById(R.id.update_edit_description_city);
        cd_desc.setText(cd.getDescripcion());

        cd_url = (TextView) findViewById(R.id.update_edit_link_city);
        cd_url.setText(cd.getUrl_img());

        cd_rating = (RatingBar) findViewById(R.id.update_rating_city);
        cd_rating.setRating(cd.getEstrellas());

        btn_update = (FloatingActionButton) findViewById(R.id.fab_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_ciudad(cd);
            }
        });
    }

    private void update_ciudad(Ciudad cd) {
        realm.beginTransaction();
        cd.setNombre(cd_name.getText().toString());
        cd.setDescripcion(cd_desc.getText().toString());
        cd.setUrl_img(cd_url.getText().toString());
        cd.setEstrellas(cd_rating.getRating());
        realm.copyToRealmOrUpdate(cd);
        realm.commitTransaction();

        onBackPressed();

    }
}
