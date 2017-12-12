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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


        //Set up toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Populate view
        article = intent.getParcelableExtra("Article");
        toolbar.setTitle(article.getTitle());
        Glide.with(this).load(article.getUrlToImage()).into(newsImage);
        newsTitle.setText(article.getTitle());
        newsBody.setText(article.getDescription());
        newsSource.setText(article.getSourceName());
        newsAuthor.setText(article.getAuthor());
        newsDate.setText(article.getPublishedAt());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(article.getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newsDate.setText((date == null) ? "not available" : date.toString());

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            try {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, article.getUrl());
                startActivity(Intent.createChooser(intent, "Share this news via: "));
            } catch(Exception e) {
                e.printStackTrace();
            }
            return  true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        return true;
    }

    @OnClick(R.id.link_out)
    public void linkToNews(View v) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", article.getUrl());
        startActivity(intent);
    }


}
