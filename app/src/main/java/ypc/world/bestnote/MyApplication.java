package ypc.world.bestnote;

import android.app.Application;

import ypc.world.bestnote.WriteNote.Manager;
import ypc.world.bestnote.WriteNote.presenter.MainPresenter;
import ypc.world.bestnote.WriteNote.presenter.WhiteBoardPresenter;

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
        Manager.registerMainPresenter(new MainPresenter());
    }


}
