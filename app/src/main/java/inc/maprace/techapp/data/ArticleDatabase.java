package inc.maprace.techapp.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

/**
 * Created by demiladebamgbose on 12/11/17.
 */
@Database(entities = {Article.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase{
    public abstract ArticleDao articleDao();

}
