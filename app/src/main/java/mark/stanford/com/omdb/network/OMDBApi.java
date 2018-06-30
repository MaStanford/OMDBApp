package mark.stanford.com.omdb.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import mark.stanford.com.omdb.models.Movie;
import mark.stanford.com.omdb.models.Search;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OMDBApi {
    private RetroNetworkService networkService;
    private Retrofit retrofit;

    public OMDBApi(){
        retrofit = buildRetrofit(new OkHttpClient());
        networkService = buildNetworkService();
    }

    private Retrofit buildRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(RetroNetworkService.BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private RetroNetworkService buildNetworkService(){
        return retrofit.create(RetroNetworkService.class);
    }

    public Observable<Search> searchMovieTitle(String title){
        return networkService.searchMovies(title)
                .flatMapIterable((x) -> x.search) ;}

    public Observable<Search> searchMovieTitleWithPage(String title, int page){
        return networkService.searchMoviesPage(title, page)
                .flatMapIterable((x) -> x.search) ;
    }

    public Observable<Movie> fetchMovie(String movieID){
        return  networkService.getMovie(movieID);
    }
}
