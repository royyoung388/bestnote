package ypc.world.bestnote.WriteNote;

import ypc.world.bestnote.WriteNote.presenter.MainPresenter;
import ypc.world.bestnote.WriteNote.presenter.WhiteBoardPresenter;

/**
 * Created by Administrator on 2017/12/31 0031.
 */

public class Manager {

    private static WhiteBoardPresenter whiteBoardPresenter;

    private static MainPresenter mainPresenter;
    public static void registerWhiteBoardPresenter(WhiteBoardPresenter presenter) {
        whiteBoardPresenter = presenter;
    }


    public static WhiteBoardPresenter getWhiteBoardPresenter() {
        return whiteBoardPresenter;
    }


    public static void registerMainPresenter(MainPresenter presenter) {
        mainPresenter = presenter;
    }

    public static MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
