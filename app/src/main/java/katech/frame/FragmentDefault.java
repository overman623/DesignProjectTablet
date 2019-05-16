package katech.frame;


import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_2_main.MainFragment;
import katech.fragments.depth_2_main.depth_2_parts.FirstMainFragment;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.Data;
import katech.frame.DesignTextView;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;

/**
 * Created by bjkim on 2017-07-22.
 */
public class FragmentDefault extends ParentFragment {

    public static String TAG = "FreezeFragment";
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
                mainActivity.setCancelListener(null);
                mainFragment.checkClear();
                mainFragment.decorator.setTitleLeftImage(true);
                mainFragment.replaceFragment(new FirstMainFragment(), FirstMainFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
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

        View view = inflater.inflate(R.layout.fragment_3_freeze, container, false);

        DesignTextView text_speed = (DesignTextView) view.findViewById(R.id.text_speed);
        ButtonLayout btn_toggle_battery = (ButtonLayout) view.findViewById(R.id.btn_toggle_battery);
        ToggleButtonLayout toggle_btn_left_light = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_left_light);
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

        if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_ORANGE));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "배송모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "배송모드_좌향등");
        }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_MINT));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "헬스모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "헬스모드_좌향등");
        }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_VIOLET));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "공유모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "공유모드_좌향등");
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
}
