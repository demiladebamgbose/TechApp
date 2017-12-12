package inc.maprace.techapp.retrofit;

import inc.maprace.techapp.retrofit.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by demiladebamgbose on 12/11/17.
 */

public interface NewsInterface {
    @GET("top-headlines")
    Call<NewsResponse> getTopNews (@Query("apiKey") String apiKey, @Query("category") String category);

    @GET("top-headlines")
    Call<NewsResponse> getTopNews (@Query("apiKey") String apiKey, @Query("category") String category, @Query("q") String searchString);

}
