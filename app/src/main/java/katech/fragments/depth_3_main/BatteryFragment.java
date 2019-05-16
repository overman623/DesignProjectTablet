package katech.fragments.depth_3_main;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_2_main.MainFragment;
import katech.fragments.depth_2_main.depth_2_parts.FirstMainFragment;
import katech.fragments.depth_3_main.battery.BatteryNavigationFragment;
import katech.fragments.depth_3_main.battery.DialogBattery;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.Data;
import katech.frame.DesignTextView;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;

/**
 * Created by bjkim on 2017-07-23.
 */
public class BatteryFragment extends ParentFragment {

    public static String TAG = "BatteryFragment";
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
    private View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            //다음 프레그먼트..
            String mode = null;
            final DialogBattery dialogBattery = new DialogBattery(getActivity());
            dialogBattery.setMode(mainActivity.nowMode);
            dialogBattery.setLayoutResource(R.layout.layout_delivery_dialog_navigation);
            dialogBattery.show();
            dialogBattery.setOnClickListenerToOkButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainFragment.replaceFragment(new BatteryNavigationFragment(), BatteryNavigationFragment.TAG, MainActivity.SAVE_FRAGMENT);
                    dialogBattery.cancel();
                    //길안내가 시작됨.
                }
            });

            if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
                mode = "배송";
            }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
                mode = "헬스";
            }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
                mode = "공유";
            }

            requestManager.load(Data.Image.get(mode + "모드_충전소_안내창")).into(dialogBattery.img_dialog_info_title_image);
            //나중에 다이얼로그로 교체한다.
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainFragment = (MainFragment) getParentFragment();
        mainFragment.isMain = false;
        mainActivity.setCancelListener(new MainActivity.CancelListener() {
            @Override
            public void callBack() {
                mainFragment.isMain = true;
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

        View view = inflater.inflate(R.layout.fragment_3_battery, container, false);

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

        ImageView img_battery_status = (ImageView) view.findViewById(R.id.img_battery_status);
        ImageView img_battery_load = (ImageView) view.findViewById(R.id.img_battery_load);
        ImageView img_battery_percent = (ImageView) view.findViewById(R.id.img_battery_percent);

        ImageView img_battery_status_big = (ImageView) view.findViewById(R.id.img_battery_status_big);
        DesignTextView text_battery_percent_inner = (DesignTextView) view.findViewById(R.id.text_battery_percent_inner);
        text_battery_percent_inner.setText(getPercentTextInner(text_battery_percent_inner.getText().toString()));

        ButtonLayout btn_battery_navigation1 = (ButtonLayout) view.findViewById(R.id.btn_battery_navigation1);
        ButtonLayout btn_battery_navigation2 = (ButtonLayout) view.findViewById(R.id.btn_battery_navigation2);
        Button btn_navigation2 = (Button) btn_battery_navigation2.findViewById(R.id.btn_clickable);
        Button btn_navigation1 = (Button) btn_battery_navigation1.findViewById(R.id.btn_clickable);


        btn_navigation1.setOnClickListener(onClickListener2);
        btn_navigation2.setOnClickListener(onClickListener2);

        ImageView img_battery_status_drive = (ImageView) view.findViewById(R.id.img_battery_status_drive);
        ImageView img_battery_status_freeze = (ImageView) view.findViewById(R.id.img_battery_status_freeze);
        ImageView img_battery_status_navigation = (ImageView) view.findViewById(R.id.img_battery_status_navigation);
        ImageView img_battery_status_music = (ImageView) view.findViewById(R.id.img_battery_status_music);
        requestManager.load(Data.Image.get("배터리_상태_드라이브")).into(img_battery_status_drive);
        requestManager.load(Data.Image.get("배터리_상태_냉난방")).into(img_battery_status_freeze);//가변
        requestManager.load(Data.Image.get("배터리_상태_길찾기")).into(img_battery_status_navigation);//가변
        requestManager.load(Data.Image.get("배터리_상태_음악")).into(img_battery_status_music);//가변

        ProgressBar progress_battery_status_drive = (ProgressBar) view.findViewById(R.id.progress_battery_status_drive);
        ProgressBar progress_battery_status_freeze = (ProgressBar) view.findViewById(R.id.progress_battery_status_freeze);
        ProgressBar progress_battery_status_navigation = (ProgressBar) view.findViewById(R.id.progress_battery_status_navigation);
        ProgressBar progress_battery_status_music = (ProgressBar) view.findViewById(R.id.progress_battery_status_music);

        Drawable progressDrawable1 = null;
        Drawable progressDrawable2 = null;
        Drawable progressDrawable3 = null;
        Drawable progressDrawable4 = null;

        DesignTextView text_battery_status_drive = (DesignTextView) view.findViewById(R.id.text_battery_status_drive);
        text_battery_status_drive.setText(getPercentTextProgress(text_battery_status_drive.getText().toString()));
        DesignTextView text_battery_status_freeze = (DesignTextView) view.findViewById(R.id.text_battery_status_freeze);
        text_battery_status_freeze.setText(getPercentTextProgress(text_battery_status_freeze.getText().toString()));
        DesignTextView text_battery_status_navigation = (DesignTextView) view.findViewById(R.id.text_battery_status_navigation);
        text_battery_status_navigation.setText(getPercentTextProgress(text_battery_status_navigation.getText().toString()));
        DesignTextView text_battery_status_music = (DesignTextView) view.findViewById(R.id.text_battery_status_music);
        text_battery_status_music.setText(getPercentTextProgress(text_battery_status_music.getText().toString()));

        if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
            progressDrawable1 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_orange);
            progressDrawable2 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_orange);
            progressDrawable3 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_orange);
            progressDrawable4 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_orange);

            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_ORANGE));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "배송모드_배터리");
            new ButtonLayoutNormalSetting(requestManager, btn_battery_navigation1, "배송모드_배터리_안내_길찾기");
            new ButtonLayoutNormalSetting(requestManager, btn_battery_navigation2, "배송모드_배터리_안내_길찾기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "배송모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "배송모드_우향등");
            requestManager.load(Data.Image.get("배송모드_배터리_잔량")).into(img_battery_status);
            requestManager.load(Data.Image.get("배송모드_배터리_길찾기")).into(img_battery_load);
            requestManager.load(Data.Image.get("배송모드_배터리_소비현황")).into(img_battery_percent);
            requestManager.load(Data.Image.get("배송모드_배터리")).into(img_battery_status_big);
        }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
            progressDrawable1 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_mint);
            progressDrawable2 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_mint);
            progressDrawable3 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_mint);
            progressDrawable4 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_mint);
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_MINT));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "헬스모드_배터리");
            new ButtonLayoutNormalSetting(requestManager, btn_battery_navigation1, "헬스모드_배터리_안내_길찾기");
            new ButtonLayoutNormalSetting(requestManager, btn_battery_navigation2, "헬스모드_배터리_안내_길찾기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "헬스모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "헬스모드_우향등");
            requestManager.load(Data.Image.get("헬스모드_배터리_잔량")).into(img_battery_status);
            requestManager.load(Data.Image.get("헬스모드_배터리_길찾기")).into(img_battery_load);
            requestManager.load(Data.Image.get("헬스모드_배터리_소비현황")).into(img_battery_percent );
            requestManager.load(Data.Image.get("헬스모드_배터리")).into(img_battery_status_big);
        }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
            progressDrawable1 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_violet);
            progressDrawable2 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_violet);
            progressDrawable3 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_violet);
            progressDrawable4 = mainActivity.getDrawable(R.drawable.deco_seek_bar_effecter_violet);
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_VIOLET));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "공유모드_배터리");
            new ButtonLayoutNormalSetting(requestManager, btn_battery_navigation1, "공유모드_배터리_안내_길찾기");
            new ButtonLayoutNormalSetting(requestManager, btn_battery_navigation2, "공유모드_배터리_안내_길찾기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "공유모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "공유모드_우향등");
            requestManager.load(Data.Image.get("공유모드_배터리_잔량")).into(img_battery_status);
            requestManager.load(Data.Image.get("공유모드_배터리_길찾기")).into(img_battery_load);
            requestManager.load(Data.Image.get("공유모드_배터리_소비현황")).into(img_battery_percent );
            requestManager.load(Data.Image.get("공유모드_배터리")).into(img_battery_status_big);
        }
        progress_battery_status_drive.setProgressDrawable(progressDrawable1);
        progress_battery_status_freeze.setProgressDrawable(progressDrawable2);
        progress_battery_status_navigation.setProgressDrawable(progressDrawable3);
        progress_battery_status_music.setProgressDrawable(progressDrawable4);

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


    public SpannableStringBuilder getPercentTextInner(String data){
        final SpannableStringBuilder sp = new SpannableStringBuilder(data);
        sp.setSpan(new AbsoluteSizeSpan(90), data.length() - 1, data.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), 0, data.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }

    public SpannableStringBuilder getPercentTextProgress(String data){
        final SpannableStringBuilder sp = new SpannableStringBuilder(data);
        sp.setSpan(new AbsoluteSizeSpan(30), data.length() - 1, data.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), 0, data.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }
}
