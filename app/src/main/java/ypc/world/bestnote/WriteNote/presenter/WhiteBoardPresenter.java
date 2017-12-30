package ypc.world.bestnote.WriteNote.presenter;

import java.util.ArrayList;
import java.util.List;

import ypc.world.bestnote.WriteNote.model.DrawInfos.DrawingInfo;
import ypc.world.bestnote.WriteNote.model.IWhiteBoardModel;
import ypc.world.bestnote.WriteNote.model.WhiteBoardModelImpl;
import ypc.world.bestnote.WriteNote.view.IWhiteBoardView;
import ypc.world.bestnote.WriteNote.view.WhiteBoardView;
/**
 * Created by RoyYoung on 2017/12/28.
 */

public class WhiteBoardPresenter {

    IWhiteBoardView view;
    IWhiteBoardModel model;

    public WhiteBoardPresenter(IWhiteBoardView _view) {
        this.view = _view;
        model = new WhiteBoardModelImpl();
    }


    public List<DrawingInfo> getDrawingList()
    {
        return model.getDrawingList();
    }

    public List<DrawingInfo> getRemovedList()
    {
        return model.getRemovedList();
    }

    public boolean canRedo() {
        return model.canRedo();
    }

    public boolean canUndo() {
        return model.canUndo();
    }

    //撤销
    public void Undo() {
        view.reDraw();
        model.Undo();
    }


    //重做
    public void Redo() {
        view.reDraw();
        model.Redo();
    }


    public void Clear() {
        model.Clear();
    }


    public void saveDrawingPath()
    {
        model.saveDrawingPath(view.getPath(), view.getPaint());
    }


    public boolean canErase() {
        return model.canErase();
    }
}



