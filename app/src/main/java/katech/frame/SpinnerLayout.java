package katech.frame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import katech.designprojecttablet.R;

public class SpinnerLayout extends RelativeLayout {

    public Spinner spinner = null;

    public SpinnerLayout(Context context) {
        super(context);
        initView();
    }

    public SpinnerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SpinnerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.spinner_with_glide, this, false);
        spinner = (Spinner) findViewById(R.id.spinner);
        addView(v);
    }

}
