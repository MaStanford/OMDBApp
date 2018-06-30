package mark.stanford.com.omdb.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import mark.stanford.com.omdb.models.Movie;
import mark.stanford.com.salesforceapp.R;

public class MovieItemViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final CheckBox checkBox;
    public final TextView title;
    public final TextView year;
    public final TextView director;
    public final TextView plot;
    public final ImageView poster;
    public Movie mItem;

    public MovieItemViewHolder(View view) {
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
