package com.hispano_mx.cityworld.app;

import android.app.Application;

import com.hispano_mx.cityworld.models.Ciudad;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by jcblas on 3/9/2017.
 */
public class MyApplication extends Application {
    public static AtomicInteger CiudadID = new AtomicInteger();

    @Override
    public void onCreate() {
        //setUpConfig();
        Realm.init(getApplicationContext());

        Realm realm = Realm.getDefaultInstance();
        CiudadID = getIdByTable(realm, Ciudad.class);
        realm.close();
    }

    private void setUpConfig(){
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    //Este metodo regresara el ultimo id insertado en cualquier tabla
    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0 ) ? new AtomicInteger(results.max("id").intValue()): new AtomicInteger() ;
    }

}
