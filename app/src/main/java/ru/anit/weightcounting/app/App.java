package ru.anit.weightcounting.app;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.anit.weightcounting.api.realm.RealmMigration;


/**
 * Created by user on 13.05.2017.
 */

public class App extends Application {


    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();

        if(mAppContext == null){
            mAppContext = this;
        }

        Realm.init(this);

        //RealmConfiguration config = new RealmConfiguration.Builder().build();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new RealmMigration())
                .build();
        Realm.setDefaultConfiguration(config);

    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
