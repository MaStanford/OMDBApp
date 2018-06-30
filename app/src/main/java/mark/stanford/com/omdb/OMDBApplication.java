package mark.stanford.com.omdb;

import android.app.Application;

/**
 * I originally held some singletons here for data and persitance but decided against it
 */
public class OMDBApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}