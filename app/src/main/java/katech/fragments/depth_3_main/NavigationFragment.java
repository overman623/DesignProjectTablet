package katech.fragments.depth_3_main;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_2_main.MainFragment;
import katech.fragments.depth_2_main.depth_2_parts.FirstMainFragment;
import katech.fragments.depth_3_main.navigation.DialogNavigation;
import katech.fragments.depth_3_main.navigation.NavigationAdapter;
import katech.fragments.depth_3_main.navigation.NavigationItem;
import katech.fragments.depth_3_main.navigation.NavigationStartFragment;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.Data;
import katech.frame.DesignTextView;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;


public class NavigationFragment extends ParentFragment {

    public static String TAG = "NavigationFragment";
    private MainFragment mainFragment;

    private ToggleButton toggle_btn_head_light;
    private ToggleButton toggle_btn_door_open;
    private ToggleButton toggle_btn_seat_belt;

    private RelativeLayout layout_search_list;//검색리스트창 //패딩조절
    private RelativeLayout layout_navigation_left;//왼쪽 전체창
    private RelativeLayout layout_search;//검색창 //패딩조절

    private LinearLayout layout_navigation_right;//오른쪽 창 //weight조절 1로 //내용 인플레이트 하기

    private LinearLayout layout_text_bottom;

    private EditText edit_text_search;

    private ArrayList<NavigationItem> navigationItems = new ArrayList<>();

    private ImageView img_map;

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
        if(mainActivity.navigating){
            navigationReady();
            mainActivity.navigating = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3_navigation, container, false);

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

        ButtonLayout btn_search = (ButtonLayout) view.findViewById(R.id.btn_search);
        ImageView img_mic = (ImageView) view.findViewById(R.id.img_mic);
        requestManager.load(Data.Image.get("마이크")).into(img_mic);

        img_map = (ImageView) view.findViewById(R.id.img_map);
        requestManager.load(Data.Image.get("지도")).into(img_map);

        layout_navigation_left = (RelativeLayout) view.findViewById(R.id.layout_navigation_left);
        layout_search = (RelativeLayout) view.findViewById(R.id.layout_search);
        layout_navigation_right = (LinearLayout) view.findViewById(R.id.layout_navigation_right);
        layout_search_list = (RelativeLayout) view.findViewById(R.id.layout_search_list);

        layout_text_bottom = (LinearLayout) view.findViewById(R.id.layout_text_bottom);

        TextView text_address = (TextView) view.findViewById(R.id.text_address);
        text_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationReady();
            }
        });
        edit_text_search = (EditText) view.findViewById(R.id.edit_text_search);
        edit_text_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                /*if (cs.toString().length() == 0){
                    edit_text_search.setText(" ");
                }*/
                Log.d(TAG, cs.toString());
                if(cs.toString().equals("")){
                    removeList();
                }else{
                    //창 나타나기
                    loadList();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }

        });

        if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_ORANGE));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "배송모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "배송모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "배송모드_우향등");
            new ButtonLayoutNormalSetting(requestManager, btn_search, "배송모드_검색");
        }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_MINT));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "헬스모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "헬스모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "헬스모드_우향등");
            new ButtonLayoutNormalSetting(requestManager, btn_search, "헬스모드_검색");
        }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
            text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_VIOLET));
            new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "공유모드_배터리");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "공유모드_좌향등");
            new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_right_light, "공유모드_우향등");
            new ButtonLayoutNormalSetting(requestManager, btn_search, "공유모드_검색");
        }
        return view;

    }

    private void navigationOff(){
        if(layout_navigation_right.getChildCount() > 0){
            layout_navigation_right.removeAllViews();
        }
        mainActivity.setCancelListener(cancelListener);
        requestManager.load(Data.Image.get("지도")).into(img_map);
        layout_navigation_right.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, -1));
        layout_search.setPadding(100, 0, 100, 0);
        layout_search_list.setPadding(100, 0, 100, 0);
        layout_text_bottom.setVisibility(View.GONE);
    }

    private void navigationStart(){

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void navigationReady() {
        removeList();
        hideKeyboard();
        String mode = null;
        layout_navigation_right.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        View view = View.inflate(getContext(), R.layout.layout_inflate_navigation, null);

        navigationItems.add(0, new NavigationItem("서초경찰서", "서울 서초구 반포대로 179 서초경찰서"));
        navigationItems.add(1, new NavigationItem("서초경찰서미원봉사실", "서울 서초구 반포대로 179"));
        navigationItems.add(2, new NavigationItem("서초3파출소", "서울 서초구 반포대로30길 40"));
        navigationItems.add(3, new NavigationItem("서초파출소", "서울 서초구 효령로 297 서초지구대"));

        ListView list_navigation = (ListView) view.findViewById(R.id.list_navigation);
        NavigationAdapter navigationAdapter = new NavigationAdapter(navigationItems, getContext(), mainActivity.nowMode);
        list_navigation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //검색이 바뀔뿐이다.
            }
        });
        list_navigation.setAdapter(navigationAdapter);
        Button btn_start_navigation = (Button) view.findViewById(R.id.btn_start_navigation);
        if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
            btn_start_navigation.setBackgroundResource(R.drawable.deco_btn_orange);
            mode = "배송";
        }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
            btn_start_navigation.setBackgroundResource(R.drawable.deco_btn_mint);
            mode = "헬스";
        }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
            btn_start_navigation.setBackgroundResource(R.drawable.deco_btn_violet);
            mode = "공유";
        }

        requestManager.load(Data.Image.get("지도절반")).into(img_map);

        final int dialogBackground = Data.Image.get(mode + "모드_네비게이션_안내창");

        btn_start_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다이얼로그가 실행된다.
                final DialogNavigation dialogLayoutNavigation = new DialogNavigation(getActivity());
                dialogLayoutNavigation.setMode(mainActivity.nowMode);
                dialogLayoutNavigation.setLayoutResource(R.layout.layout_delivery_dialog_navigation);
                dialogLayoutNavigation.show();
                dialogLayoutNavigation.setOnClickListenerToOkButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //navigationOff();//네비게이션 크기가 다시 줄어드는게 아니라....
                        //프레그먼트가 아예 바뀌어 버린다.
                        mainFragment.replaceFragment(new NavigationStartFragment(), NavigationStartFragment.TAG, MainActivity.SAVE_FRAGMENT);
                        dialogLayoutNavigation.cancel();
                        //길안내가 시작됨.
                        mainActivity.navigating = true;
                    }
                });
                requestManager.load(dialogBackground).into(dialogLayoutNavigation.img_dialog_info_title_image);

            }
        });

        layout_navigation_right.addView(view);
        mainActivity.setCancelListener(new MainActivity.CancelListener() {
            @Override
            public void callBack() {
                navigationOff();
            }
        });
        layout_search.setPadding(20, 0, 20, 0);
        layout_search_list.setPadding(20, 0, 20, 0);
        layout_text_bottom.setVisibility(View.VISIBLE);
    }


    private void loadList() {
        layout_search_list.setVisibility(View.VISIBLE);
    }

    private void removeList() {
        layout_search_list.setVisibility(View.GONE);
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

    @TargetApi(Build.VERSION_CODES.M)
    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit_text_search.getWindowToken(), 0);
    }

}
