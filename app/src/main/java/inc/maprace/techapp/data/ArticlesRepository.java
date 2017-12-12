package inc.maprace.techapp.data;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import inc.maprace.techapp.R;
import inc.maprace.techapp.TechAppApplication;
import inc.maprace.techapp.retrofit.NewsInterface;
import inc.maprace.techapp.retrofit.model.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by demiladebamgbose on 12/11/17.
 */

public class ArticlesRepository {
    private ArticleDatabase mArticleDatabase;
    private  NewsInterface mNewsInterface;
    private TechAppApplication mApplication;

    private static final String TECHNOLOGY = "technology";
    private static final String LANGUAGE = "en";

    public ArticlesRepository(ArticleDatabase articleDatabase, NewsInterface newsInterface, TechAppApplication appApplication) {
        mArticleDatabase = articleDatabase;
        mNewsInterface = newsInterface;
        mApplication = appApplication;
    }

    public LiveData<List<Article>> getArticles() {
        fetchData();
        return mArticleDatabase.articleDao().getArticles();
    }

    public void fetchData() {
        mNewsInterface.getTopNews(mApplication.getResources().getString(R.string.api_key), TECHNOLOGY, LANGUAGE).enqueue(
                new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                        if (response.code() == 200 && response.body().getStatus().equals("ok")) {
                            List<Article> articlesList = new ArrayList<>();

                            for (inc.maprace.techapp.retrofit.model.Article article : response.body().getArticles()) {
                                articlesList.add(new Article(article));
                            }

                            mArticleDatabase.articleDao().emptyTable();
                            mArticleDatabase.articleDao().addNews(articlesList);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        // Handle on failure
                    }
                }
        );
    }


    public void fetchData(String searchString) {
        mNewsInterface.getTopNews(mApplication.getResources().getString(R.string.api_key), TECHNOLOGY, searchString, LANGUAGE).enqueue(
                new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                        if (response.code() == 200 && response.body().getStatus().equals("ok")) {
                            List<Article> articlesList = new ArrayList<>();

                            for (inc.maprace.techapp.retrofit.model.Article article : response.body().getArticles()) {
                                articlesList.add(new Article(article));
                            }

                            mArticleDatabase.articleDao().emptyTable();
                            mArticleDatabase.articleDao().addNews(articlesList);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        // Handle on failure
                    }
                }
        );
    }

    public void fetchDataByCategoty (String category) {
        mNewsInterface.getTopNews(mApplication.getResources().getString(R.string.api_key), category, LANGUAGE).enqueue(
                new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                        if (response.code() == 200 && response.body().getStatus().equals("ok")) {
                            List<Article> articlesList = new ArrayList<>();

                            for (inc.maprace.techapp.retrofit.model.Article article : response.body().getArticles()) {
                                articlesList.add(new Article(article));
                            }

                            mArticleDatabase.articleDao().emptyTable();
                            mArticleDatabase.articleDao().addNews(articlesList);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        // Handle on failure
                    }
                }
        );
    }


}
