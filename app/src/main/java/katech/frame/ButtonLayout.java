package katech.frame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.RequestManager;

import katech.designprojecttablet.R;


public class ButtonLayout extends RelativeLayout{

    public ImageView img_non_changeable;
    public Button btn_clickable;
    public ImageView img_changeable;
    private RequestManager requestManager;

    private int beforeResource = 0;
    private int afterResource = 0;

    public ButtonLayout(Context context) {
        super(context);
        initView();
    }

    public ButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.button_layout, this, false);
        addView(v);
        img_non_changeable = (ImageView) findViewById(R.id.img_non_changeable);
        btn_clickable = (Button) findViewById(R.id.btn_clickable);
        img_changeable = (ImageView) findViewById(R.id.img_changeable);
        //btn_clickable.setOnTouchListener(this);
    }

    public void setOnTouchListener(final RequestManager requestManager){
        btn_clickable.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    requestManager.load(afterResource).into(img_changeable);
                }else if(event.getAction() == MotionEvent.ACTION_UP) {
                    //requestManager.load(beforeResource).into(img_changeable);
                    requestManager.load(R.drawable.background_none).into(img_changeable);
                }
                return false;
            }
        });
    }

    public void setOnTouchListener(OnTouchListener touchListener){
        btn_clickable.setOnTouchListener(touchListener);
    }

    public void setImage(RequestManager requestManager, String data){
        beforeResource = Data.Image.get(data);
        requestManager.load(beforeResource).into(img_non_changeable);
        afterResource = Data.Image.get(data+"_클릭");
    }


}
