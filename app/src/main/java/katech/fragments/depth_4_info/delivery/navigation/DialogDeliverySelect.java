package katech.fragments.depth_4_info.delivery.navigation;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.R;
import katech.frame.ButtonLayout;


public class DialogDeliverySelect extends AlertDialog {

    public ButtonLayout btn_call;
    public ButtonLayout btn_message;
    public ButtonLayout btn_complete;

    public ImageView img_dialog_info_title_image;

    public DialogDeliverySelect(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public DialogDeliverySelect(Context context, int themeResId) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
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

        setContentView(R.layout.layout_dialog_navigation_select);

        setLayout();
    }

    private void setLayout(){
        btn_complete = (ButtonLayout) findViewById(R.id.btn_complete);
        btn_call = (ButtonLayout) findViewById(R.id.btn_call);
        btn_message = (ButtonLayout) findViewById(R.id.btn_message);

    }


}
