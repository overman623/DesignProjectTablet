package katech.frame;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.RequestManager;

import katech.designprojecttablet.R;

/**
 * Created by bjkim on 2017-07-22.
 */
public abstract class ViewDecorator {

    private RequestManager requestManager;
    private int nowMode;
    private View view;
    protected View.OnClickListener onClickListener = null;
    protected Context context;

    public abstract void setView(View view);

    public View getView(){
        return this.view;
    }

    public void setOnclickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

}
