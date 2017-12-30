package ypc.world.bestnote.WriteNote.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ypc.world.bestnote.WriteNote.model.DrawInfos.DrawingInfo;
import ypc.world.bestnote.WriteNote.model.WhiteBoardModelImpl;
import ypc.world.bestnote.WriteNote.presenter.WhiteBoardPresenter;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class WhiteBoardView extends View implements IWhiteBoardView{


    private WhiteBoardPresenter presenter;

    private Paint mPaint;
    private Path mPath;
    private float mLastX;
    private float mLastY;


    private Xfermode mClearMode;
    private float mDrawSize;
    private float mEraserSize;
    private Bitmap mBufferBitmap;
    private Canvas mBufferCanvas;


    @Override
    public Paint getPaint() {
        return this.mPaint;
    }


    @Override
    public Path getPath() {
        return this.mPath;
    }

    private void initBuffer() {
        mBufferBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mBufferCanvas = new Canvas(mBufferBitmap);
    }

    public enum Mode {
        DRAW,
        ERASER
    }

    private Mode mMode = Mode.DRAW;

    public WhiteBoardView(Context context) {
        this(context, null);
    }

    public WhiteBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置图像缓存
        setDrawingCacheEnabled(true);
        init();
    }


    private void init() {
        presenter = new WhiteBoardPresenter(this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setFilterBitmap(true);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mDrawSize = 20;
        mEraserSize = 40;
        mPaint.setStrokeWidth(mDrawSize);
        mPaint.setColor(0XFF000000);

        //设置图像混合模式为Clear
        mClearMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    }


    public Mode getMode() {
        return mMode;
    }


    public void setMode(Mode mode) {
        if (mode != mMode) {
            mMode = mode;
            if (mMode == Mode.DRAW) {
                //绘图模式
                mPaint.setXfermode(null);
                mPaint.setStrokeWidth(mDrawSize);
            } else {
                //橡皮擦模式
                mPaint.setXfermode(mClearMode);
                mPaint.setStrokeWidth(mEraserSize);
            }
        }
    }


    /**
     * 设置橡皮擦大小
     * @param size
     */
    public void setEraserSize(float size) {
        mEraserSize = size;
    }


    /**
     * 设置笔大小
     * @param size
     */
    public void setPenRawSize(float size) {
        mEraserSize = size;
    }


    /**
     * 设置笔颜色
     * @param color
     */
    public void setPenColor(int color) {
        mPaint.setColor(color);
    }


    /**
     * 设置笔透明度
     * @param alpha
     */
    public void setPenAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }


    //重新绘制
    @Override
    public void reDraw() {
        List<DrawingInfo> mDrawingList = presenter.getRemovedList();
        if (mDrawingList != null) {
            mBufferBitmap.eraseColor(Color.TRANSPARENT);
            for (DrawingInfo drawingInfo : mDrawingList) {
                drawingInfo.draw(mBufferCanvas);
            }
            invalidate();
        }
    }


    public boolean canRedo() {
        return presenter.canRedo();

    }


    public boolean canUndo() {
        return presenter.canUndo();
    }


    public void clear() {
        if (mBufferBitmap != null) {
            presenter.Clear();
            mBufferBitmap.eraseColor(Color.TRANSPARENT);
            invalidate();
        }
    }


    public Bitmap buildBitmap() {
        Bitmap bm = getDrawingCache();
        Bitmap result = Bitmap.createBitmap(bm);
        destroyDrawingCache();
        return result;
    }


    private void saveDrawingPath() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBufferBitmap != null) {
            canvas.drawBitmap(mBufferBitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction() & MotionEvent.ACTION_MASK;
        final float x = event.getX();
        final float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                if (mPath == null) {
                    mPath = new Path();
                }
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.quadTo(mLastX, mLastY, (x + mLastX) / 2, (y + mLastY) / 2);
                if (mBufferBitmap == null) {
                    initBuffer();
                }
                if (mMode == Mode.ERASER && !mCanEraser) {
                    break;
                }
                mBufferCanvas.drawPath(mPath, mPaint);
                invalidate();
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                if (mMode == Mode.DRAW || mCanEraser) {
                    saveDrawingPath();
                }
                mPath.reset();
                break;
        }
        return true;
    }

}
