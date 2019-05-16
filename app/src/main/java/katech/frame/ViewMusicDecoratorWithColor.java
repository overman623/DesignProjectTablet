package katech.frame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.R;

/**
 * Created by bjkim on 2017-07-22.
 */
public class ViewMusicDecoratorWithColor extends ViewDecorator{

    private RequestManager requestManager;
    private int nowMode;
    private View view;

    public ViewMusicDecoratorWithColor(Context context, int nowMode) {
        this.context = context;
        requestManager = Glide.with(context);
        this.nowMode = nowMode;
    }

    @Override
    public void setView(View view){

        ToggleButtonLayout toggle_btn_music_slow = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_music_slow);
        ToggleButtonLayout toggle_btn_music_pause = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_music_pause);
        ToggleButtonLayout toggle_btn_music_fast = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_music_fast);

        ButtonLayout btn_layout_detail_music = (ButtonLayout) view.findViewById(R.id.btn_detail_music);

        Button btn_detail_music = (Button) btn_layout_detail_music.findViewById(R.id.btn_clickable);
        btn_detail_music.setOnClickListener(this.onClickListener);

        final SeekBar seek_bar_music = (SeekBar) view.findViewById(R.id.seek_bar_music);
        ViewTreeObserver vto = seek_bar_music.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                Resources res = context.getResources();
                Drawable progressDrawable = null;
                Drawable thumb = null;
                if (nowMode == MainActivity.MODE_DELIVERY) {
                    progressDrawable = res.getDrawable(R.drawable.deco_seek_bar_effecter_orange);
                    thumb = res.getDrawable(Data.Image.get("배송모드_돌기"));
                } else if (nowMode == MainActivity.MODE_HEALTH) {
                    progressDrawable = res.getDrawable(R.drawable.deco_seek_bar_effecter_mint);
                    thumb = res.getDrawable(Data.Image.get("헬스모드_돌기"));
                } else if (nowMode == MainActivity.MODE_SHARING) {
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

        ProgressBar progress_music = (ProgressBar) view.findViewById(R.id.progress_music);
        Drawable progressDrawable = null;

        ImageView img_sound_down = (ImageView) view.findViewById(R.id.img_sound_down);
        ImageView img_sound_up = (ImageView) view.findViewById(R.id.img_sound_up);
        requestManager.load(Data.Image.get("볼륨최소")).into(img_sound_down);
        requestManager.load(Data.Image.get("볼륨최대")).into(img_sound_up);

        if(nowMode == MainActivity.MODE_DELIVERY){
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_slow, "배송모드_되감기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_pause, "배송모드_일시정지");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_fast, "배송모드_빨리감기");
            new ButtonLayoutNormalSetting(requestManager, btn_layout_detail_music, "배송모드_상세보기");
            progressDrawable = context.getDrawable(R.drawable.deco_seek_bar_effecter_orange);
            progress_music.setProgressDrawable(progressDrawable);
        }else if(nowMode == MainActivity.MODE_HEALTH){
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_slow, "헬스모드_되감기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_pause, "헬스모드_일시정지");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_fast, "헬스모드_빨리감기");
            new ButtonLayoutNormalSetting(requestManager, btn_layout_detail_music, "헬스모드_상세보기");
            progressDrawable = context.getDrawable(R.drawable.deco_seek_bar_effecter_mint);
            progress_music.setProgressDrawable(progressDrawable);
        }else if(nowMode == MainActivity.MODE_SHARING){
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_slow, "공유모드_되감기");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_pause, "공유모드_일시정지");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_music_fast, "공유모드_빨리감기");
            new ButtonLayoutNormalSetting(requestManager, btn_layout_detail_music, "공유모드_상세보기");
            progressDrawable = context.getDrawable(R.drawable.deco_seek_bar_effecter_violet);
            progress_music.setProgressDrawable(progressDrawable);
        }

        this.view = view;
    }

    public View getView(){
        return this.view;
    }

}
