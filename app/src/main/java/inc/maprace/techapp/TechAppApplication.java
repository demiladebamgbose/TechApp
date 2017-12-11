package inc.maprace.techapp;

import android.app.Application;

import javax.inject.Singleton;

import inc.maprace.techapp.dagger.AppComponent;
import inc.maprace.techapp.dagger.AppModule;
import inc.maprace.techapp.dagger.DaggerAppComponent;

/**
 * Created by demiladebamgbose on 12/11/17.
 */


public class TechAppApplication extends Application {
    private final String baseUrl = "https://newsapi.org/v2/";
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(baseUrl, this))
                .build();
    }

    public AppComponent getAppComponent () {
        return appComponent;
    }

}
