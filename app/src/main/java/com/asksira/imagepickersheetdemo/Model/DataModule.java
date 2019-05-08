package com.asksira.imagepickersheetdemo.Model;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module(
        complete = false,
        library = true
)
public class DataModule {
    @Provides
    Realm provideRealm(Application application) {
        return Realm.getDefaultInstance();
    }
}
