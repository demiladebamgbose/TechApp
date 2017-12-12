package inc.maprace.techapp.activities;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import inc.maprace.techapp.R;
import inc.maprace.techapp.TechAppApplication;
import inc.maprace.techapp.adapters.NewsAdapter;
import inc.maprace.techapp.network.ConnectivityChangeReceiver;
import inc.maprace.techapp.viewmodels.MainViewModel;
import inc.maprace.techapp.viewmodels.ViewModelFactory;

public class MainActivity extends AppCompatActivity implements ConnectivityChangeReceiver.ConnectionReceiverListener {

    @Inject
    ViewModelFactory mVmFactory;
    private MainViewModel mainViewModel;
    private NewsAdapter mNewsAdapter;

    @BindView(R.id.search_text) EditText mSearchText;
    @BindView(R.id.recycler_view) RecyclerView mNewsList;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.search) LinearLayout mEditText;
    @BindView(R.id.delete_search) View deleteSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((TechAppApplication) getApplication()).getAppComponent().inject(this);
        ((TechAppApplication) getApplication()).setConnectivityChangeReciever(this);

        mainViewModel = ViewModelProviders.of(this, mVmFactory).get(MainViewModel.class);


        //Set up toolbar
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);


        // Set up adapter
        mNewsAdapter = new NewsAdapter(this);

        mNewsList.setLayoutManager(new LinearLayoutManager(this));
        mNewsList.setAdapter(mNewsAdapter);

        registerObservers();


        // initial network indicator
        mainViewModel.setRefreshIndicator(ConnectivityChangeReceiver.isConnected());

    }

    private void registerObservers() {

        mainViewModel.getArticles().observe(this, (articles -> {
            mNewsAdapter.setmArticles(articles);
        }));

        mainViewModel.getRefreshIndicator().observe(this, (bool) -> {
            if (bool && bool !=null)
                mainViewModel.getArticles();

            Toast.makeText(this, "Internet connection unavailable, plse check your neteork and try again", Toast.LENGTH_LONG).show();
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                mEditText.setVisibility(mEditText.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                break;
            case R.id.politics:
                mainViewModel.getNewsByCategory("politics");
                break;
            case R.id.business:
                mainViewModel.getNewsByCategory("business");
                break;
            case R.id.sport:
                mainViewModel.getNewsByCategory("sport");
                break;
            case R.id.general:
                mainViewModel.getNewsByCategory("general");
                break;
            case R.id.music:
                mainViewModel.getNewsByCategory("music");
                break;
            case R.id.technology:
                mainViewModel.getNewsByCategory("technology");
                break;
            default:
                super.onOptionsItemSelected(item);

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


   @OnClick(R.id.search_btn)
   public void search () {
        if (mSearchText.getText() != null)
            mainViewModel.getNews(mSearchText.getText().toString());

   }

   @OnClick(R.id.delete_search)
   public void clearSearch() {
       mSearchText.setText("");
       mEditText.setVisibility(View.GONE);
       mainViewModel.getNews();

   }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

        if(mainViewModel.getRefreshIndicator().getValue() != isConnected )
            mainViewModel.setRefreshIndicator(true);
    }






}
