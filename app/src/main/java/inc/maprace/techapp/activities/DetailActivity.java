package inc.maprace.techapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import inc.maprace.techapp.R;
import inc.maprace.techapp.data.Article;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.image) ImageView newsImage;
    @BindView(R.id.news_body) TextView newsBody;
    @BindView(R.id.source) TextView newsSource;
    @BindView(R.id.author) TextView newsAuthor;
    @BindView(R.id.date) TextView newsDate;
    @BindView(R.id.news_title) TextView newsTitle;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private Article article;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        article = intent.getParcelableExtra("Article");
        toolbar.setTitle(article.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);





        Glide.with(this).load(article.getUrlToImage()).into(newsImage);
        newsTitle.setText(article.getTitle());
        newsBody.setText(article.getDescription());
        newsSource.setText(article.getSourceName());
        newsAuthor.setText(article.getAuthor());
        newsDate.setText(article.getPublishedAt());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, article.getUrl());
            startActivity(Intent.createChooser(intent, "Share news via"));
        } catch(Exception e) {
            //e.toString();
        }

        return  true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        return true;
    }

    @OnClick(R.id.link_out)
    public void linkToNews(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
        startActivity(intent);
    }


}
