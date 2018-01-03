package ypc.world.bestnote.WriteNote.view;

import android.graphics.Paint;
import android.graphics.Path;

import ypc.world.bestnote.WriteNote.ui.WhiteBoardView;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public interface IWhiteBoardView {

    void reDraw();


    Path getPath();


    Paint getPaint();


    void setPenColor(int color);


    void setPenSize(float size);


    void setEraserSize(float size);


    void setMode(WhiteBoardView.Mode mode);
}
