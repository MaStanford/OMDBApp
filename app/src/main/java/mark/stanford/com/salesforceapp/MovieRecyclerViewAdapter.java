package mark.stanford.com.salesforceapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mark.stanford.com.salesforceapp.MovieFragment.OnListFragmentInteractionListener;
import mark.stanford.com.salesforceapp.models.Movie;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Movie} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private List<Movie> movieList;
    private List<Movie> favoritesList;
    private final OnListFragmentInteractionListener mListener;

    public MovieRecyclerViewAdapter(Context context, List<Movie> items, OnListFragmentInteractionListener listener) {
        this.context = context;
        movieList = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = movieList.get(position);
        holder.title.setText(movieList.get(position).title);
        holder.year.setText(movieList.get(position).year);
        holder.director.setText(movieList.get(position).director);
        holder.plot.setText(movieList.get(position).plot);
        holder.checkBox.setChecked(favoritesList.contains(movieList.get(position)));
        Picasso.get().load(movieList.get(position).poster).into(holder.poster);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListItemClicked(holder.mItem);
                }
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFavoriteClicked(holder.mItem);
                }
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CheckBox checkBox;
        public final TextView title;
        public final TextView year;
        public final TextView director;
        public final TextView plot;
        public final ImageView poster;
        public Movie mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            checkBox = view.findViewById(R.id.checkBox);
            title = (TextView) view.findViewById(R.id.title);
            year = (TextView) view.findViewById(R.id.year);
            director = (TextView) view.findViewById(R.id.director);
            plot = (TextView) view.findViewById(R.id.plot);
            poster = view.findViewById(R.id.poster);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
