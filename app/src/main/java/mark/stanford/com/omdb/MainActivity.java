package mark.stanford.com.omdb;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mark.stanford.com.omdb.fragments.FavoriteFragment;
import mark.stanford.com.omdb.fragments.MovieDetailDialogFragment;
import mark.stanford.com.omdb.fragments.MovieFragment;
import mark.stanford.com.salesforceapp.R;
import mark.stanford.com.omdb.data.DataObservable;
import mark.stanford.com.omdb.models.Movie;

public class MainActivity extends AppCompatActivity implements MovieFragment.OnListFragmentInteractionListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClicked(Movie movie) {
        showDetailDialog(movie.imdbID);
    }

    @Override
    public void onListFavoriteClicked(Movie movie) {
        if(DataObservable.getInstance(this).getFavoritesList().contains(movie)){
            DataObservable.getInstance(this).removeFavorite(movie);
        }else{
            DataObservable.getInstance(this).addFavorite(movie);
        }
        DataObservable.getInstance(this).notifyObservers();
    }

    private void showDetailDialog(String id){
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        android.support.v4.app.Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        MovieDetailDialogFragment newFragment = MovieDetailDialogFragment.newInstance(id);
        newFragment.show(ft, "dialog");
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return MovieFragment.newInstance();
                case 1:
                    return FavoriteFragment.newInstance();
                default:
                    return MovieFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
