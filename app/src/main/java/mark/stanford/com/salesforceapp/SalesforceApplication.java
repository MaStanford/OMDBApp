package mark.stanford.com.salesforceapp;

import android.app.Application;

/**
 * I originally held some singletons here for data and persitance but decided against it
 */
public class SalesforceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}