package katech.fragments.depth_1_login;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_0_start.SelectModeFragment;
import katech.fragments.depth_1_login.dialogs.DialogLayoutFindEmail;
import katech.fragments.depth_1_login.dialogs.DialogLayoutFindEmail2;
import katech.fragments.depth_1_login.dialogs.DialogLayoutFindPassword;
import katech.fragments.depth_1_login.dialogs.DialogLayoutFindPassword1;
import katech.fragments.depth_1_login.dialogs.DialogLayoutFindPassword2;
import katech.fragments.depth_1_login.dialogs.DialogLayoutLoginGoogle;
import katech.fragments.depth_1_login.dialogs.DialogLayoutLoginNaver;
import katech.fragments.depth_2_main.MainFragment;
import katech.frame.Data;

/**
 * Created by bjkim on 2017-07-20.
 */
public class LoginFragment extends ParentFragment {

    public static String TAG = "LoginFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_login, container, false);

//        mainActivity.backgroundToggle(true);

        Button btn_login = (Button) view.findViewById(R.id.btn_login);

        Button btn_find_email = (Button) view.findViewById(R.id.btn_find_email);
        Button btn_find_password = (Button) view.findViewById(R.id.btn_find_password);

        Button btn_naver = (Button) view.findViewById(R.id.btn_naver);
        Button btn_google = (Button) view.findViewById(R.id.btn_google);

        setOnclickListeners(btn_find_email, btn_find_password, btn_login, btn_naver, btn_google);

        ImageView img_start_main = (ImageView) view.findViewById(R.id.img_start_main);
        ImageView img_start_bottom = (ImageView) view.findViewById(R.id.img_bottom_title);
        ImageView img_naver = (ImageView) view.findViewById(R.id.img_naver);
        ImageView img_google = (ImageView) view.findViewById(R.id.img_google);

        requestManager.load(Data.Image.get("바닥글자")).into(img_start_bottom);
        requestManager.load(Data.Image.get("타이틀")).into(img_start_main);
        requestManager.load(Data.Image.get("네이버")).into(img_naver);
        requestManager.load(Data.Image.get("구글")).into(img_google);

        setTouchListenerOnTexts(btn_find_email, btn_find_password);

        return view;

    }

    private void setTouchListenerOnTexts(Button... buttons){
        for(Button button : buttons){
            button.setOnTouchListener(textTouchListener);
        }
    }

    private View.OnTouchListener textTouchListener = new View.OnTouchListener() {

        Button button = null;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            button = (Button) v;
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                button.setTextColor(Color.parseColor("#25C0F0"));
            }else if(event.getAction() == MotionEvent.ACTION_UP) {
                button.setTextColor(Color.WHITE);
            }
            return false;
        }

    };

    protected void setOnclickListeners(Button... buttons){
        for(Button button : buttons){
            button.setOnClickListener(clickListener);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*mainActivity.setCancelListener(new MainActivity.CancelListener() {
            @Override
            public void callBack() {
                //mainActivity.replaceFragment(new SelectModeFragment(), SelectModeFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
                mainActivity.replaceFragment(new SelectModeFragment(), SelectModeFragment.TAG, MainActivity.SAVE_FRAGMENT);
                mainActivity.setCancelListener(null);
            }
        });*/
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_find_email:
                    showDialogEmail1();
                    break;
                case R.id.btn_find_password:
                    showDialogPassword1();
                    break;
                case R.id.btn_login:
                    //mainActivity.replaceFragment(new SelectModeFragment(), SelectModeFragment.TAG, MainActivity.SAVE_FRAGMENT);
                    //여기서 본격적으로 시작한다.
                    //int mode = mainActivity.nowMode;
                   // Log.d(TAG, mainActivity.nowMode + " : login mode");
                    mainActivity.replaceFragment(new MainFragment(), MainFragment.TAG, MainActivity.SAVE_FRAGMENT);
                    //번들로 데이터를 싣는다.
                    break;
                case R.id.btn_naver:
                    showDialogNaver();
                    break;
                case R.id.btn_google:
                    showDialogGoogle();
                    break;
            }
        }
    };

    ////////////////////////////
    private void showDialogNaver(){
        final DialogLayoutLoginNaver dialogLayout = new DialogLayoutLoginNaver(getActivity());
        dialogLayout.setLayoutResource(R.layout.layout_dialog_login_naver);
        dialogLayout.show();
        dialogLayout.btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLayout.cancel();
                //mainActivity.replaceFragment(new SelectModeFragment(), SelectModeFragment.TAG, MainActivity.SAVE_FRAGMENT);
                // 모드 시작
            }
        });
    }

    private void showDialogGoogle(){
        final DialogLayoutLoginGoogle dialogLayout = new DialogLayoutLoginGoogle(getActivity());
        dialogLayout.setLayoutResource(R.layout.layout_dialog_login_google);
        dialogLayout.show();
        dialogLayout.btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLayout.cancel();
               // mainActivity.replaceFragment(new SelectModeFragment(), SelectModeFragment.TAG, MainActivity.SAVE_FRAGMENT);
                // 모드 시작
            }
        });
    }

    private void showDialogPassword1(){
        final DialogLayoutFindPassword dialogLayout = new DialogLayoutFindPassword(getActivity());
        dialogLayout.setLayoutResource(R.layout.layout_dialog_find_password);
        dialogLayout.show();
        dialogLayout.btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLayout.cancel();
                showDialogPassword2();
            }
        });
    }

    private void showDialogPassword2(){
        final DialogLayoutFindPassword1 dialogLayout = new DialogLayoutFindPassword1(getActivity());
        dialogLayout.setLayoutResource(R.layout.layout_dialog_find_password_1);
        dialogLayout.show();
        dialogLayout.btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLayout.cancel();
                showDialogPassword3();
            }
        });
    }

    private void showDialogPassword3(){
        final DialogLayoutFindPassword2 dialogLayout = new DialogLayoutFindPassword2(getActivity());
        dialogLayout.setLayoutResource(R.layout.layout_dialog_find_password_2);
        dialogLayout.show();
    }

    private void showDialogEmail1(){
        final DialogLayoutFindEmail dialogLayout = new DialogLayoutFindEmail(getActivity());
        dialogLayout.setLayoutResource(R.layout.layout_dialog_find_email);
        dialogLayout.show();
        dialogLayout.btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLayout.cancel();
                showDialogEmail2();
            }
        });
    }

    private void showDialogEmail2(){
        final DialogLayoutFindEmail2 dialogLayout = new DialogLayoutFindEmail2(getActivity());
        dialogLayout.setLayoutResource(R.layout.layout_dialog_find_email_1);
        dialogLayout.show();
        dialogLayout.btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLayout.cancel();
                showDialogEmail3();
            }
        });

    }

    private void showDialogEmail3(){
        final DialogLayoutFindPassword2 dialogLayout = new DialogLayoutFindPassword2(getActivity());
        dialogLayout.setLayoutResource(R.layout.layout_dialog_find_email_2);
        dialogLayout.show();
    }

}
