package ypc.world.bestnote.WriteNote.model.WhiteBoardModel;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.List;

import ypc.world.bestnote.WriteNote.model.DrawInfos.DrawingInfo;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public interface IWhiteBoardModel {


    List<DrawingInfo> getDrawingList();

    List<DrawingInfo> getRemovedList();


    boolean canErase();


    //撤销 反撤销
    boolean canRedo();

    boolean canUndo();

    void Undo();

    void Redo();

    //清除
    void Clear();


    //保存
    void saveDrawingPath(Path cPath, Paint cPaint);
}
