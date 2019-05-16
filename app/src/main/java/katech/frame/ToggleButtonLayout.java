package katech.frame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.bumptech.glide.RequestManager;

import katech.designprojecttablet.R;

/**
 * Created by bjkim on 2017-07-21.
 */
public class ToggleButtonLayout extends RelativeLayout {

    private ImageView img_changeable;
    private ImageView img_non_changeable;
    private ToggleButton btn_clickable;

    private int beforeResource = 0;
    private int afterResource = 0;

    private RequestManager requestManager;

    public ToggleButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public ToggleButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ToggleButtonLayout(Context context) {
        super(context);
        initView();
    }

    protected void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.toggle_button_layout, this, false);
        addView(v);
        img_non_changeable = (ImageView) findViewById(R.id.img_non_changeable);
        btn_clickable = (ToggleButton) findViewById(R.id.btn_clickable);
        img_changeable = (ImageView) findViewById(R.id.img_changeable);
        //btn_clickable.setOnTouchListener(this);
    }

    public void setImage(RequestManager requestManager, String data){
        beforeResource = Data.Image.get(data);
        requestManager.load(beforeResource).into(img_non_changeable);
        afterResource = Data.Image.get(data+"_클릭");
    }

    public void setOnTouchListener(final RequestManager requestManager){
        btn_clickable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    requestManager.load(afterResource).into(img_changeable);
                }else{
                    requestManager.load(R.drawable.background_none).into(img_changeable);
                }
            }
        });
    }
}
