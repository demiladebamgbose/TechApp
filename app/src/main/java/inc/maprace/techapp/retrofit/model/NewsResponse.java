package inc.maprace.techapp.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by demiladebamgbose on 12/11/17.
 */

public class NewsResponse {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("articles")
    List<Article> articles;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
