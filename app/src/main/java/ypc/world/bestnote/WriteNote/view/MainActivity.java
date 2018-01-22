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
import android.widget.Button;
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
import ypc.world.bestnote.WriteNote.ui.WhiteBoardView;
import ypc.world.bestnote.WriteNote.ui.adapters.ColorSelectAdapter;

public class MainActivity extends BaseActivity implements IMainView {


    WhiteBoardPresenter whiteBoardPresenter;
    boolean isPencilPressed = false,
            isEraserPressed = false,
            isMarkerPressed = false;
    boolean pencilBubbleDisplayed = false, eraserBubbleDisplayed = false;
    BubbleLayout pencilBubbleLayout, eraserBubbleLayout;
    PopupWindow pencilPopupWindow, eraserPopupWindow;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.img_pencil)
    ImageView pencilImg;
    @BindView(R.id.img_eraser)
    ImageView eraserImg;
    @BindView(R.id.img_marker)
    ImageView markerImg;
    @BindView(R.id.view_colormarker)
    View colorMarkerView;

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
            refresh();
            eraserBubbleDisplayed = false;
            pencilImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pencil_icon_selected));
            isPencilPressed = true;
            whiteBoardPresenter.setMode(WhiteBoardView.Mode.DRAW);
        } else {
            if (!pencilBubbleDisplayed)
                displayPencilBubbleLayout(v);
            else
                pencilPopupWindow.dismiss();
            pencilBubbleDisplayed = !pencilBubbleDisplayed;
        }
    }

    @OnClick(R.id.img_eraser)
    public void click_Eraser(View v) {
        if (!isEraserPressed) {
            refresh();
            pencilBubbleDisplayed = false;
            eraserImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.eraser_icon_selected));
            isEraserPressed = true;
            whiteBoardPresenter.setMode(WhiteBoardView.Mode.ERASER);
        } else {
            if (!eraserBubbleDisplayed)
                displayEraserBubbleLayout(v);
            else
                eraserPopupWindow.dismiss();
            eraserBubbleDisplayed = !eraserBubbleDisplayed;
        }
    }

    @OnClick(R.id.img_marker)
    public void click_Marker(View v) {
        refresh();
        markerImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.marker_icon_selected));
        isMarkerPressed = true;
        whiteBoardPresenter.setMode(WhiteBoardView.Mode.HIGHTLIGHT);
    }


    private void refresh() {
        isPencilPressed = false;
        isEraserPressed = false;
        isMarkerPressed = false;
        pencilImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pencil_icon));
        eraserImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.eraser_icon));
        markerImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.marker_icon));
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
    public void onEventMainThread(ColorModifyEvent event) {
        int color = event.getColor();
        whiteBoardPresenter.setPenColor(color);
        colorMarkerView.setBackgroundColor(color);
        pencilPopupWindow.dismiss();
    }


    private void initBubbleLayout() {
        //Pencil Bubble
        pencilBubbleLayout = (BubbleLayout) LayoutInflater.from(this).inflate(R.layout.select_pencil_bubble_layout, null);
        pencilPopupWindow = BubblePopupHelper.create(this, pencilBubbleLayout);
        RecyclerView colorRecyclerView = (RecyclerView) pencilBubbleLayout.findViewById(R.id.select_color_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        colorRecyclerView.setLayoutManager(layoutManager);
        ArrayList<Integer> colorIds = new ArrayList<Integer>();
        for (int i : Config.colors)
            colorIds.add(i);
        ColorSelectAdapter adapter = new ColorSelectAdapter(colorIds);
        colorRecyclerView.setAdapter(adapter);

        initPencilBubbleListeners(pencilBubbleLayout);

        //Eraser Bubble
        eraserBubbleLayout = (BubbleLayout) LayoutInflater.from(this).inflate(R.layout.select_eraser_bubblelayout, null);
        eraserPopupWindow = BubblePopupHelper.create(this, eraserBubbleLayout);
        initEraserBubbleListeners(eraserBubbleLayout);

        refresh();
        pencilImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pencil_icon_selected));
    }


    private void initEraserBubbleListeners(BubbleLayout bubbleLayout) {
        ImageView eraserSmall = (ImageView) bubbleLayout.findViewById(R.id.btn_eraser_small),
                eraserMedium = (ImageView) bubbleLayout.findViewById(R.id.btn_eraser_medium),
                eraserLarge = (ImageView) bubbleLayout.findViewById(R.id.btn_eraser_large);
        eraserSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteBoardPresenter.setEraserSize(20.0f);
                eraserPopupWindow.dismiss();
            }
        });

        eraserMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteBoardPresenter.setEraserSize(40.0f);
                eraserPopupWindow.dismiss();
            }
        });

        eraserLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteBoardPresenter.setEraserSize(65.0f);
                eraserPopupWindow.dismiss();
            }
        });
    }


    private void displayPencilBubbleLayout(View v) {
        int[] location = new int[2];
        v.getLocationInWindow(location);
        pencilBubbleLayout.setArrowDirection(ArrowDirection.TOP);
        pencilPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], v.getHeight() + location[1]);

    }


    private void initPencilBubbleListeners(BubbleLayout bubbleLayout) {
        Button btn2px = (Button) bubbleLayout.findViewById(R.id.btn_line2px),
                btn3px = (Button) bubbleLayout.findViewById(R.id.btn_line3px),
                btn4px = (Button) bubbleLayout.findViewById(R.id.btn_line4px);
        btn2px.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteBoardPresenter.setPenSize(5.0f);
                pencilPopupWindow.dismiss();
            }
        });

        btn3px.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteBoardPresenter.setPenSize(18.0f);
                pencilPopupWindow.dismiss();
            }
        });

        btn4px.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteBoardPresenter.setPenSize(36.0f);
                pencilPopupWindow.dismiss();
            }
        });
    }


    private void displayEraserBubbleLayout(View v) {
        int[] location = new int[2];
        v.getLocationInWindow(location);
        eraserBubbleLayout.setArrowDirection(ArrowDirection.TOP);
        eraserPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], v.getHeight() + location[1]);
    }


}
