package mark.stanford.com.omdb.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import mark.stanford.com.omdb.fragments.MovieFragment;
import mark.stanford.com.omdb.models.Movie;
import mark.stanford.com.salesforceapp.R;

public class FavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MovieItemViewHolder> {

    private List<Movie> movieList;
    MovieFragment.OnListFragmentInteractionListener listener;

    public FavoriteRecyclerViewAdapter(List<Movie> items, MovieFragment.OnListFragmentInteractionListener mListener) {
        movieList = items;
        listener = mListener;
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
        holder.checkBox.setChecked(true);
        Picasso.get().load(movieList.get(position).poster).into(holder.poster);

        holder.mView.setOnClickListener(v -> {
            if (null != listener) {
                listener.onListItemClicked(holder.mItem);
            }
        });

        holder.checkBox.setOnClickListener(v -> {
            if (null != listener) {
                listener.onListFavoriteClicked(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void notifyDataSetChanged(List<Movie> favoritesList) {
        this.movieList = favoritesList;
        this.notifyDataSetChanged();
    }
}
