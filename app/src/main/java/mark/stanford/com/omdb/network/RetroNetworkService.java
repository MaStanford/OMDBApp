package mark.stanford.com.omdb.network;

import io.reactivex.Observable;
import mark.stanford.com.omdb.models.MovieSearchResultWrapper;
import mark.stanford.com.omdb.models.Movie;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroNetworkService {
    String BASE_URI = "http://www.omdbapi.com/";
    String API_KEY = "c02b6340";

    @GET("?apikey=" + API_KEY)
    Observable<Movie> getMovie(@Query("i") String id);

    @GET("?apikey=" + API_KEY)
    Observable<MovieSearchResultWrapper> searchMovies(@Query("s") String searchTerm);

    @GET("?apikey=" + API_KEY)
    Observable<MovieSearchResultWrapper> searchMoviesPage(@Query("s") String searchTerm, @Query("page") int page);
}
