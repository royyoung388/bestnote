package ypc.world.bestnote.WriteNote.model.WhiteBoardItems;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import ypc.world.bestnote.Config;

/**
 * Created by Administrator on 2017/12/30 0030.
 * super class for
 * @see WhiteBoardPen
 */

public class WhiteBoardObject {


    //大小（粗细）
    private double size;

    //颜色
    private int color;

    //透明度
    private double alpha;


    public WhiteBoardObject(Context context)
    {
        this.size = 20;
        this.color = ContextCompat.getColor(context, Config.colors[0]);
    }


    public double getSize() {
        return size;
    }


    public void setSize(double size) {
        this.size = size;
    }


    public int getColor() {
        return color;
    }


    public void setColor(int color) {
        this.color = color;
    }


    public double getAlpha() {
        return alpha;
    }


    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }


}
