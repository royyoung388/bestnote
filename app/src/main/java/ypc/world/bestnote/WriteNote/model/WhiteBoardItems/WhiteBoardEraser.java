package ypc.world.bestnote.WriteNote.model.WhiteBoardItems;

import android.content.Context;

import ypc.world.bestnote.Config;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class WhiteBoardEraser extends WhiteBoardObject{

    private WhiteBoardEraser(Context context)
    {
        super(context);
    }


    private static WhiteBoardEraser eraserInstance = null;


    public static WhiteBoardEraser getInstance(Context context) {
        if (eraserInstance == null) {
            synchronized (WhiteBoardPen.class) {
                if (eraserInstance == null) {
                    eraserInstance = new WhiteBoardEraser(context);
                    eraserInstance.setAlpha(255);
                    eraserInstance.setSize(20);
                }
            }
        }
        return eraserInstance;
    }
}
