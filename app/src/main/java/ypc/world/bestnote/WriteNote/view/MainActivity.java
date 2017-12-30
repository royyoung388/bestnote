package ypc.world.bestnote.WriteNote.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ypc.world.bestnote.BaseActivity;
import ypc.world.bestnote.R;
import ypc.world.bestnote.WriteNote.Manager;
import ypc.world.bestnote.WriteNote.presenter.WhiteBoardPresenter;

public class MainActivity extends BaseActivity implements IMainView {


    WhiteBoardPresenter whiteBoardPresenter;

    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @OnClick(R.id.btn_undo)
    public void click_BtnUndo() {
        whiteBoardPresenter.Undo();
    }

    @OnClick(R.id.btn_redo)
    public void click_BtnRedo() {
        whiteBoardPresenter.Redo();
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

}
