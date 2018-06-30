package mark.stanford.com.omdb.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import mark.stanford.com.omdb.fragments.MovieFragment;
import mark.stanford.com.salesforceapp.R;
import mark.stanford.com.omdb.models.Movie;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Movie} and makes a call to the
 * specified {@link MovieFragment.OnListFragmentInteractionListener}.
 */
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieItemViewHolder> {

    private final Context context;
    private List<Movie> movieList;
    private List<Movie> favoritesList;
    private final MovieFragment.OnListFragmentInteractionListener mListener;

    public MovieRecyclerViewAdapter(Context context, List<Movie> items, MovieFragment.OnListFragmentInteractionListener listener) {
        this.context = context;
        movieList = items;
        mListener = listener;
    }

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movie, parent, false);
        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieItemViewHolder holder, int position) {
        holder.mItem = movieList.get(position);
        holder.title.setText(movieList.get(position).title);
        holder.year.setText(movieList.get(position).year);
        holder.director.setText(movieList.get(position).director);
        holder.plot.setText(movieList.get(position).plot);
        holder.checkBox.setChecked(favoritesList.contains(movieList.get(position)));
        Picasso.get().load(movieList.get(position).poster).into(holder.poster);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onListItemClicked(holder.mItem);
            }
        });

        holder.checkBox.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onListFavoriteClicked(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void notifyDataSetChanged(List<Movie> movies, List<Movie> favorites){
        this.movieList = movies;
        this.favoritesList = favorites;
        super.notifyDataSetChanged();
    }
}
