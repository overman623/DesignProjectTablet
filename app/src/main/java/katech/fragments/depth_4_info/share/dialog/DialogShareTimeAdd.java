package katech.fragments.depth_4_info.share.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import katech.designprojecttablet.R;


public class DialogShareTimeAdd extends AlertDialog {

    private int layoutResource;

    public Button btn_cancel;
    public Button btn_ok;

    public ImageView img_dialog_info_title_image;

    public TextView text_add_time_min;
    public TextView text_add_time_hour;
    public TextView text_add_time;

    public DialogShareTimeAdd(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public DialogShareTimeAdd(Context context, int themeResId) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public void setLayoutResource(int layoutResource){
        this.layoutResource = layoutResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;

        getWindow().setAttributes(lpWindow);
        getWindow().setLayout(1362, 767);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setContentView(this.layoutResource);

        setLayout();
    }

    private void setLayout(){
        img_dialog_info_title_image = (ImageView) findViewById(R.id.img_dialog_info_title_image);

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel.setBackgroundResource(R.drawable.deco_btn_violet);
        btn_ok.setBackgroundResource(R.drawable.deco_btn_violet);
        text_add_time_min = (TextView) findViewById(R.id.text_add_time_min);
        text_add_time_hour = (TextView) findViewById(R.id.text_add_time_hour);
        text_add_time = (TextView) findViewById(R.id.text_add_time);
        text_add_time_min.setOnClickListener(textClickListener);
        text_add_time_hour.setOnClickListener(textClickListener);
        text_add_time.setOnClickListener(textClickListener);
    }

    public void setOnClickListenerToCancelButton(View.OnClickListener listener){
        btn_cancel.setOnClickListener(listener);
    }

    public void setOnClickListenerToOkButton(View.OnClickListener listener){
        btn_ok.setOnClickListener(listener);
    }

    private View.OnClickListener textClickListener = new View.OnClickListener() {

        int time;
        int min;
        int hour;

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.text_add_time_min:
                    if(min == 0){
                        text_add_time_min.setText(" 40 \n 20 \n 30 ");
                        min = 1;
                    }else if(min == 1){
                        text_add_time_min.setText(" 30 \n 40 \n 20 ");
                        min = 2;
                    }else if(min == 2){
                        text_add_time_min.setText(" 20 \n 30 \n 40 ");
                        min = 0;
                    }
                    break;
                case R.id.text_add_time_hour:
                    if(hour == 0){
                        text_add_time_hour.setText(" 06 \n 04 \n 05 ");
                        hour = 1;
                    }else if(hour == 1){
                        text_add_time_hour.setText(" 05 \n 06 \n 04 ");
                        hour = 2;
                    }else if(hour == 2){
                        text_add_time_hour.setText(" 04 \n 05 \n 06 ");
                        hour = 0;
                    }
                    break;
                case R.id.text_add_time:
                    if(time == 0){
                        text_add_time.setText("1:00");
                        time = 1;
                    }else if(time == 1){
                        text_add_time.setText("2:00");
                        time = 2;
                    }else if(time == 2){
                        text_add_time.setText("3:00");
                        time = 0;
                    }
                    break;
            }
        }
    };
}
