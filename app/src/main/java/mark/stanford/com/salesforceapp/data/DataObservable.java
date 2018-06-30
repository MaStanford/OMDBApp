package mark.stanford.com.salesforceapp.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import mark.stanford.com.salesforceapp.models.Movie;

public class DataObservable extends Observable {

    private List<Movie> movieList = new ArrayList<>();
    private List<Movie> favoritesList = new ArrayList<>();

    private static final DataObservable INSTANCE = new DataObservable();

    private DataObservable(){

    }

    public static DataObservable getInstance(){
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
    }

    public void addFavorites(List<Movie> movies){
        this.setChanged();
        favoritesList.addAll(movies);
    }

    public void clearFavorites(){
        this.setChanged();
        favoritesList.clear();
    }

    public List<Movie> getFavoritesList() {
        return Collections.unmodifiableList(favoritesList);
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
