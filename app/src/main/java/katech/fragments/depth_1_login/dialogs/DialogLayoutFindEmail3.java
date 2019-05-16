package katech.fragments.depth_1_login.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import katech.designprojecttablet.R;


public class DialogLayoutFindEmail3 extends AlertDialog {

    private int layoutResource;

    public Button btn_ok;

    public DialogLayoutFindEmail3(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public DialogLayoutFindEmail3(Context context, int themeResId) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    protected DialogLayoutFindEmail3(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
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
        getWindow().setLayout(1000, 1000);

        setContentView(this.layoutResource);
        setLayout();
    }

    private void setLayout(){
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
