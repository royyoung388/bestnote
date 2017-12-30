package ypc.world.bestnote.WriteNote.model.WhiteBoardItems;

import android.graphics.Color;

/**
 * Created by Administrator on 2017/12/30 0030.
 * super class for
 * @see WhiteBoardPen
 */

public class WhiteBoardObject {

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    //大小（粗细）
    private double size;
    //颜色
    private Color color;
    //透明度
    private double alpha;

    public WhiteBoardObject()
    {
        this.size = 20;
        this.color =
    }

}
