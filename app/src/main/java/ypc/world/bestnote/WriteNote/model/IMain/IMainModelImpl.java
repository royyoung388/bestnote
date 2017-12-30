package ypc.world.bestnote.WriteNote.model.IMain;

import ypc.world.bestnote.WriteNote.presenter.WhiteBoardPresenter;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class IMainModelImpl implements IMainModel {


    WhiteBoardPresenter whiteBoardPresenter;
    public IMainModelImpl(WhiteBoardPresenter presenter) {
        this.whiteBoardPresenter = presenter;
    }


    @Override
    public void Undo() {
        whiteBoardPresenter.Undo();
    }


    @Override
    public void Redo() {
        whiteBoardPresenter.Undo();
    }
}
