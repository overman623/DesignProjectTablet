package katech.fragments.depth_4_info.share;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_2_main.MainFragment;
import katech.fragments.depth_2_main.depth_2_parts.FirstMainFragment;
import katech.fragments.depth_4_info.delivery.navigation.DialogDeliveryNavigation;
import katech.fragments.depth_4_info.share.dialog.DialogShareReturn;
import katech.fragments.depth_4_info.share.dialog.DialogShareTimeAdd;
import katech.fragments.depth_4_info.share.dialog.DialogShareTimeConfirm;
import katech.fragments.depth_4_info.share.dialog.DialogShareTimeWarning;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.Data;
import katech.frame.DesignTextView;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;

public class ShareFragment extends ParentFragment {

    public static String TAG = "ShareFragment";
    private MainFragment mainFragment;

    private ToggleButton toggle_btn_head_light;
    private ToggleButton toggle_btn_door_open;
    private ToggleButton toggle_btn_seat_belt;

    private WebView webView;
    private String defaultLocation = "file:///android_asset/web/page/";

    private ImageView img_seek_bar_bg;
    private ImageView img_sound_toggle;
    private SeekBar seek_bar_sound;
    private EditText edit_write_message;


    private boolean timeFlag = false;

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

    private DialogDeliveryNavigation dialogDeliveryNavigation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainFragment = (MainFragment) getParentFragment();
        mainFragment.isMain = false;
        mainActivity.setCancelListener(cancelListener);
    }

    private MainActivity.CancelListener cancelListener = new MainActivity.CancelListener() {
        @Override
        public void callBack() {
            mainActivity.setCancelListener(null);
            mainFragment.checkClear();
            mainFragment.isMain = true;
            mainFragment.decorator.setTitleLeftImage(true);
            mainFragment.replaceFragment(new FirstMainFragment(), FirstMainFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
        }
    };

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

        View view = inflater.inflate(R.layout.fragment_4_share, container, false);

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

        text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_VIOLET));
        new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "공유모드_배터리");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "공유모드_좌향등");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "공유모드_우향등");

        edit_write_message = (EditText) view.findViewById(R.id.edit_write_message);


        webView = (WebView) view.findViewById(R.id.web_parent);
        webView.loadUrl(defaultLocation + "share/share_flow_01_main.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new AndroidBridge(), "Bridge");
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.d(TAG, cm.message() + " -- From line "
                        + cm.lineNumber() + " of "
                        + cm.sourceId());
                return true;
            }
        });

        img_sound_toggle = (ImageView) view.findViewById(R.id.img_sound_toggle);
        requestManager.load(Data.Image.get("공유모드_배터리_사운드키_클릭")).into(img_sound_toggle);

        seek_bar_sound = (SeekBar) view.findViewById(R.id.seek_bar_sound);

        img_seek_bar_bg = (ImageView) view.findViewById(R.id.img_seek_bar_bg);
        requestManager.load(Data.Image.get("네비게이션_사운드_배경")).into(img_seek_bar_bg);

        img_sound_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundSetVisibleToggle(false);
            }
        });


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

    private final Handler handler = new Handler();
    private class AndroidBridge {

        private int modeWrite = 1;
        private int modeNoWrite = 2;
        private int nowMode = 0;

        private DialogShareTimeAdd dialogShareTimeAdd;

        @JavascriptInterface
        public void setMessage(final String arg) { // must be final
            handler.post(new Runnable() {
                public void run() {
                    soundSetVisibleToggle(false);
                    //mainActivity.setCancelListener(cancelListener);
                    edit_write_message.setVisibility(View.GONE);

                    if (arg.equals("sound")) {
                        soundSetVisibleToggle(true);
                    } else if (arg.equals("search")) {
                        webView.loadUrl(defaultLocation + "health/health_flow_03_navigation_search_another_location.html");
                    } else if (arg.equals("address_list")) {
                        webView.loadUrl(defaultLocation + "health/health_flow_04_navigation_search_address_list.html");
                    } else if (arg.equals("input_message")) {
                        webView.loadUrl(defaultLocation + "health/health_flow_03_message_write_edit.html");
                        //메시지직접입력
                    } else if (arg.equals("messagetime")) {
                        timeFlag = true;
                    } else {


                    }

                    Log.d(TAG, "setMessage(" + arg + ")");//test ok;
                }
            });
        }

        @JavascriptInterface
        public void cancelButton(final String arg) { // must be final
            handler.post(new Runnable() {
                public void run() {

                    if(arg.equals("home")){
                        mainActivity.setCancelListener(cancelListener);
                    } else if(arg.equals("web_history_back")){
                        mainActivity.setCancelListener(new MainActivity.CancelListener() {
                            @Override
                            public void callBack() {
                                webView.loadUrl("javascript:back();");
                            }
                        });
                    }
                    Log.d(TAG, "cancelButton(" + arg + ")");//test ok;
                }
            });
        }

        @JavascriptInterface
        public void dialog(final String arg) { // must be final
            handler.post(new Runnable() {
                public void run() {
                    if (arg.equals("time_add")) {
                        dialogShareTimeAdd = new DialogShareTimeAdd(getActivity());
                        dialogShareTimeAdd.setLayoutResource(R.layout.layout_dialog_share_time_add);
                        dialogShareTimeAdd.show();
                        requestManager.load(Data.Image.get("공유모드_대여시간_추가_안내창")).into(dialogShareTimeAdd.img_dialog_info_title_image);
                        dialogShareTimeAdd.btn_ok.setText("확인");
                        dialogShareTimeAdd.btn_cancel.setText("취소");
                        dialogShareTimeAdd.btn_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogShareTimeAdd.cancel();
                                webView.loadUrl("javascript:changeImage('init');");
                            }

                        });
                        dialogShareTimeAdd.btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogShareTimeAdd.cancel(); //다음 페이지
                                if(dialogShareTimeAdd.text_add_time.getText().toString().equals("3:00")){
                                    webView.loadUrl("javascript:dialog('time_add_warning');");
                                }else{
                                    //다른 다이얼로그
                                    webView.loadUrl("javascript:dialog('time_add_confirm');");
                                }
                            }
                        });

                    } else if (arg.equals("info_return")) {
                        final DialogShareReturn dialogShareReturn = new DialogShareReturn(getActivity());
                        dialogShareReturn.setLayoutResource(R.layout.layout_dialog_share_return);
                        dialogShareReturn.show();
                        requestManager.load(Data.Image.get("공유모드_대여장소_안내창")).into(dialogShareReturn.img_dialog_info_title_image);
                        dialogShareReturn.btn_ok.setText("예");
                        dialogShareReturn.btn_cancel.setText("아니오");
                        dialogShareReturn.btn_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogShareReturn.cancel();
                                webView.loadUrl("javascript:changeImage('init');");
                            }

                        });
                        dialogShareReturn.btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogShareReturn.cancel(); //다음 페이지
                                webView.loadUrl("javascript:changeImage('init');");
                            }
                        });
                    } else if (arg.equals("time_add_warning")) {
                        final DialogShareTimeWarning dialogShareTimeWarning = new DialogShareTimeWarning(getActivity());
                        dialogShareTimeWarning.setLayoutResource(R.layout.layout_dialog_share_time_warning);
                        dialogShareTimeWarning.show();
                        requestManager.load(Data.Image.get("공유모드_대여시간_추가_경고_안내창")).into(dialogShareTimeWarning.img_dialog_info_title_image);
                        dialogShareTimeWarning.btn_ok.setText("다시 시간 설정");
                        dialogShareTimeWarning.btn_cancel.setText("취소");
                        dialogShareTimeWarning.btn_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogShareTimeWarning.cancel();
                                webView.loadUrl("javascript:changeImage('init');");
                            }

                        });
                        dialogShareTimeWarning.btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogShareTimeWarning.cancel(); //다음 페이지
                                webView.loadUrl("javascript:dialog('time_add');");
                            }
                        });
                    } else if (arg.equals("time_add_confirm")) {
                        final DialogShareTimeConfirm dialogShareTimeConfirm = new DialogShareTimeConfirm(getActivity());
                        dialogShareTimeConfirm.setLayoutResource(R.layout.layout_dialog_share_time_confirm);
                        dialogShareTimeConfirm.show();
                        requestManager.load(Data.Image.get("공유모드_대여시간_추가_확인_안내창")).into(dialogShareTimeConfirm.img_dialog_info_title_image);
                        dialogShareTimeConfirm.btn_ok.setText("예");
                        dialogShareTimeConfirm.btn_cancel.setText("아니오");
                        dialogShareTimeConfirm.btn_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogShareTimeConfirm.cancel();
                                webView.loadUrl("javascript:changeImage('init');");
                            }

                        });
                        dialogShareTimeConfirm.btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogShareTimeConfirm.cancel(); //다음 페이지
                                webView.loadUrl("javascript:changeImage('init');");
                                //webView.loadUrl("javascript:dialog('time_add');");
                            }
                        });
                    }

                Log.d(TAG,"dialog("+arg+")");//test ok;

            }
        });

        }

    }



    private void soundSetVisibleToggle(boolean toggle){
        if(toggle){
            seek_bar_sound.setVisibility(View.VISIBLE);
            img_sound_toggle.setVisibility(View.VISIBLE);
            img_seek_bar_bg.setVisibility(View.VISIBLE);

            ViewTreeObserver vto = seek_bar_sound.getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    if (getActivity() == null) return true;
                    Resources res = getActivity().getResources();
                    Drawable progressDrawable = res.getDrawable(R.drawable.deco_seek_bar_effecter_mint);
                    Drawable thumb = res.getDrawable(Data.Image.get("헬스모드_돌기"));

                    int h = (int) (seek_bar_sound.getMeasuredHeight() * 1.0);
                    int w = h;

                    Bitmap bmpOrg = ((BitmapDrawable) thumb).getBitmap();
                    Bitmap bmpScaled = Bitmap.createScaledBitmap(bmpOrg, w, h, true);
                    Drawable newThumb = new BitmapDrawable(res, bmpScaled);
                    newThumb.setBounds(0, 0, newThumb.getIntrinsicWidth(), newThumb.getIntrinsicHeight());
                    seek_bar_sound.setThumb(newThumb);
                    seek_bar_sound.setProgressDrawable(progressDrawable);
                    seek_bar_sound.getViewTreeObserver().removeOnDrawListener(null);

                    return true;

                }
            });
        }else{
            seek_bar_sound.setVisibility(View.INVISIBLE);
            img_sound_toggle.setVisibility(View.GONE);
            img_seek_bar_bg.setVisibility(View.GONE);
        }
    }
/*

    private void openSelectDialog(){
        final DialogDeliverySelect dialogDeliverySelect = new DialogDeliverySelect(getActivity());
        dialogDeliverySelect.show();
        new ButtonLayoutNormalSetting(requestManager, dialogDeliverySelect.btn_complete, "헬스모드_큰버튼_예약목록");
        new ButtonLayoutNormalSetting(requestManager, dialogDeliverySelect.btn_call, "헬스모드_큰버튼_전화");
        new ButtonLayoutNormalSetting(requestManager, dialogDeliverySelect.btn_message, "헬스모드_큰버튼_메시지");

        ((Button)dialogDeliverySelect.btn_complete.findViewById(R.id.btn_clickable)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDeliverySelect.cancel();
                webView.loadUrl(defaultLocation + "health/health_flow_01_list.html");
            }
        });

        ((Button)dialogDeliverySelect.btn_call.findViewById(R.id.btn_clickable)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDeliverySelect.cancel();
                //webView.loadUrl("javascript:mapInit();");//전화 사이트 접속
                webView.loadUrl(defaultLocation + "health/health_flow_03_navigation_end_phone_call.html");

            }
        });

        ((Button)dialogDeliverySelect.btn_message.findViewById(R.id.btn_clickable)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDeliverySelect.cancel();
                webView.loadUrl(defaultLocation + "health/health_flow_03_navigation_end_message_list.html");
            }
        });

    }
*/

}