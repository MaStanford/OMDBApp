package mark.stanford.com.salesforceapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mark.stanford.com.salesforceapp.models.Movie;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A fragment representing a list of Items.
 * <p/>
 */
public class FavoriteFragment extends Fragment implements Observer {

    private FavoriteRecyclerViewAdapter adapter;

    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance(int columnCount) {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new FavoriteRecyclerViewAdapter(new ArrayList<Movie>());
            recyclerView.setAdapter(adapter);
        }

        ((SalesforceApplication)getActivity().getApplication()).getDataObservable().addObserver(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void update(Observable o, Object arg) {
        adapter.notifyDataSetChanged();
    }
}
