package ypc.world.bestnote.WriteNote.model.DrawInfos;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public abstract class DrawingInfo {
    Paint paint;

    public void setPaint(Paint pnt) {
        this.paint = pnt;
    }


    public Paint getPaint() {
        return this.paint;
    }

    public abstract void draw(Canvas canvas);
}