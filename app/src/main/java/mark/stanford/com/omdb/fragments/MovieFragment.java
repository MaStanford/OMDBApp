package mark.stanford.com.omdb.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mark.stanford.com.omdb.adapters.MovieRecyclerViewAdapter;
import mark.stanford.com.omdb.network.OMDBApi;
import mark.stanford.com.omdb.network.NetworkUtils;
import mark.stanford.com.salesforceapp.R;
import mark.stanford.com.omdb.data.DataObservable;
import mark.stanford.com.omdb.models.Movie;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MovieFragment extends Fragment implements Observer{

    private OnListFragmentInteractionListener mListener;
    private MovieRecyclerViewAdapter adapter;
    private EditText et;
    private Disposable dispoable;
    private OMDBApi omdbAPI;

    private int totalPages = 0;
    private String searchTerm = "";
    public static final int ENTRIES_PER_PAGE = 10;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieFragment() {
    }

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MovieRecyclerViewAdapter(getContext(), new ArrayList<>(), mListener);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
        itemDecoration.setDrawable(getContext().getDrawable(R.drawable.hor_line));
        recyclerView.addItemDecoration(itemDecoration);

        et = view.findViewById(R.id.editText);
        et.setImeActionLabel("Search", KeyEvent.KEYCODE_SEARCH);
        et.setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_SEARCH){
                searchTerm = et.getText().toString();
                searchMovieTitle(searchTerm);
                hideKeyboardFrom(getContext(), et);
                return true;
            }
            return false;
        });

        view.findViewById(R.id.button).setOnClickListener(v -> {
            searchTerm = et.getText().toString();
            hideKeyboardFrom(getContext(), et);
            searchMovieTitle(searchTerm);
        });

        //Saved State handling
        if(savedInstanceState != null){
            searchTerm = savedInstanceState.getString("SearchTerm");
            if(searchTerm != null){
                searchMovieTitle(searchTerm);
            }
        }

        omdbAPI = new OMDBApi();

        DataObservable.getInstance(getContext()).addObserver(this);

        return view;
    }

    private void searchMovieTitle(String searchTerm) {
        if(!NetworkUtils.hasConnection(getContext())){
            Toast.makeText(getContext(), "Network Unavailable, search failed", Toast.LENGTH_LONG).show();
            return;
        }

        //Clear the current results
        DataObservable.getInstance(getContext()).clearMovies();

        //The big fun chain of operators in rxjava that replaces a couple lines of code using stream, observables and callbacks.
        omdbAPI.searchMovieTitle(searchTerm)
                .subscribeOn(Schedulers.newThread())
                .map((searchResult) -> searchResult.imdbID)
                .map((id) -> omdbAPI.fetchMovie(id))
                .flatMap(x -> x)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        dispoable = d;
                    }

                    @Override
                    public void onSuccess(List<Movie> movies) {
                        addMovies(movies);
                        notifyData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        displayError(e);
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("SearchTerm", searchTerm);
    }

    private void displayError(Throwable e) {
        Toast.makeText(getContext(), "Error fetching movies, try again.", Toast.LENGTH_LONG).show();
        Log.e("MARK", "displayError: " + e.getMessage());
    }

    private void addMovies(List<Movie> movies) {
        DataObservable.getInstance(getContext()).addMovies(movies);
    }

    private void addMovie(Movie movie) {
        DataObservable.getInstance(getContext()).addMovie(movie);
    }

    private void notifyData() {
        DataObservable.getInstance(getContext()).notifyObservers();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        DataObservable.getInstance(getContext()).deleteObserver(this);
        if(dispoable != null) {
            dispoable.dispose();
        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(adapter != null)
            adapter.notifyDataSetChanged(((DataObservable)o).getMovieList(), ((DataObservable)o).getFavoritesList());
    }

    public interface OnListFragmentInteractionListener {
        void onListItemClicked(Movie movie);
        void onListFavoriteClicked(Movie movie);
    }
}
