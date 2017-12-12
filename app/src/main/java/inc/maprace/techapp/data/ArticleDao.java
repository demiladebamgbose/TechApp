package inc.maprace.techapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by demiladebamgbose on 12/11/17.
 */

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM " + Article.TABLE_NAME)
    LiveData<List<Article>> getArticles();

    @Query("SELECT * FROM " + Article.TABLE_NAME + " WHERE id LIKE :id")
    Article getArticle(String id);

    @Insert(onConflict = REPLACE)
    void addNews (List<Article> articles);


    @Query("DELETE FROM " + Article.TABLE_NAME)
    void emptyTable();
}
