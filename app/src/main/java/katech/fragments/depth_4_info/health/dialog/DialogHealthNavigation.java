package katech.fragments.depth_4_info.health.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.R;


public class DialogHealthNavigation extends AlertDialog {

    private int layoutResource;

    public Button btn_cancel;
    public Button btn_ok;
    public TextView text_title;


    public ImageView img_dialog_info_title_image;

    public DialogHealthNavigation(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public DialogHealthNavigation(Context context, int themeResId) {
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
        getWindow().setLayout(1562, 767);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setContentView(this.layoutResource);

        setLayout();
    }

    private void setLayout(){
        img_dialog_info_title_image = (ImageView) findViewById(R.id.img_dialog_info_title_image);
        text_title = (TextView) findViewById(R.id.text_title);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel.setBackgroundResource(R.drawable.deco_btn_mint);
        btn_ok.setBackgroundResource(R.drawable.deco_btn_mint);

    }

    public void setOnClickListenerToCancelButton(View.OnClickListener listener){
        btn_cancel.setOnClickListener(listener);
    }

    public void setOnClickListenerToOkButton(View.OnClickListener listener){
        btn_ok.setOnClickListener(listener);
    }

}
