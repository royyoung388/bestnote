package ypc.world.bestnote;

import android.app.Application;

/**
 * Created by RoyYoung on 2017/12/28.
 */

public class MyApplication extends Application {

    private static MyApplication mApp;


    public static MyApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
