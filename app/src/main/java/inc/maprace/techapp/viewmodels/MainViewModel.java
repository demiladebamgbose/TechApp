package inc.maprace.techapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import inc.maprace.techapp.data.Article;
import inc.maprace.techapp.data.ArticlesRepository;

/**
 * Created by demiladebamgbose on 12/11/17.
 */

public class MainViewModel extends ViewModel {

    private ArticlesRepository mRepository;


    private LiveData<List<Article>> mArticles;

    public LiveData<List<Article>> getArticles() {
        return mArticles;
    }


    public MainViewModel (ArticlesRepository repository) {
        mRepository = repository;
        mArticles = Transformations.map(mRepository.getArticles(), (articles) -> articles);
    }



}
