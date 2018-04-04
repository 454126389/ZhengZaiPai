package app.conqueror.com.zhengzaipai.base;

import android.content.Context;

/**
 * Created by baixiaokang on 16/4/22.
 */

public abstract class BasePresenter<E, T> {
    public Context context;
    public E mModel;
    public T mView;
    public RxManage mRxManage = new RxManage();

    public void setVM(T v, E m,Context context) {
        this.mView = v;
        this.mModel = m;
        this.context = context;
        this.onStart();
    }

    public abstract void onStart();

    public void onDestroy() {
        mRxManage.clear();
    }
}
