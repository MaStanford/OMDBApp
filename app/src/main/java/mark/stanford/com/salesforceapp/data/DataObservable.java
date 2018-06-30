package mark.stanford.com.salesforceapp.data;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import mark.stanford.com.salesforceapp.models.Movie;
import mark.stanford.com.salesforceapp.models.Search;

public class DataObservable extends Observable {

    //List of movies
    private List<Movie> movieList = new ArrayList<>();

    //List of items returned in the search
    private List<Search> searchList = new ArrayList<>();

    //List of Favorites
    private List<Movie> favoritesList = new ArrayList<>();

    /**
     * I was going to use the search results as the data for persisting and passing to the adapters,
     * This mapping would've been used for details.  If val not there, then make network request for val.
     * This could've let me do lazy fetching of the movie object which is much larger than the search object.
     * In the onBind or something I would've made a async task or background task to fetch the data
     *  and populate it when it returns.
     */
    private Map<Search, Movie> searchToMovieMap = new HashMap<>();

    private static DataObservable INSTANCE;

    private Context context;

    private DataObservable(Context context){
        this.context = context;
        ArrayList<Movie> favs = (ArrayList<Movie>) FileUtils.loadObject(context, "FAVS", new TypeToken<ArrayList<Movie>>(){}.getType());
        if(favs != null){
            this.favoritesList = favs;
            this.setChanged();
        }
    }

    public static DataObservable getInstance(Context context){
        if(INSTANCE == null) {
            synchronized (DataObservable.class) {
                INSTANCE = new DataObservable(context);
            }
        }
        return INSTANCE;
    }

    public void addMovie(Movie movie){
        this.setChanged();
        movieList.add(movie);
    }

    public void addMovies(List<Movie> movies){
        this.setChanged();
        movieList.addAll(movies);
    }

    public void clearMovies(){
        this.setChanged();
        movieList.clear();
    }

    public List<Movie> getMovieList() {
        return Collections.unmodifiableList(movieList);
    }

    public void addFavorite(Movie movie){
        this.setChanged();
        favoritesList.add(movie);
        new Thread(() -> {
            FileUtils.saveObject(context, "FAVS", this.favoritesList);
        });
    }

    public void addFavorites(List<Movie> movies){
        this.setChanged();
        favoritesList.addAll(movies);
        new Thread(() -> {
            FileUtils.saveObject(context, "FAVS", this.favoritesList);
        });
    }

    public void removeFavorite(Movie movie){
        this.favoritesList.remove(movie);
        new Thread(() -> {
            FileUtils.saveObject(context, "FAVS", "");
        });
    }

    public void clearFavorites(){
        this.setChanged();
        favoritesList.clear();
        new Thread(() -> {
            FileUtils.saveObject(context, "FAVS", "");
        });
    }

    public List<Movie> getFavoritesList() {
        return Collections.unmodifiableList(favoritesList);
    }

    public List<Search> getSearchList() {
        return Collections.unmodifiableList(searchList);
    }

    public Map<Search, Movie> getSearchToMovieMap() {
        return Collections.unmodifiableMap(searchToMovieMap);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }
}
