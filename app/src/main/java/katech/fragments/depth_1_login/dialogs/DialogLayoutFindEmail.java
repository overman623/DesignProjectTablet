package katech.fragments.depth_1_login.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import katech.designprojecttablet.R;
import katech.frame.SpinnerLayout;

public class DialogLayoutFindEmail extends AlertDialog {

    private int layoutResource;

    public Button btn_cancel;
    public Button btn_ok;

    private EditText edit_text_email;
    private EditText edit_text_name;
    private EditText edit_text_birth;

    private RadioButton radio_btn_1;
    private RadioButton radio_btn_2;

    public DialogLayoutFindEmail(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public DialogLayoutFindEmail(Context context, int themeResId) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    protected DialogLayoutFindEmail(Context context, boolean cancelable, OnCancelListener cancelListener) {
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

        edit_text_email = (EditText) findViewById(R.id.edit_text_email);

        SpinnerLayout spinner_phone = (SpinnerLayout) findViewById(R.id.spinner_phone);
        Spinner spinner = (Spinner) spinner_phone.findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.phone, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        radio_btn_1 = (RadioButton) findViewById(R.id.radio_btn_1);
        radio_btn_2 = (RadioButton) findViewById(R.id.radio_btn_2);
//        radio_btn_1.setButtonDrawable(R.drawable.deco_radio_btn_skyblue);
//        radio_btn_2.setButtonDrawable(R.drawable.deco_radio_btn_skyblue);
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
