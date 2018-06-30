package mark.stanford.com.salesforceapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mark.stanford.com.salesforceapp.data.DataObservable;
import mark.stanford.com.salesforceapp.models.Movie;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A fragment representing a list of Items.
 * <p/>
 */
public class FavoriteFragment extends Fragment implements Observer {
    private MovieFragment.OnListFragmentInteractionListener mListener;
    private FavoriteRecyclerViewAdapter adapter;

    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
       DataObservable.getInstance(getContext()).addObserver(this);

        List<Movie> favorites = DataObservable.getInstance(getContext()).getFavoritesList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new FavoriteRecyclerViewAdapter(favorites, mListener);
            recyclerView.setAdapter(adapter);
            DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
            itemDecoration.setDrawable(getContext().getDrawable(R.drawable.hor_line));
            recyclerView.addItemDecoration(itemDecoration);
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MovieFragment.OnListFragmentInteractionListener) {
            mListener = (MovieFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        DataObservable.getInstance(getContext()).deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        adapter.notifyDataSetChanged(DataObservable.getInstance(getContext()).getFavoritesList());
    }
}
