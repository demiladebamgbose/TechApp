package inc.maprace.techapp.dagger;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inc.maprace.techapp.TechAppApplication;
import inc.maprace.techapp.data.ArticleDatabase;
import inc.maprace.techapp.data.ArticlesRepository;
import inc.maprace.techapp.retrofit.NewsInterface;
import inc.maprace.techapp.retrofit.NewsInterfaceImpl;
import inc.maprace.techapp.viewmodels.ViewModelFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by demiladebamgbose on 12/11/17.
 */

@Module
@Singleton
public class AppModule {

    private String mBaseUrl;
    private TechAppApplication mApplication;

    public AppModule (String baseUrl, TechAppApplication application) {
        mBaseUrl = baseUrl;
        mApplication = application;
    }

    @Singleton
    @Provides
    protected TechAppApplication techAppApplication() {
        return mApplication;
    }

    @Provides
    protected NewsInterface providesNewsInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(new OkHttpClient.Builder()
                        .addNetworkInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .addConverterFactory(GsonConverterFactory.create())
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();

        return  retrofit.create(NewsInterface.class);

    }

//    @Provides
//    protected NewsInterfaceImpl providesNewsInterfaceImpl() {
//        return new NewsInterfaceImpl();
//    }

    @Provides
    @Singleton
    protected ArticleDatabase providesArticleDatabase(TechAppApplication techAppApplication) {
        return  Room.databaseBuilder(techAppApplication, ArticleDatabase.class, "articles")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    protected ArticlesRepository providesArticleRepository(ArticleDatabase articleDatabase, NewsInterface newsInterface, TechAppApplication techAppApplication) {
        return new ArticlesRepository(articleDatabase, newsInterface, techAppApplication);
    }

    @Provides
    protected ViewModelFactory providesViewModelFactory(ArticlesRepository articlesRepository) {
        return new ViewModelFactory(articlesRepository);
    }

}
