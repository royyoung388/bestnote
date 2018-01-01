package ypc.world.bestnote.WriteNote.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.daasuu.bl.ArrowDirection;
import com.daasuu.bl.BubbleLayout;
import com.daasuu.bl.BubblePopupHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ypc.world.bestnote.BaseActivity;
import ypc.world.bestnote.Config;
import ypc.world.bestnote.R;
import ypc.world.bestnote.WriteNote.Manager;
import ypc.world.bestnote.WriteNote.events.ColorModifyEvent;
import ypc.world.bestnote.WriteNote.presenter.WhiteBoardPresenter;
import ypc.world.bestnote.WriteNote.ui.adapters.ColorSelectAdapter;

public class MainActivity extends BaseActivity implements IMainView {


    WhiteBoardPresenter whiteBoardPresenter;
    boolean isPencilPressed = false;
    BubbleLayout bubbleLayout;
    PopupWindow popupWindow;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.img_pencil)
    ImageView pencilImg;

    @OnClick(R.id.btn_undo)
    public void click_BtnUndo() {
        whiteBoardPresenter.Undo();
    }

    @OnClick(R.id.btn_redo)
    public void click_BtnRedo() {
        whiteBoardPresenter.Redo();
    }

    @OnClick(R.id.img_pencil)
    public void click_Pencil(View v) {
        if (!isPencilPressed) {
            pencilImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pencil_icon_selected));
            isPencilPressed = true;
        } else {
            displayBubbleLayout(v);
        }
    }


    private void refresh() {
        isPencilPressed = false;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        whiteBoardPresenter = Manager.getWhiteBoardPresenter();
        initBubbleLayout();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected View bindView() {
        return null;
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ColorModifyEvent event)
    {
        int color = event.getColor();
        whiteBoardPresenter.setPenColor(color);
        popupWindow.dismiss();
    }


    private void initBubbleLayout() {
        bubbleLayout = (BubbleLayout) LayoutInflater.from(this).inflate(R.layout.select_pencil_bubble_layout, null);
        popupWindow = BubblePopupHelper.create(this, bubbleLayout);
        RecyclerView colorRecyclerView = (RecyclerView) bubbleLayout.findViewById(R.id.select_color_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        colorRecyclerView.setLayoutManager(layoutManager);
        ArrayList<Integer> colorIds = new ArrayList<Integer>();
        for (int i : Config.colors)
            colorIds.add(i);
        ColorSelectAdapter adapter = new ColorSelectAdapter(colorIds);
        colorRecyclerView.setAdapter(adapter);
    }


    private void displayBubbleLayout(View v) {
        int[] location = new int[2];
        v.getLocationInWindow(location);
        bubbleLayout.setArrowDirection(ArrowDirection.TOP);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], v.getHeight() + location[1]);

    }

}
