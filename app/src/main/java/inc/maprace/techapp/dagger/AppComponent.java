package inc.maprace.techapp.dagger;

import javax.inject.Singleton;

import dagger.Component;
import inc.maprace.techapp.activities.MainActivity;

/**
 * Created by demiladebamgbose on 12/11/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
