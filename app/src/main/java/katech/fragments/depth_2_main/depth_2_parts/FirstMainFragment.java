package katech.fragments.depth_2_main.depth_2_parts;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_1_login.LoginFragment;
import katech.fragments.depth_2_main.MainFragment;
import katech.fragments.depth_3_main.BatteryFragment;
import katech.fragments.depth_3_main.FreezeFragment;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.DesignTextView;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;

public class FirstMainFragment extends ParentFragment {

    public static String TAG = "FirstMainFragment";

    private ToggleButton toggle_head_light;
    private ToggleButton toggle_door_open;
    private ToggleButton toggle_seat_belt;

    private MainFragment mainFragment;
    private LinearLayout layout_main_inflated_text;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btn_clickable){
                if(v.equals(toggle_head_light)){
                    mainFragment.headLight = !mainFragment.headLight;
                }else if(v.equals(toggle_door_open)){
                    mainFragment.doorOpen = !mainFragment.doorOpen;
                }else if(v.equals(toggle_seat_belt)){
                    mainFragment.seatBelt = !mainFragment.seatBelt;
                }
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "FirstMainFragment onAttach");
        mainFragment = (MainFragment) getParentFragment();
       // mainActivity.setCancelListener(null);
        /*mainActivity.setCancelListener(new MainActivity.CancelListener() {
            @Override
            public void callBack() {
                mainActivity.replaceFragment(new LoginFragment(), LoginFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
            }
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2_main_first, container, false);
        ToggleButtonLayout toggle_btn_panel = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_panel);
        ToggleButtonLayout toggle_btn_left_light = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_left_light);
        ToggleButtonLayout toggle_btn_head_light = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_head_light);
        ToggleButtonLayout toggle_btn_door_open = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_door_open);
        ToggleButtonLayout toggle_btn_seat_belt = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_seat_belt);
        ToggleButtonLayout toggle_btn_right_light = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_right_light);

        ButtonLayout btn_layout_battery = (ButtonLayout) view.findViewById(R.id.btn_layout_battery);
        Button btn_battery = (Button) btn_layout_battery.findViewById(R.id.btn_clickable);
        btn_battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFragment.checkClear();
                mainFragment.initInflatedLayout();
                mainFragment.decorator.setTitleLeftImage(false);
                mainFragment.decorator.setTitleText("배터리");
                mainFragment.replaceFragment(new BatteryFragment(), BatteryFragment.TAG, MainActivity.SAVE_FRAGMENT);

            }
        });

        TextView text_d = (TextView) view.findViewById(R.id.text_d);
        DesignTextView text_battery_percent = (DesignTextView) view.findViewById(R.id.text_battery_percent);
        String percentText = text_battery_percent.getText().toString();
        text_battery_percent.setText(getPercentText(percentText));

        toggle_head_light = (ToggleButton) toggle_btn_head_light.findViewById(R.id.btn_clickable);
        toggle_door_open = (ToggleButton) toggle_btn_door_open.findViewById(R.id.btn_clickable);
        toggle_seat_belt = (ToggleButton) toggle_btn_seat_belt.findViewById(R.id.btn_clickable);

        toggle_head_light.setOnClickListener(onClickListener);
        toggle_door_open.setOnClickListener(onClickListener);
        toggle_seat_belt.setOnClickListener(onClickListener);

        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_head_light, "모드헤드라이트");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_door_open, "모드문열림");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_seat_belt, "모드안전벨트");

        layout_main_inflated_text = (LinearLayout) view.findViewById(R.id.layout_main_inflated_text);

        int mode = getArguments().getInt("mode");
        if(mode == MainActivity.MODE_DELIVERY){
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_panel, "배송모드_판넬");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "배송모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "배송모드_우향등");
            new ButtonLayoutNormalSetting(requestManager, btn_layout_battery, "배송모드_배터리");
            text_d.setTextColor(Color.parseColor(MainActivity.COLOR_ORANGE));
            inflateText(R.layout.layout_inflate_main_text_delivery);
        }else if(mode == MainActivity.MODE_HEALTH){
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_panel, "헬스모드_판넬");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "헬스모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "헬스모드_우향등");
            new ButtonLayoutNormalSetting(requestManager, btn_layout_battery, "헬스모드_배터리");
            text_d.setTextColor(Color.parseColor(MainActivity.COLOR_MINT));
            inflateText(R.layout.layout_inflate_main_text_health);
        }else if(mode == MainActivity.MODE_SHARING){
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_panel, "공유모드_판넬");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "공유모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "공유모드_우향등");
            new ButtonLayoutNormalSetting(requestManager, btn_layout_battery, "공유모드_배터리");
            text_d.setTextColor(Color.parseColor(MainActivity.COLOR_VIOLET));
            inflateText(R.layout.layout_inflate_main_text_share);
        }

        return view;

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void inflateText(int layoutResource){
        View view = null;
        view = View.inflate(getContext(), layoutResource, null);
        if(mainActivity.nowMode == MainActivity.MODE_SHARING){
            DesignTextView text_kilo = (DesignTextView) view.findViewById(R.id.text_kilo);
            text_kilo.setText(getKiloText(text_kilo.getText().toString()));
        }
        layout_main_inflated_text.addView(view);
    }

    public SpannableStringBuilder getPercentText(String data){
        final SpannableStringBuilder sp = new SpannableStringBuilder(data);
        sp.setSpan(new AbsoluteSizeSpan(50), data.length() - 2, data.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), 0, data.length() - 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }

    public SpannableStringBuilder getKiloText(String data){
        final SpannableStringBuilder sp = new SpannableStringBuilder(data);
        sp.setSpan(new AbsoluteSizeSpan(50), data.length() - 2, data.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), 0, data.length() - 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "FirstMainFragment onDetach");
        super.onDetach();
    }
}
