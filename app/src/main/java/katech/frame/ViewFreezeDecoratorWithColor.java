package katech.frame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
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
public class ViewFreezeDecoratorWithColor extends ViewDecorator{

    private RequestManager requestManager;
    private int nowMode;
    private View view;

    public ViewFreezeDecoratorWithColor(Context context, int nowMode) {
        this.context = context;
        requestManager = Glide.with(context);
        this.nowMode = nowMode;
    }

    @Override
    public void setView(View view){

        ImageView img_freeze_color = (ImageView) view.findViewById(R.id.img_freeze_color);
        final ImageView img_freeze_gray = (ImageView) view.findViewById(R.id.img_freeze_gray);

        ToggleButtonLayout toggle_btn_snow = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_snow);
        ToggleButtonLayout toggle_btn_temp = (ToggleButtonLayout) view.findViewById(R.id.toggle_btn_temp);

        ButtonLayout btn_layout_detail_freeze = (ButtonLayout) view.findViewById(R.id.btn_detail_freeze);
        Button btn_detail_freeze = (Button) btn_layout_detail_freeze.findViewById(R.id.btn_clickable);
        btn_detail_freeze.setOnClickListener(this.onClickListener);

        //requestManager.load(Data.Image.get("")).into(img_freeze_gray);


        final SeekBar seek_bar_freeze = (SeekBar) view.findViewById(R.id.seek_bar_freeze);
        seek_bar_freeze.setProgress(100);
        seek_bar_freeze.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress >= 0 && progress < 25){
                    requestManager.load(Data.Image.get("냉난방게이지_0_0")).into(img_freeze_gray);
                }else if(progress >= 25 && progress < 50){
                    requestManager.load(Data.Image.get("냉난방게이지_0_1")).into(img_freeze_gray);
                }else if(progress >= 50 && progress < 75){
                    requestManager.load(Data.Image.get("냉난방게이지_0_2")).into(img_freeze_gray);
                }else if(progress >= 75 && progress < 100){
                    requestManager.load(Data.Image.get("냉난방게이지_0_3")).into(img_freeze_gray);
                }else{
                    requestManager.load(Data.Image.get("냉난방게이지_0_4")).into(img_freeze_gray);
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

        if(nowMode == MainActivity.MODE_DELIVERY){
            requestManager.load(Data.Image.get("배송모드_냉난방게이지_풀")).into(img_freeze_color);
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_snow, "배송모드_눈송이버튼");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_temp, "배송모드_온도계버튼");
            new ButtonLayoutNormalSetting(requestManager, btn_layout_detail_freeze, "배송모드_상세보기");
        }else if(nowMode == MainActivity.MODE_HEALTH){
            requestManager.load(Data.Image.get("헬스모드_냉난방게이지_풀")).into(img_freeze_color);
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_snow, "헬스모드_눈송이버튼");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_temp, "헬스모드_온도계버튼");
            new ButtonLayoutNormalSetting(requestManager, btn_layout_detail_freeze, "헬스모드_상세보기");
        }else if(nowMode == MainActivity.MODE_SHARING){
            requestManager.load(Data.Image.get("공유모드_냉난방게이지_풀")).into(img_freeze_color);
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_snow, "공유모드_눈송이버튼");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_temp, "공유모드_온도계버튼");
            new ButtonLayoutNormalSetting(requestManager, btn_layout_detail_freeze, "공유모드_상세보기");
        }

        this.view = view;

    }

    public View getView(){
        return this.view;
    }

}
