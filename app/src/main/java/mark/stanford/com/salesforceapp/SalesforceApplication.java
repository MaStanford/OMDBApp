package mark.stanford.com.salesforceapp;

import android.app.Application;

import mark.stanford.com.salesforceapp.data.DataObservable;

public class SalesforceApplication extends Application {
    DataObservable dataObservable;

    @Override
    public void onCreate() {
        super.onCreate();

        dataObservable = DataObservable.getInstance();
    }

    public DataObservable getDataObservable() {
        return dataObservable;
    }
}
