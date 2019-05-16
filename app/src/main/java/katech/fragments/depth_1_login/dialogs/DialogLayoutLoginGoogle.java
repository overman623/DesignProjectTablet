package katech.fragments.depth_1_login.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import katech.designprojecttablet.R;


public class DialogLayoutLoginGoogle extends AlertDialog {

    private int layoutResource;

    public Button btn_ok;
    public Button btn_cancel;
    public ImageView img_dialog_all;

    public DialogLayoutLoginGoogle(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public DialogLayoutLoginGoogle(Context context, int themeResId) {
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
        getWindow().setLayout(800, 800);

        setContentView(this.layoutResource);

        setLayout();
    }

    private void setLayout(){
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
