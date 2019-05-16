package katech.frame;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.RequestManager;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.R;

/**
 * Created by bjkim on 2017-07-20.
 */
public class ViewDecoratorWithColor {

    private RequestManager requestManager;
    private View view;
    private String colorString;
    private String modeString;
    private View.OnClickListener onClickListener;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    private ButtonLayout btn_title_left;
    private TextView text_title_center;

    public RadioGroup radio_group_bottom;


    public static String TAG = "ViewDecoratorWithColor";

    public ViewDecoratorWithColor() {
    }

    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {

        btn_title_left = (ButtonLayout) view.findViewById(R.id.btn_title_left);
        setTitleLeftImage(true);

        text_title_center = (TextView) view.findViewById(R.id.text_title_center);
        text_title_center.setText("SMARTCAR");
        radio_group_bottom = (RadioGroup) view.findViewById(R.id.radio_group_bottom);

        ButtonLayout btn_goto_home = (ButtonLayout) view.findViewById(R.id.btn_goto_home);
        Button btn_go_home = (Button) btn_goto_home.findViewById(R.id.btn_clickable);
        btn_go_home.setTag(R.id.btn_clickable, "press_home_key"); //이거 추가해서 온클릭 리스너에서 태그를 찾는다.
        //btn_go_home.setTag(R.id.radio_group_bottom, radio_group_bottom);

        ImageView img_find_load_sub = (ImageView) view.findViewById(R.id.img_find_load_sub);
        ImageView img_find_load = (ImageView) view.findViewById(R.id.img_find_load);
        RadioButton btn_radio_find_load = (RadioButton) view.findViewById(R.id.btn_radio_find_load);
        btn_radio_find_load.setTag(R.id.btn_radio_find_load, img_find_load);

        ImageView img_freeze_sub = (ImageView) view.findViewById(R.id.img_freeze_sub);
        ImageView img_freeze = (ImageView) view.findViewById(R.id.img_freeze);
        RadioButton btn_radio_freeze = (RadioButton) view.findViewById(R.id.btn_radio_freeze);
        btn_radio_freeze.setTag(R.id.btn_radio_freeze, img_freeze);

        RadioButton btn_radio_guide_changeable = (RadioButton) view.findViewById(R.id.btn_radio_guide_changeable);
        ImageView img_guide_changeable_sub = (ImageView) view.findViewById(R.id.img_guide_changeable_sub);
        ImageView img_guide_changeable = (ImageView) view.findViewById(R.id.img_guide_changeable);
        TextView text_guide_changeable = (TextView) view.findViewById(R.id.text_guide_changeable);
        btn_radio_guide_changeable.setTag(R.id.btn_radio_guide_changeable, img_guide_changeable);

        ImageView img_music = (ImageView) view.findViewById(R.id.img_music);
        ImageView img_music_sub = (ImageView) view.findViewById(R.id.img_music_sub);
        RadioButton btn_radio_music = (RadioButton) view.findViewById(R.id.btn_radio_music);
        btn_radio_music.setTag(R.id.btn_radio_music, img_music);

        ToggleButtonLayout toggle_button_layout = (ToggleButtonLayout) view.findViewById(R.id.toggle_button_layout);
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_button_layout, "공통모드_삼각경고등");

        requestManager.load(Data.Image.get("공통모드_길찾기")).into(img_find_load_sub);
        requestManager.load(Data.Image.get("공통모드_냉난방")).into(img_freeze_sub);
        requestManager.load(Data.Image.get("공통모드_음악")).into(img_music_sub);

        if(this.colorString == MainActivity.COLOR_ORANGE){  //배송모드
            new ButtonLayoutNormalSetting(requestManager, btn_goto_home, "배송모드_홈으로");
            text_guide_changeable.setText("배송안내");
            requestManager.load(Data.Image.get("배송모드_안내")).into(img_guide_changeable_sub);
        }else if(this.colorString == MainActivity.COLOR_MINT){ //헬스모드
            new ButtonLayoutNormalSetting(requestManager, btn_goto_home, "헬스모드_홈으로");
            text_guide_changeable.setText("예약안내");
            requestManager.load(Data.Image.get("헬스모드_안내")).into(img_guide_changeable_sub);
        }else if(this.colorString == MainActivity.COLOR_VIOLET){ //공유모드
            new ButtonLayoutNormalSetting(requestManager, btn_goto_home, "공유모드_홈으로");
            text_guide_changeable.setText("대여정보");
            requestManager.load(Data.Image.get("공유모드_안내")).into(img_guide_changeable_sub);
        }

        setAllOnCheckedChangeListener(btn_radio_find_load, btn_radio_freeze, btn_radio_guide_changeable, btn_radio_music);
        setAllOnClickListeners(btn_go_home, btn_radio_find_load, btn_radio_freeze, btn_radio_guide_changeable, btn_radio_music);
        this.view = view;
    }

    private void setAllOnCheckedChangeListener(RadioButton... radioButtons){
        for(RadioButton radioButton : radioButtons){
            radioButton.setOnCheckedChangeListener(this.onCheckedChangeListener);
        }
    }

    private void setAllOnClickListeners(Button... buttons){
        for(Button button : buttons){
            button.setOnClickListener(this.onClickListener);
        }
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener){
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void setColorString(String colorString){
        this.colorString = colorString;
        if(colorString == MainActivity.COLOR_ORANGE){
            this.modeString = "배송";
        }else if(colorString == MainActivity.COLOR_MINT){
            this.modeString = "헬스";
        }else if(colorString == MainActivity.COLOR_VIOLET){
            this.modeString = "공유";
        }

    }

    public String getColorString(){
        return this.colorString;
    }

    public String getModeString(){
        return modeString;
    }

    public void setTitleLeftImage(boolean toggle){
        Button btn_clickable = (Button) btn_title_left.findViewById(R.id.btn_clickable);
        btn_clickable.setTag(R.id.btn_clickable, null);
        if(toggle){
            new ButtonLayoutNormalSetting(requestManager, btn_title_left, "공통모드_처음화면");
            btn_clickable.setTag(R.id.btn_clickable, "go_to_login");
        }else{
            new ButtonLayoutNormalSetting(requestManager, btn_title_left, "모드선택왼쪽화살표");
            btn_clickable.setTag(R.id.btn_clickable, "return_back");
        }
        btn_clickable.setOnClickListener(this.onClickListener);
    }

    public void setTitleText(String text){
        text_title_center.setText(text);
    }

}
