package ypc.world.bestnote.WriteNote.model.DrawInfos;

import android.graphics.Canvas;
import android.graphics.Path;

/**
 * Created by Administrator on 2017/12/30 0030.
 */


public class PathDrawingInfo extends DrawingInfo {

    Path path;
    public void setPath(Path _path) {
        this.path = _path;
    }


    public Path getPath() {
        return this.path;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }
}
