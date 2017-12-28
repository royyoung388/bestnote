package ypc.world.bestnote;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by RoyYoung on 2017/12/28.
 * 所有activity的基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    private View contentView;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = setContentView();
        setContentView(contentView);

        //buterknife
        ButterKnife.bind(this);
        //eventbus
        EventBus.getDefault().register(this);

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    /**
     * 设置布局的View
     * @return  获取布局，用于setContentView
     */
    protected abstract View setContentView();

    /**
     * 初始化的所有方法
     */
    protected abstract void init();

    /**
     * 获取View
     * @return
     */
    private View getContentView() {
        return contentView;
    }

    /**
     * 获取Context
     * @return
     */
    protected Context getContext() {
        return mContext;
    }
}
