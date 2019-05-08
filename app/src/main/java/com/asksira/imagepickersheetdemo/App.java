package com.asksira.imagepickersheetdemo;

import android.app.Application;
import android.content.Context;

import com.asksira.imagepickersheetdemo.Model.AppModule;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.joanzapata.iconify.fonts.TypiconsModule;

import dagger.ObjectGraph;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class App extends Application {
    private final static int SCHEMA_VERSION = 1;
    private ObjectGraph injector;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        //Timber for logging -> no pun intended
        Timber.plant(new Timber.DebugTree());

        // Configure default configuration for Realm
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfig);

        //Iconify
        Iconify
                .with(new FontAwesomeModule())
                .with(new TypiconsModule())
                .with(new IoniconsModule());

        injector = ObjectGraph.create(new AppModule(this));
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public ObjectGraph getInjector() {
        return injector;
    }
}
