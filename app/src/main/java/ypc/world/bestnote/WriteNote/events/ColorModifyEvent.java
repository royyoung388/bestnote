package ypc.world.bestnote.WriteNote.events;

/**
 * Created by Administrator on 2018/1/1 0001.
 */

public class ColorModifyEvent {

    private int color;
    public ColorModifyEvent(int color) {
        this.color = color;
    }


    public int getColor() {
        return this.color;
    }

}
