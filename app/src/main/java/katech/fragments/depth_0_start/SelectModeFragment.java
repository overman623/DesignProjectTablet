package katech.fragments.depth_0_start;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_1_login.LoginFragment;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.Data;

/**
 * Created by bjkim on 2017-07-20.
 */
public class SelectModeFragment extends ParentFragment{

    public static String TAG = "SelectModeFragment";

    private ViewFlipper flipper_mode;
    private int preTouchPosX = 0;
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private int nowPosition = MainActivity.MODE_DELIVERY;

    private Button btn_title_left;

    private Button btn_left_arrow;
    private Button btn_right_arrow;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_0_select_mode, container, false);
        flipper_mode = (ViewFlipper)view.findViewById(R.id.flipper_mode);
        flipper_mode.setOnTouchListener(listener);
        imageViews.clear();
        // text_flipper_page = (TextView) view.findViewById(R.id.text_flipper_page);

       // ButtonLayout btn_layout_title_left = (ButtonLayout) view.findViewById(R.id.btn_title_left);

        ButtonLayout btn_layout_left_arrow = (ButtonLayout)view.findViewById(R.id.btn_left_arrow);
        ButtonLayout btn_layout_right_arrow = (ButtonLayout)view.findViewById(R.id.btn_right_arrow);


        new ButtonLayoutNormalSetting(requestManager, btn_layout_left_arrow, "모드선택왼쪽화살표");
        new ButtonLayoutNormalSetting(requestManager, btn_layout_right_arrow, "모드선택오른쪽화살표");

       // btn_title_left = (Button) btn_layout_title_left.findViewById(R.id.btn_clickable);

        ImageView img_login_view1 = (ImageView) view.findViewById(R.id.img_login_view1);
        ImageView img_login_view2 = (ImageView) view.findViewById(R.id.img_login_view2);
        ImageView img_login_view3 = (ImageView) view.findViewById(R.id.img_login_view3);

       // ImageView img_select_mode_content = (ImageView) view.findViewById(R.id.img_select_mode_content);
        ImageView img_bottom_title = (ImageView) view.findViewById(R.id.img_bottom_title);
       // ImageView img_background = (ImageView) view.findViewById(R.id.img_background);

        btn_left_arrow = (Button) btn_layout_left_arrow.findViewById(R.id.btn_clickable);
        btn_right_arrow = (Button) btn_layout_right_arrow.findViewById(R.id.btn_clickable);
        Button btn_next_fragment = (Button) view.findViewById(R.id.btn_next_fragment);

        btn_left_arrow.setOnClickListener(clickListener);
        btn_right_arrow.setOnClickListener(clickListener);
        btn_next_fragment.setOnClickListener(clickListener);

        imageViews.add(img_login_view1);
        imageViews.add(img_login_view2);
        imageViews.add(img_login_view3);

       // new ButtonLayoutNormalSetting(requestManager, btn_layout_title_left, "타이틀왼쪽화살표");

        setOnclickListeners(btn_left_arrow, btn_right_arrow, btn_next_fragment);

        requestManager.load(Data.Image.get("모드이미지배송")).into(img_login_view1);
        requestManager.load(Data.Image.get("모드이미지헬스케어")).into(img_login_view2);
        requestManager.load(Data.Image.get("모드이미지공유")).into(img_login_view3);

        //requestManager.load(Data.Image.get("배경")).into(img_background);
        requestManager.load(Data.Image.get("바닥글자")).into(img_bottom_title);

        nowPosition = MainActivity.MODE_DELIVERY;

        return view;

    }

    private void setOnclickListeners(Button... buttons){
        for(Button button : buttons){
            button.setOnClickListener(clickListener);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void moveNextView(){
        flipper_mode.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.flipper_appear_form_right));
        flipper_mode.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.flipper_disappear_to_left));
        makePageText(imageViews.size(), ++nowPosition);
        flipper_mode.showNext();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void movePreviousView(){
        flipper_mode.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.flipper_appear_form_left));
        flipper_mode.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.flipper_disappear_to_right));
        makePageText(imageViews.size(), --nowPosition);
        flipper_mode.showPrevious();
    }

    private void makePageText(int size, int position){
        if(position == -1){
            nowPosition = size - 1;
        }else if(position == size){
            nowPosition = 0;
        }
        Log.d(TAG, nowPosition + "");
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_clickable:
                    if(v.equals(btn_left_arrow)){
                        movePreviousView();
                    }else if(v.equals(btn_right_arrow)){
                        moveNextView();
                    }
                    break;
                case R.id.btn_left_arrow:
                    movePreviousView();
                    break;
                case R.id.btn_right_arrow:
                    moveNextView();
                    break;
                case R.id.btn_next_fragment:
                    switch (nowPosition){
                        case MainActivity.MODE_DELIVERY:
                            //mainActivity.replaceFragment(new DeliveryModeFragment(), DeliveryModeFragment.TAG, true);
                            mainActivity.nowMode = MainActivity.MODE_DELIVERY;
                            break;
                        case MainActivity.MODE_SHARING:
                            //mainActivity.replaceFragment(new SharingModeFragment(), SharingModeFragment.TAG, true);
                            mainActivity.nowMode = MainActivity.MODE_SHARING;
                            break;
                        case MainActivity.MODE_HEALTH:
                            //mainActivity.replaceFragment(new HealthModeFragment(), HealthModeFragment.TAG, true);
                            mainActivity.nowMode = MainActivity.MODE_HEALTH;
                            break;
                    }
                    Log.d(TAG, mainActivity.nowMode + " : select mode");
                    //mainActivity.replaceFragment(new LoginFragment(), LoginFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
                    mainActivity.replaceFragment(new LoginFragment(), LoginFragment.TAG, MainActivity.SAVE_FRAGMENT);
                    //프레그먼트 로그인으로 교체함.

                    break;
            }
        }
    };

    private View.OnTouchListener listener = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN){
                preTouchPosX = (int)event.getX();
            }

            if (event.getAction() == MotionEvent.ACTION_UP){
                int nTouchPosX = (int)event.getX();
                if (nTouchPosX < preTouchPosX){
                    moveNextView();
                }else if (nTouchPosX > preTouchPosX){
                    movePreviousView();
                }
                preTouchPosX = nTouchPosX;
            }
            return true;
        }
    };

}
