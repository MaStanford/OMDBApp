package mark.stanford.com.salesforceapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mark.stanford.com.salesforceapp.models.Movie;

import java.util.List;

public class FavoriteRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteRecyclerViewAdapter.ViewHolder> {

    private List<Movie> movieList;

    public FavoriteRecyclerViewAdapter(List<Movie> items) {
        movieList = items;
    }

    @Override
    public FavoriteRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_favorite, parent, false);
        return new FavoriteRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FavoriteRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = movieList.get(position);
        holder.title.setText(movieList.get(position).title);
        holder.year.setText(movieList.get(position).year);
        holder.director.setText(movieList.get(position).director);
        holder.plot.setText(movieList.get(position).plot);

        Picasso.get().load(movieList.get(position).poster).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void notifyDataSetChanged(List<Movie> favoritesList) {
        this.movieList = favoritesList;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView year;
        public final TextView director;
        public final TextView plot;
        public final ImageView poster;
        public Movie mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
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
