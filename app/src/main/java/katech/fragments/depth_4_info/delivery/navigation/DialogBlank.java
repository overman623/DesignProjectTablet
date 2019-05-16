package katech.fragments.depth_4_info.delivery.navigation;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.R;


public class DialogBlank extends AlertDialog {

    private int layoutResource;

    public ImageView img_dialog_info_title_image;

    private int mode;

    public Handler handler = new Handler();
    public DialogBlank(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public DialogBlank(Context context, int themeResId) {
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

    }

}
