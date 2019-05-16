package katech.fragments.depth_3_main.navigation;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_2_main.MainFragment;
import katech.fragments.depth_3_main.NavigationFragment;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.Data;
import katech.frame.DesignTextView;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;

/**
 * Created by bjkim on 2017-07-24.
 */
public class NavigationStartFragment extends ParentFragment {

    public static String TAG = "NavigationStartFragment";
    private MainFragment mainFragment;

    private ToggleButton toggle_btn_head_light;
    private ToggleButton toggle_btn_door_open;
    private ToggleButton toggle_btn_seat_belt;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btn_clickable){
                if(v.equals(toggle_btn_head_light)){
                    mainFragment.headLight = !mainFragment.headLight;
                }else if(v.equals(toggle_btn_door_open)){
                    mainFragment.doorOpen = !mainFragment.doorOpen;
                }else if(v.equals(toggle_btn_seat_belt)){
                    mainFragment.seatBelt = !mainFragment.seatBelt;
                }
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainFragment = (MainFragment) getParentFragment();
        mainActivity.setCancelListener(new MainActivity.CancelListener() {
            @Override
            public void callBack() {
                mainFragment.replaceFragment(new NavigationFragment(), NavigationFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toggle_btn_head_light.setChecked(mainFragment.headLight);
        toggle_btn_door_open.setChecked(mainFragment.doorOpen);
        toggle_btn_seat_belt.setChecked(mainFragment.seatBelt);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_4_navigation, container, false);

        DesignTextView text_speed = (DesignTextView) view.findViewById(R.id.text_speed);
        ButtonLayout btn_toggle_battery = (ButtonLayout) view.findViewById(R.id.btn_toggle_battery);

        ToggleButtonLayout toggle_btn_left_light = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_left_light);
        ToggleButtonLayout toggle_btn_right_light = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_right_light);

        ToggleButtonLayout toggle_btn_layout_head_light = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_head_light);
        ToggleButtonLayout toggle_btn_layout_door_open = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_door_open);
        ToggleButtonLayout toggle_btn_layout_seat_belt = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_seat_belt);

        toggle_btn_head_light = (ToggleButton) toggle_btn_layout_head_light.findViewById(R.id.btn_clickable);
        toggle_btn_door_open = (ToggleButton) toggle_btn_layout_door_open.findViewById(R.id.btn_clickable);
        toggle_btn_seat_belt = (ToggleButton) toggle_btn_layout_seat_belt.findViewById(R.id.btn_clickable);

        TextView text_battery_percent = (TextView) view.findViewById(R.id.text_battery_percent);
        text_battery_percent.setText(getPercentText(text_battery_percent.getText().toString()));

        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_layout_head_light, "모드헤드라이트");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_layout_door_open, "모드문열림");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_layout_seat_belt, "모드안전벨트");

        toggle_btn_layout_head_light.setOnClickListener(onClickListener);
        toggle_btn_layout_door_open.setOnClickListener(onClickListener);
        toggle_btn_layout_seat_belt.setOnClickListener(onClickListener);

        TextView text_minute = (TextView) view.findViewById(R.id.text_minute);
        text_minute.setText(getPercentMinuteText(text_minute.getText().toString()));

        ToggleButtonLayout btn_toggle_sound = (ToggleButtonLayout) view.findViewById(R.id.btn_toggle_sound);

        ImageView img_map = (ImageView) view.findViewById(R.id.img_map);
        requestManager.load(Data.Image.get("지도")).into(img_map);
        img_map.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                //다이얼로그.
                final DialogNavigationFinish dialogNavigationFinish = new DialogNavigationFinish(getContext());
                dialogNavigationFinish.setLayoutResource(R.layout.layout_delivery_dialog_navigation);
                dialogNavigationFinish.setMode(mainActivity.nowMode);
                dialogNavigationFinish.show();
                if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
                    requestManager.load(Data.Image.get("배송모드_네비게이션_완료_안내창")).into(dialogNavigationFinish.img_dialog_info_title_image);
                }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
                    requestManager.load(Data.Image.get("헬스모드_네비게이션_완료_안내창")).into(dialogNavigationFinish.img_dialog_info_title_image);
                }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
                    requestManager.load(Data.Image.get("공유모드_네비게이션_완료_안내창")).into(dialogNavigationFinish.img_dialog_info_title_image);
                }
                dialogNavigationFinish.setOnClickListenerToOkButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogNavigationFinish.cancel();
                        mainActivity.onBackPressed();
                    }
                });
            }
        });

        ImageView img_navigation_arrow_up = (ImageView) view.findViewById(R.id.img_navigation_arrow_up);
        ImageView img_navigation_arrow_down = (ImageView) view.findViewById(R.id.img_navigation_arrow_down);

        requestManager.load(Data.Image.get("지도_화살표_위")).into(img_navigation_arrow_up);
        requestManager.load(Data.Image.get("지도_화살표_아래")).into(img_navigation_arrow_down);

        if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_ORANGE));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "배송모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "배송모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "배송모드_우향등");
            new ToggleButtonLayoutNormalSetting(requestManager, btn_toggle_sound, "배송모드_배터리_사운드키");
        }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_MINT));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "헬스모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "헬스모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "헬스모드_우향등");
            new ToggleButtonLayoutNormalSetting(requestManager, btn_toggle_sound, "헬스모드_배터리_사운드키");
        }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_VIOLET));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "공유모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "공유모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "공유모드_우향등");
            new ToggleButtonLayoutNormalSetting(requestManager, btn_toggle_sound, "공유모드_배터리_사운드키");
        }

        return view;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity.setCancelListener(null);
    }

    public SpannableStringBuilder getPercentText(String data){
        final SpannableStringBuilder sp = new SpannableStringBuilder(data);
        sp.setSpan(new AbsoluteSizeSpan(50), data.length() - 1, data.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), 0, data.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }

    public SpannableStringBuilder getPercentMinuteText(String data){
        final SpannableStringBuilder sp = new SpannableStringBuilder(data);
        //   sp.setSpan(new AbsoluteSizeSpan(50), data.length() - 1, data.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
            sp.setSpan(new ForegroundColorSpan(Color.parseColor(MainActivity.COLOR_ORANGE)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
            sp.setSpan(new ForegroundColorSpan(Color.parseColor(MainActivity.COLOR_MINT)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
            sp.setSpan(new ForegroundColorSpan(Color.parseColor(MainActivity.COLOR_VIOLET)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return sp;
    }

}