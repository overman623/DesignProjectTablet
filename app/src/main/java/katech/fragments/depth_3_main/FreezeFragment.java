package katech.fragments.depth_3_main;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_1_login.LoginFragment;
import katech.fragments.depth_2_main.MainFragment;
import katech.fragments.depth_2_main.depth_2_parts.FirstMainFragment;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.Data;
import katech.frame.DesignTextView;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;



public class FreezeFragment extends ParentFragment {

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
        Log.d(TAG, "FreezeFragment onAttach");
        mainFragment.isMain = false;
        mainActivity.setCancelListener(new MainActivity.CancelListener() {
            @Override
            public void callBack() {
                mainFragment.checkClear();
                mainFragment.isMain = true;
                mainFragment.decorator.setTitleLeftImage(true);
                mainFragment.decorator.setTitleText("SMARTCAR");
                mainActivity.setCancelListener(null);
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
        ToggleButtonLayout toggle_btn_right_light = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_right_light);

        ToggleButtonLayout toggle_btn_layout_head_light = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_head_light);
        ToggleButtonLayout toggle_btn_layout_door_open = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_door_open);
        ToggleButtonLayout toggle_btn_layout_seat_belt = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_seat_belt);

        toggle_btn_head_light = (ToggleButton) toggle_btn_layout_head_light.findViewById(R.id.btn_clickable);
        toggle_btn_door_open = (ToggleButton) toggle_btn_layout_door_open.findViewById(R.id.btn_clickable);
        toggle_btn_seat_belt = (ToggleButton) toggle_btn_layout_seat_belt.findViewById(R.id.btn_clickable);

        TextView text_battery_percent = (TextView) view.findViewById(R.id.text_battery_percent);
        text_battery_percent.setText(getPercentText(text_battery_percent.getText().toString()));

        ImageView img_cloud = (ImageView) view.findViewById(R.id.img_cloud);
        ImageView img_wind = (ImageView) view.findViewById(R.id.img_wind);
        ImageView img_temperature = (ImageView) view.findViewById(R.id.img_temperature);

        ImageView img_weather = (ImageView) view.findViewById(R.id.img_weather);
        requestManager.load(Data.Image.get("안개")).into(img_weather);

        ImageView img_freeze_color = (ImageView) view.findViewById(R.id.img_freeze_color);
        final ImageView img_freeze_gray = (ImageView) view.findViewById(R.id.img_freeze_gray);

        ToggleButtonLayout toggle_btn_fresh_air = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_fresh_air);
        ToggleButtonLayout toggle_btn_cool_air = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_cool_air);
        ToggleButtonLayout toggle_btn_hot_air = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_hot_air);

        TextView text_humidity = (TextView) view.findViewById(R.id.text_humidity);
        text_humidity.setText(getPercentText(text_humidity.getText().toString()));

        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_layout_head_light, "모드헤드라이트");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_layout_door_open, "모드문열림");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_layout_seat_belt, "모드안전벨트");

        toggle_btn_layout_head_light.setOnClickListener(onClickListener);
        toggle_btn_layout_door_open.setOnClickListener(onClickListener);
        toggle_btn_layout_seat_belt.setOnClickListener(onClickListener);


        ImageView img_temperature_background = (ImageView) view.findViewById(R.id.img_temperature_background);
        ImageView img_temperature_foreground = (ImageView) view.findViewById(R.id.img_temperature_foreground); //여기와 시크가 리스너와 반응할것임.

        ImageView img_background_graph = (ImageView) view.findViewById(R.id.img_background_graph);
        requestManager.load(Data.Image.get("그래프")).into(img_background_graph);

        mainFragment.decorator.setTitleLeftImage(false);

        final SeekBar seek_bar_freeze = (SeekBar) view.findViewById(R.id.seek_bar_freeze);
        seek_bar_freeze.setProgress(100);
        seek_bar_freeze.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress >= 0 && progress < 25){
                    requestManager.load(Data.Image.get("냉난방게이지_1_0")).into(img_freeze_gray);
                }else if(progress >= 25 && progress < 50){
                    requestManager.load(Data.Image.get("냉난방게이지_1_1")).into(img_freeze_gray);
                }else if(progress >= 50 && progress < 75){
                    requestManager.load(Data.Image.get("냉난방게이지_1_2")).into(img_freeze_gray);
                }else if(progress >= 75 && progress < 100){
                    requestManager.load(Data.Image.get("냉난방게이지_1_3")).into(img_freeze_gray);
                }else{
                    requestManager.load(Data.Image.get("냉난방게이지_1_4")).into(img_freeze_gray);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ViewTreeObserver vto = seek_bar_freeze.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if(getActivity() == null) return true;
                Resources res = getActivity().getResources();
                Drawable progressDrawable = null;
                Drawable thumb = null;
                if (mainActivity.nowMode == MainActivity.MODE_DELIVERY) {
                    progressDrawable = res.getDrawable(R.drawable.deco_seek_bar_effecter_orange);
                    thumb = res.getDrawable(Data.Image.get("배송모드_돌기"));
                } else if (mainActivity.nowMode == MainActivity.MODE_HEALTH) {
                    progressDrawable = res.getDrawable(R.drawable.deco_seek_bar_effecter_mint);
                    thumb = res.getDrawable(Data.Image.get("헬스모드_돌기"));
                } else if (mainActivity.nowMode == MainActivity.MODE_SHARING) {
                    progressDrawable = res.getDrawable(R.drawable.deco_seek_bar_effecter_violet);
                    thumb = res.getDrawable(Data.Image.get("공유모드_돌기"));
                }
                int h = (int) (seek_bar_freeze.getMeasuredHeight() * 1.0);
                int w = h;
                Bitmap bmpOrg = ((BitmapDrawable) thumb).getBitmap();
                Bitmap bmpScaled = Bitmap.createScaledBitmap(bmpOrg, w, h, true);
                Drawable newThumb = new BitmapDrawable(res, bmpScaled);
                newThumb.setBounds(0, 0, newThumb.getIntrinsicWidth(), newThumb.getIntrinsicHeight());
                seek_bar_freeze.setThumb(newThumb);
                seek_bar_freeze.setProgressDrawable(progressDrawable);
                seek_bar_freeze.getViewTreeObserver().removeOnDrawListener(null);
                return true;
            }
        });

        if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_ORANGE));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "배송모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "배송모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "배송모드_우향등");
            requestManager.load(Data.Image.get("배송모드_구름")).into(img_cloud);
            requestManager.load(Data.Image.get("배송모드_바람")).into(img_wind);
            requestManager.load(Data.Image.get("배송모드_온도계")).into(img_temperature);
            requestManager.load(Data.Image.get("배송모드_냉난방게이지_상세_풀")).into(img_freeze_color);
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_fresh_air, "배송모드_환기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_cool_air, "배송모드_냉방");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_hot_air, "배송모드_난방");
            requestManager.load(Data.Image.get("배송모드_빈_온도계")).into(img_temperature_background);
        }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_MINT));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "헬스모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "헬스모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "헬스모드_우향등");
            requestManager.load(Data.Image.get("헬스모드_구름")).into(img_cloud);
            requestManager.load(Data.Image.get("헬스모드_바람")).into(img_wind);
            requestManager.load(Data.Image.get("헬스모드_온도계")).into(img_temperature);
            requestManager.load(Data.Image.get("헬스모드_냉난방게이지_상세_풀")).into(img_freeze_color);
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_fresh_air, "헬스모드_환기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_cool_air, "헬스모드_냉방");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_hot_air, "헬스모드_난방");
            requestManager.load(Data.Image.get("헬스모드_빈_온도계")).into(img_temperature_background);
        }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_VIOLET));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "공유모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "공유모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "공유모드_우향등");
            requestManager.load(Data.Image.get("공유모드_구름")).into(img_cloud);
            requestManager.load(Data.Image.get("공유모드_바람")).into(img_wind);
            requestManager.load(Data.Image.get("공유모드_온도계")).into(img_temperature);
            requestManager.load(Data.Image.get("공유모드_냉난방게이지_상세_풀")).into(img_freeze_color);
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_fresh_air, "공유모드_환기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_cool_air, "공유모드_냉방");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_hot_air, "공유모드_난방");
            requestManager.load(Data.Image.get("공유모드_빈_온도계")).into(img_temperature_background);
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "FreezeFragment onDetach");
       // mainActivity.setCancelListener(null);
    }

    public SpannableStringBuilder getPercentText(String data){
        final SpannableStringBuilder sp = new SpannableStringBuilder(data);
        sp.setSpan(new AbsoluteSizeSpan(50), data.length() - 1, data.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#c3c3c3")), 0, data.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }
}
