package katech.fragments.depth_4_info.delivery;

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
import android.widget.Button;
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
import katech.fragments.depth_4_info.delivery.navigation.DialogBlank;
import katech.fragments.depth_4_info.delivery.navigation.DialogDeliveryNavigation;
import katech.fragments.depth_4_info.delivery.navigation.DialogDeliverySelect;
import katech.fragments.depth_4_info.delivery.navigation.DialogWarning;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.Data;
import katech.frame.DesignTextView;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;

public class DeliveryFragment extends ParentFragment {

    public static String TAG = "DeliveryFragment";
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

    private boolean soundToggle = false;

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

    private MainActivity.CancelListener cancelListenerWeb = new MainActivity.CancelListener() {
        @Override
        public void callBack() {
            webView.loadUrl(defaultLocation + "delivery/delivery_flow_01_list.html");
            mainActivity.setCancelListener(cancelListener);
        }
    };

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

        View view = inflater.inflate(R.layout.fragment_4_delivery, container, false);

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

        text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_ORANGE));
        new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "배송모드_배터리");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "배송모드_좌향등");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "배송모드_우향등");

        edit_write_message = (EditText) view.findViewById(R.id.edit_write_message);

        webView = (WebView) view.findViewById(R.id.web_parent);
        webView.loadUrl(defaultLocation + "delivery/delivery_flow_01_list.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new AndroidBridge(), "Bridge");
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.d("Bridge", cm.message() + " -- From line "
                        + cm.lineNumber() + " of "
                        + cm.sourceId());
                return true;
            }
        });

        img_sound_toggle = (ImageView) view.findViewById(R.id.img_sound_toggle);
        requestManager.load(Data.Image.get("배송모드_배터리_사운드키_클릭")).into(img_sound_toggle);

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

        @JavascriptInterface
        public void setMessage(final String arg) { // must be final
            handler.post(new Runnable() {
                public void run() {
                    soundSetVisibleToggle(false);
                    mainActivity.setCancelListener(cancelListener);
                    edit_write_message.setVisibility(View.GONE);

                    if (arg.equals("call")) {
                        webView.loadUrl(defaultLocation + "delivery/delivery_flow_01_call.html");
                    } else if (arg.equals("list_item")) {
                        webView.loadUrl(defaultLocation + "delivery/delivery_flow_01_navigation_ready.html");
                        timeFlag = false;
                    } else if (arg.equals("navigate")) {
                        webView.loadUrl(defaultLocation + "delivery/delivery_flow_02_start_navigation.html"); //안내 버튼 - 안내를 시작한다.
                        //이때 캔슬리스너를 새로 심어야 할것 같다.
                        mainActivity.setCancelListener(cancelListenerWeb);
                    } else if (arg.equals("call_quit")) {
                        webView.loadUrl(defaultLocation + "delivery/delivery_flow_01_call_quit.html");
                    } else if (arg.equals("dialog1")) {
                        dialogDeliveryNavigation = new DialogDeliveryNavigation(getActivity());
                        dialogDeliveryNavigation.setLayoutResource(R.layout.layout_delivery_dialog_navigation);
                        dialogDeliveryNavigation.setMode(MainActivity.MODE_DELIVERY);
                        dialogDeliveryNavigation.show();
                        requestManager.load(Data.Image.get("배송모드_네비게이션_안내창")).into(dialogDeliveryNavigation.img_dialog_info_title_image);
                        dialogDeliveryNavigation.setOnClickListenerToOkButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogDeliveryNavigation.cancel();
                                webView.loadUrl("javascript:navigate();");
                            }
                        });
                        dialogDeliveryNavigation.setOnClickListenerToCancelButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogDeliveryNavigation.cancel();
                                webView.loadUrl("javascript:resetButton();");
                            }
                        });
                    } else if (arg.equals("sound")) {
                        soundSetVisibleToggle(true);
                    } else if (arg.equals("navigation_end")) {
                        dialogDeliveryNavigation = new DialogDeliveryNavigation(getActivity());
                        dialogDeliveryNavigation.setLayoutResource(R.layout.layout_delivery_dialog_navigation);
                        dialogDeliveryNavigation.setMode(MainActivity.MODE_DELIVERY);
                        dialogDeliveryNavigation.show();
                        dialogDeliveryNavigation.btn_ok.setText("다음 배송지");
                        dialogDeliveryNavigation.btn_cancel.setText("안내 종료");
                        requestManager.load(Data.Image.get("배송모드_네비게이션_완료_안내창")).into(dialogDeliveryNavigation.img_dialog_info_title_image);
                        dialogDeliveryNavigation.setOnClickListenerToOkButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogDeliveryNavigation.cancel();
                                webView.loadUrl("javascript:sendMessage('list_item');");

                                //timeFlag
                            }
                        });
                        dialogDeliveryNavigation.setOnClickListenerToCancelButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogDeliveryNavigation.cancel();
                                openSelectDialog();
                            }
                        });

                    } else if (arg.equals("nowrite")) {
                        webView.loadUrl(defaultLocation + "delivery/delivery_flow_03_message_nowrite.html");
                        nowMode = modeNoWrite;
                    } else if (arg.equals("write1")) {
                        if (timeFlag) {
                            webView.loadUrl(defaultLocation + "delivery/delivery_flow_02_message_write.html");
                        } else {
                            DialogWarning dialogDeliveryWarning = new DialogWarning(getActivity());
                            dialogDeliveryWarning.setLayoutResource(R.layout.layout_dialog_warning);
                            dialogDeliveryWarning.setMode(mainActivity.nowMode);
                            dialogDeliveryWarning.show();
                            requestManager.load(Data.Image.get("배송모드_메시지_경고_배경")).into(dialogDeliveryWarning.img_dialog_info_title_image);
                        }

                    } else if (arg.equals("write2")) {
                        webView.loadUrl(defaultLocation + "delivery/delivery_flow_03_message_write.html");
                        nowMode = modeWrite;
                        edit_write_message.setVisibility(View.VISIBLE);
                        mainActivity.setCancelListener(new MainActivity.CancelListener() {
                            @Override
                            public void callBack() {
                                webView.loadUrl(defaultLocation + "delivery/delivery_flow_02_message_write.html");
                                mainActivity.setCancelListener(cancelListener);
                            }
                        });
                    } else if (arg.equals("dialog2")) {
                        dialogDeliveryNavigation = new DialogDeliveryNavigation(getActivity());
                        dialogDeliveryNavigation.setLayoutResource(R.layout.layout_delivery_dialog_navigation);
                        dialogDeliveryNavigation.setMode(MainActivity.MODE_DELIVERY);
                        dialogDeliveryNavigation.show();
                        requestManager.load(Data.Image.get("배송모드_메시지_전송_확인_배경")).into(dialogDeliveryNavigation.img_dialog_info_title_image);
                        if (nowMode == modeNoWrite) {
                            dialogDeliveryNavigation.setOnClickListenerToOkButton(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogDeliveryNavigation.cancel();
                                    final DialogBlank dialogDeliveryBlank = new DialogBlank(getActivity());
                                    dialogDeliveryBlank.setLayoutResource(R.layout.layout_dialog_blank);
                                    dialogDeliveryBlank.show();
                                    requestManager.load(Data.Image.get("배송모드_메시지_전송_완료_배경")).into(dialogDeliveryBlank.img_dialog_info_title_image);
                                    dialogDeliveryBlank.handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialogDeliveryBlank.cancel();
                                            mainActivity.onBackPressed();
                                        }
                                    }, 3000);
                                }
                            });
                        } else if (nowMode == modeWrite) {
                            dialogDeliveryNavigation.setOnClickListenerToOkButton(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogDeliveryNavigation.cancel();
                                    webView.loadUrl(defaultLocation + "delivery/delivery_flow_04_message_send_ok.html");
                                    final DialogBlank dialogDeliveryBlank = new DialogBlank(getActivity());
                                    dialogDeliveryBlank.setLayoutResource(R.layout.layout_dialog_blank);
                                    dialogDeliveryBlank.show();
                                    requestManager.load(Data.Image.get("배송모드_메시지_전송_완료_배경")).into(dialogDeliveryBlank.img_dialog_info_title_image);
                                    dialogDeliveryBlank.handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialogDeliveryBlank.cancel();
                                            mainActivity.onBackPressed();
                                        }
                                    }, 3000);
                                }
                            });
                        }

                        dialogDeliveryNavigation.setOnClickListenerToCancelButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogDeliveryNavigation.cancel();
                                webView.loadUrl("javascript:resetButton();");
                            }
                        });

                    } else if (arg.equals("messagetime")) {
                        timeFlag = true;
                    } else {

                    }

                    Log.d(TAG, "setMessage(" + arg + ")");//test ok;
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
                    Drawable progressDrawable = res.getDrawable(R.drawable.deco_seek_bar_effecter_orange);
                    Drawable thumb = res.getDrawable(Data.Image.get("배송모드_돌기"));

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

    private void openSelectDialog(){
        final DialogDeliverySelect dialogDeliverySelect = new DialogDeliverySelect(getActivity());
        dialogDeliverySelect.show();
        new ButtonLayoutNormalSetting(requestManager, dialogDeliverySelect.btn_complete, "배송모드_큰버튼_배송완료");
        new ButtonLayoutNormalSetting(requestManager, dialogDeliverySelect.btn_call, "배송모드_큰버튼_전화");
        new ButtonLayoutNormalSetting(requestManager, dialogDeliverySelect.btn_message, "배송모드_큰버튼_메시지");

        ((Button)dialogDeliverySelect.btn_complete.findViewById(R.id.btn_clickable)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDeliverySelect.cancel();
                /*webView.loadUrl("javascript:mapInit();");
                webView.loadUrl("javascript:flagChange();");*/
                mainActivity.onBackPressed();
            }
        });

        ((Button)dialogDeliverySelect.btn_call.findViewById(R.id.btn_clickable)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDeliverySelect.cancel();
                //webView.loadUrl("javascript:mapInit();");//전화 사이트 접속
                webView.loadUrl(defaultLocation + "delivery/delivery_flow_03_navigation_end_phone_call.html");
                mainActivity.setCancelListener(cancelListenerWeb);
            }
        });

        ((Button)dialogDeliverySelect.btn_message.findViewById(R.id.btn_clickable)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDeliverySelect.cancel();
                //soundSetVisibleToggle(true);
                webView.loadUrl(defaultLocation + "delivery/delivery_flow_03_message_list.html");
            }
        });

    }

}