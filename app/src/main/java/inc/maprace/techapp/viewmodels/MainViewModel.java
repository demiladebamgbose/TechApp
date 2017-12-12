package inc.maprace.techapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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

    private MutableLiveData<Boolean> mRefreshIndicator = new MutableLiveData<>();
    public MutableLiveData<Boolean> getRefreshIndicator() { return mRefreshIndicator; }
    public void setRefreshIndicator(Boolean refreshIndicator) { mRefreshIndicator.setValue(refreshIndicator);}

    //
    public MainViewModel (ArticlesRepository repository) {
        mRepository = repository;
        mArticles = Transformations.map(mRepository.getArticles(), (articles) -> articles);
    }

    public void getNews() {
        mRepository.fetchData();
    }

    public void getNews(String searchString) {
        mRepository.fetchData(searchString);
    }
    public void getNewsByCategory(String categoty) {
        mRepository.fetchDataByCategoty(categoty);
    }



}
