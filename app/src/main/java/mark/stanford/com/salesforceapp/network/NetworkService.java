package mark.stanford.com.salesforceapp.network;

import io.reactivex.Observable;
import mark.stanford.com.salesforceapp.models.Movie;
import mark.stanford.com.salesforceapp.models.MovieSearchResultWrapper;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {
    String BASE_URI = "http://www.omdbapi.com/";
    String API_KEY = "c02b6340";

    @GET("?apikey=" + API_KEY)
    Observable<Movie> getMovie(@Query("i") String id);

    @GET("?apikey=" + API_KEY)
    Observable<MovieSearchResultWrapper> searchMovies(@Query("s") String searchTerm);

    @GET("?apikey=" + API_KEY)
    Observable<MovieSearchResultWrapper> searchMoviesPage(@Query("s") String searchTerm, @Query("page") int page);
}
