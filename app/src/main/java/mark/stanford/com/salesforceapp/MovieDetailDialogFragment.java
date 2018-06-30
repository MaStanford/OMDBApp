package mark.stanford.com.salesforceapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mark.stanford.com.salesforceapp.data.DataObservable;
import mark.stanford.com.salesforceapp.models.Movie;

public class MovieDetailDialogFragment extends DialogFragment {

    String movieID;

    public static MovieDetailDialogFragment newInstance(String id){
        MovieDetailDialogFragment dialogFragment = new MovieDetailDialogFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("id", id);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieID = getArguments().getString("id");
        setStyle(STYLE_NO_FRAME, R.style.AppTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_movie_details, container, false);

        Movie movie = null;
        for(Movie item : DataObservable.getInstance(getContext()).getFavoritesList()){
            if(item.imdbID.equals(movieID)){
                movie = item;
                break;
            }
        }

        if(movie != null){
            getDialog().setTitle(movie.title);
            Picasso.get().load(movie.poster).into((ImageView) v.findViewById(R.id.iv_detail));
            ((TextView)v.findViewById(R.id.textView)).setText( "Year:      " + movie.year);
            ((TextView)v.findViewById(R.id.textView2)).setText("Genre:     " + movie.genre);
            ((TextView)v.findViewById(R.id.textView3)).setText("Metascore: " + movie.metascore);
            ((TextView)v.findViewById(R.id.textView4)).setText("Country:   " + movie.country);
            ((TextView)v.findViewById(R.id.textView5)).setText("Language:  " + movie.language);
            ((TextView)v.findViewById(R.id.textView6)).setText("Awards:    " + movie.awards);
            ((TextView)v.findViewById(R.id.textView7)).setText("Box Office:" + movie.boxOffice);
            ((TextView)v.findViewById(R.id.textView8)).setText("Actors:    " + movie.actors);
            ((TextView)v.findViewById(R.id.textView9)).setText("Plot:      " + movie.plot);
        }else{
            getDialog().setTitle("Error!!!");
        }

        return v;
    }

}
