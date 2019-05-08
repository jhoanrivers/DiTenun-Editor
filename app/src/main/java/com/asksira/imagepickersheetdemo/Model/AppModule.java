package com.asksira.imagepickersheetdemo.Model;

import android.app.Application;

import com.asksira.imagepickersheetdemo.App;
import com.asksira.imagepickersheetdemo.activity.SpecificSingleImage;

import dagger.Module;
import dagger.Provides;


@Module(
        includes = {
                APIModule.class,
                DataModule.class
        },
        library = true,
        injects = {
                SpecificSingleImage.class
        }
)

public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }
}
