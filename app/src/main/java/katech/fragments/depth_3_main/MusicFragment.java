package katech.fragments.depth_3_main;

import android.annotation.TargetApi;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_1_login.LoginFragment;
import katech.fragments.depth_2_main.MainFragment;
import katech.fragments.depth_2_main.depth_2_parts.FirstMainFragment;
import katech.fragments.depth_3_main.music.MusicAdapter;
import katech.fragments.depth_3_main.music.MusicItem;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.Data;
import katech.frame.DesignTextView;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;



public class MusicFragment extends ParentFragment {

    public static String TAG = "MusicFragment";
    private MainFragment mainFragment;

    private ToggleButton toggle_btn_head_light;
    private ToggleButton toggle_btn_door_open;
    private ToggleButton toggle_btn_seat_belt;

    private ArrayList<MusicItem> musicItemArrayList = new ArrayList<>();

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
        //mainActivity.setCancelListener(cancelListener);
        mainFragment.isMain = false;
        mainActivity.setCancelListener(new MainActivity.CancelListener() {
            @Override
            public void callBack() {
                mainActivity.setCancelListener(null);
                mainFragment.isMain = true;
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

    private MainActivity.CancelListener cancelListener = new MainActivity.CancelListener() {
        @Override
        public void callBack() {
            mainActivity.setCancelListener(null);
            mainFragment.checkClear();
            mainFragment.decorator.setTitleLeftImage(true);
            mainFragment.replaceFragment(new FirstMainFragment(), FirstMainFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3_music, container, false);

        final int mode = getArguments().getInt("mode");

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

        ImageView img_album_photo = (ImageView) view.findViewById(R.id.img_album_photo);
        requestManager.load(Data.Image.get("앨범커버")).into(img_album_photo);
        ProgressBar progress_music = (ProgressBar) view.findViewById(R.id.progress_music);
        Drawable progressDrawable = null;

        ToggleButtonLayout toggle_btn_music_slow = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_music_slow);
        ToggleButtonLayout toggle_btn_music_pause = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_music_pause);
        ToggleButtonLayout toggle_btn_music_fast = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_music_fast);

        ImageView img_sound_down = (ImageView) view.findViewById(R.id.img_sound_down);
        ImageView img_sound_up = (ImageView) view.findViewById(R.id.img_sound_up);
        requestManager.load(Data.Image.get("볼륨최소")).into(img_sound_down);
        requestManager.load(Data.Image.get("볼륨최대")).into(img_sound_up);

        final SeekBar seek_bar_music = (SeekBar) view.findViewById(R.id.seek_bar_music);
        ViewTreeObserver vto = seek_bar_music.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                if (getActivity() == null) return true;
                Resources res = getActivity().getResources();
                Drawable progressDrawable = null;
                Drawable thumb = null;
                if (mode == MainActivity.MODE_DELIVERY) {
                    progressDrawable = res.getDrawable(R.drawable.deco_seek_bar_effecter_orange);
                    thumb = res.getDrawable(Data.Image.get("배송모드_돌기"));
                } else if (mode == MainActivity.MODE_HEALTH) {
                    progressDrawable = res.getDrawable(R.drawable.deco_seek_bar_effecter_mint);
                    thumb = res.getDrawable(Data.Image.get("헬스모드_돌기"));
                } else if (mode == MainActivity.MODE_SHARING) {
                    progressDrawable = res.getDrawable(R.drawable.deco_seek_bar_effecter_violet);
                    thumb = res.getDrawable(Data.Image.get("공유모드_돌기"));
                }
                int h = (int) (seek_bar_music.getMeasuredHeight() * 1.0);
                int w = h;
                Bitmap bmpOrg = ((BitmapDrawable) thumb).getBitmap();
                Bitmap bmpScaled = Bitmap.createScaledBitmap(bmpOrg, w, h, true);
                Drawable newThumb = new BitmapDrawable(res, bmpScaled);
                newThumb.setBounds(0, 0, newThumb.getIntrinsicWidth(), newThumb.getIntrinsicHeight());
                seek_bar_music.setThumb(newThumb);

                seek_bar_music.setProgressDrawable(progressDrawable);
                seek_bar_music.getViewTreeObserver().removeOnDrawListener(null);
                return true;
            }
        });

        toggle_btn_layout_head_light.setOnClickListener(onClickListener);
        toggle_btn_layout_door_open.setOnClickListener(onClickListener);
        toggle_btn_layout_seat_belt.setOnClickListener(onClickListener);

        ListView list_music = (ListView) view.findViewById(R.id.list_music);
        musicItemArrayList.add(0, new MusicItem(1, "Your Song", "Sam Kim"));
        musicItemArrayList.add(1, new MusicItem(2, "I LUV IT", "싸이(PSY)"));
        musicItemArrayList.add(2, new MusicItem(3, "오늘 취하면(Feat. 창모)", "수란(SURAN)"));
        musicItemArrayList.add(3, new MusicItem(4, "팔레트(Feat. G-DRAGON)", "아이유"));
        musicItemArrayList.add(4, new MusicItem(5, "TOMBOY", "혁오"));
        musicItemArrayList.add(5, new MusicItem(6, "가죽자켓", "미정"));
        MusicAdapter musicAdapter = new MusicAdapter(musicItemArrayList, getActivity(), requestManager, mainActivity.nowMode);
        list_music.setAdapter(musicAdapter);

        if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_ORANGE));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "배송모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "배송모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "배송모드_우향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_slow, "배송모드_되감기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_pause, "배송모드_일시정지");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_fast, "배송모드_빨리감기");
            progressDrawable = getActivity().getDrawable(R.drawable.deco_seek_bar_effecter_orange);
            progress_music.setProgressDrawable(progressDrawable);
        }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_MINT));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "헬스모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "헬스모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "헬스모드_우향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_slow, "헬스모드_되감기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_pause, "헬스모드_일시정지");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_fast, "헬스모드_빨리감기");
            progressDrawable = getActivity().getDrawable(R.drawable.deco_seek_bar_effecter_mint);
            progress_music.setProgressDrawable(progressDrawable);
        }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_VIOLET));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "공유모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "공유모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "공유모드_우향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_slow, "공유모드_되감기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_pause, "공유모드_일시정지");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_fast, "공유모드_빨리감기");
            progressDrawable = getActivity().getDrawable(R.drawable.deco_seek_bar_effecter_violet);
            progress_music.setProgressDrawable(progressDrawable);
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
