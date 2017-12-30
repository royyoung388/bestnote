package ypc.world.bestnote.WriteNote.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import ypc.world.bestnote.WriteNote.model.DrawInfos.DrawingInfo;
import ypc.world.bestnote.WriteNote.model.DrawInfos.PathDrawingInfo;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class WhiteBoardModelImpl implements IWhiteBoardModel{


    private boolean mCanEraser;
    private static final int MAX_CACHE_STEP = 20;
    private List<DrawingInfo> mDrawingList;
    private List<DrawingInfo> mRemovedList;
    private Callback mCallback;



    public interface Callback {
        void onUndoRedoStatusChanged();
    }


    public void setCallback(Callback callback) {
        mCallback = callback;
    }


    @Override
    public List<DrawingInfo> getDrawingList() {
        return this.mDrawingList;
    }

    @Override
    public List<DrawingInfo> getRemovedList() {
        return this.mRemovedList;
    }


    @Override
    public boolean canRedo() {
        return mRemovedList != null && mRemovedList.size() > 0;
    }


    @Override
    public boolean canUndo() {
        return mDrawingList != null && mDrawingList.size() > 0;
    }


    @Override
    public void Undo() {
        int size = mDrawingList == null? 0 : mDrawingList.size();
        if (size > 0) {
            DrawingInfo info = mDrawingList.remove(size - 1);
            if (mRemovedList == null) {
                mRemovedList = new ArrayList<>(MAX_CACHE_STEP);
            }
            if (size == 1) {
                mCanEraser = false;
            }
            mRemovedList.add(info);

            if (mCallback != null) {
                mCallback.onUndoRedoStatusChanged();
            }
        }
    }


    @Override
    public void Redo() {
        int size = mRemovedList == null ? 0 : mRemovedList.size();
        if (size > 0) {
            DrawingInfo info = mRemovedList.remove(size - 1);
            mDrawingList.add(info);
            mCanEraser = true;
            if (mCallback != null) {
                mCallback.onUndoRedoStatusChanged();
            }
        }
    }


    @Override
    public void Clear() {
        if (mDrawingList != null) {
            mDrawingList.clear();
        }
        if (mRemovedList != null) {
            mRemovedList.clear();
        }
        mCanEraser = false;
        if (mCallback != null) {
            mCallback.onUndoRedoStatusChanged();
        }
    }


    public void saveDrawingPath() {
        if (mDrawingList == null) {
            mDrawingList = new ArrayList<>(MAX_CACHE_STEP);
        } else if (mDrawingList.size() == MAX_CACHE_STEP) {
            mDrawingList.remove(0);
        }
        Path cachePath = new Path(mPath);
        Paint cachePaint = new Paint(mPaint);
        PathDrawingInfo info = new PathDrawingInfo();
        info.setPath(cachePath);
        info.paint = cachePaint;
        mDrawingList.add(info);
        mCanEraser = true;
        if (mCallback != null) {
            mCallback.onUndoRedoStatusChanged();
        }
    }


}
