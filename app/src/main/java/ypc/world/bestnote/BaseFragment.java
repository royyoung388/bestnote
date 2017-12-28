package ypc.world.bestnote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by RoyYoung on 2017/12/28.
 * 所有fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    private View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        contentView = getView();

        //butterknife
        ButterKnife.bind(this, contentView);

        init();
        return contentView;
    }

    /**
     * 获取View
     *
     * @param container
     * @return
     */
    protected abstract View setContentView(ViewGroup container);

    /**
     * 初始化的所有方法
     *
     * @return View
     */
    protected abstract View init();

    protected View getContentView() {
        return contentView;
    }
}
