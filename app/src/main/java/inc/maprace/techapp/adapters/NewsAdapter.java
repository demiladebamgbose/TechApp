package inc.maprace.techapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import inc.maprace.techapp.R;
import inc.maprace.techapp.activities.DetailActivity;
import inc.maprace.techapp.data.Article;

/**
 * Created by demiladebamgbose on 12/11/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Article> mArticles = new ArrayList<>();
    private  Context mContext;

    public NewsAdapter (Context context) {
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = mArticles.get(position);
        holder.desription.setText(article.getAuthor());
        holder.title.setText(article.getTitle());
        Glide.with(mContext).load(article.getUrlToImage()).into(holder.newsImage);

        holder.articleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("Article", article);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }


    public void setmArticles(List<Article> mArticles) {
        this.mArticles = mArticles;
        notifyDataSetChanged();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public @BindView(R.id.title) TextView title;
        public @BindView(R.id.description) TextView desription;
        public @BindView(R.id.news_image) ImageView newsImage;
        public @BindView(R.id.article_card) LinearLayout articleCard;
        Context context;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

//        @OnClick(R.id.see_more)
//        public void onSeeMoreClicked(View v) {
//            Intent intent = new Intent(context, DetailActivity.class);
//            context.startActivity(intent);
//        }
    }
}
