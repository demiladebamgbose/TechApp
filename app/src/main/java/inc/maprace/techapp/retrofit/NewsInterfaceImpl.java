package inc.maprace.techapp.retrofit;

import inc.maprace.techapp.retrofit.model.NewsResponse;
import retrofit2.Call;

/**
 * Created by demiladebamgbose on 12/11/17.
 */

public class NewsInterfaceImpl implements NewsInterface {

    @Override
    public Call<NewsResponse> getTopNews(String apiKey, String category) {
        return getTopNews(apiKey, category);
    }

}
