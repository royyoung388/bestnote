package ypc.world.bestnote.WriteNote.model.WhiteBoardItems;

import android.content.Context;
import android.view.View;

import ypc.world.bestnote.Config;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class WhiteBoardPen extends WhiteBoardObject {

    private WhiteBoardPen(Context context) {
        super(context);
    }

    private static WhiteBoardPen penInstance = null;

    public static WhiteBoardPen getInstance(Context context) {
        if (penInstance == null) {
            synchronized (WhiteBoardPen.class) {
                if (penInstance == null) {
                    penInstance = new WhiteBoardPen(context);
                    penInstance.setAlpha(255);
                    penInstance.setColor(Config.colors[0]);
                    penInstance.setSize(20);
                }
            }
        }
        return penInstance;
    }


}
