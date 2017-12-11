package inc.maprace.techapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Insert;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import inc.maprace.techapp.R;
import inc.maprace.techapp.TechAppApplication;
import inc.maprace.techapp.adapters.NewsAdapter;
import inc.maprace.techapp.viewmodels.MainViewModel;
import inc.maprace.techapp.viewmodels.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory mVmFactory;
    private MainViewModel mainViewModel;
    private NewsAdapter mNewsAdapter;

    @BindView(R.id.recycler_view) RecyclerView mNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((TechAppApplication) getApplication()).getAppComponent().inject(this);

        mainViewModel = ViewModelProviders.of(this, mVmFactory).get(MainViewModel.class);

        mNewsAdapter = new NewsAdapter(this);

        mNewsList.setLayoutManager(new LinearLayoutManager(this));
        mNewsList.setAdapter(mNewsAdapter);


        mainViewModel.getArticles().observe(this, (articles -> {
            mNewsAdapter.setmArticles(articles);
        }));

    }
}
