package ypc.world.bestnote.WriteNote.presenter;

import ypc.world.bestnote.WriteNote.view.ViewSample;
import ypc.world.bestnote.WriteNote.model.ModelImpl;
import ypc.world.bestnote.WriteNote.model.ModelSample;

/**
 * Created by RoyYoung on 2017/12/28.
 */

public class Presenter {

    private ModelSample model = new ModelImpl();
    private ViewSample view;

    public Presenter(ViewSample view) {
        this.view = view;
    }

    public void doSomething() {
        model.modelDo(new Listener() {
            @Override
            public void onSuccess() {
                //view.doSomething();
            }

            @Override
            public void OnFail() {

            }
        });
    }
}
