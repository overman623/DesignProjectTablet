package katech.fragments.depth_4_info.delivery;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
import katech.fragments.depth_3_main.navigation.NavigationStartFragment;
import katech.fragments.depth_4_info.delivery.navigation.DeliveryAdapter;
import katech.fragments.depth_4_info.delivery.navigation.DeliveryPeopleItem;
import katech.frame.ButtonLayout;
import katech.frame.ButtonLayoutNormalSetting;
import katech.frame.Data;
import katech.frame.DesignTextView;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;

public class DeliveryFragmentTest extends ParentFragment {

    public static String TAG = "DeliveryFragmentTest";
    private MainFragment mainFragment;

    private ToggleButton toggle_btn_head_light;
    private ToggleButton toggle_btn_door_open;
    private ToggleButton toggle_btn_seat_belt;

    private RelativeLayout layout_navigation_left;//왼쪽 전체창
    private RelativeLayout layout_phone_call;//검색창 //패딩조절

    private LinearLayout layout_navigation_right;//오른쪽 창 //weight조절 1로 //내용 인플레이트 하기
    private LinearLayout layout_text_bottom;

    private ArrayList<DeliveryPeopleItem> deliveryPeopleItems = new ArrayList<>();

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
        deliveryReady();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_4_delivery_test1, container, false);

        DesignTextView text_speed = (DesignTextView) view.findViewById(R.id.text_speed);
        ButtonLayout btn_toggle_battery = (ButtonLayout) view.findViewById(R.id.btn_toggle_battery);
        ToggleButtonLayout toggle_btn_left_light = (ToggleButtonLayout)view.findViewById(R.id.toggle_btn_left_light);
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

        img_map = (ImageView) view.findViewById(R.id.img_map);
        requestManager.load(Data.Image.get("지도")).into(img_map);

        layout_navigation_left = (RelativeLayout) view.findViewById(R.id.layout_navigation_left);
        layout_phone_call = (RelativeLayout) view.findViewById(R.id.layout_phone_call);
        layout_navigation_right = (LinearLayout) view.findViewById(R.id.layout_navigation_right);

        layout_text_bottom = (LinearLayout) view.findViewById(R.id.layout_text_bottom);

        text_speed.setTextColor(Color.parseColor(MainActivity.COLOR_ORANGE));
        new ButtonLayoutNormalSetting(requestManager, btn_toggle_battery, "배송모드_배터리");
        new ToggleButtonLayoutNormalSetting(requestManager, toggle_btn_left_light, "배송모드_좌향등");

        return view;

    }

    private void deliveryOn(){
        if(layout_navigation_right.getChildCount() > 0){
            layout_navigation_right.removeAllViews();
        }
        mainActivity.setCancelListener(cancelListener);
        requestManager.load(Data.Image.get("지도")).into(img_map);
        layout_navigation_right.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, -1));
        layout_phone_call.setPadding(250, 0, 250, 0);
        layout_phone_call.setVisibility(View.GONE);
        layout_text_bottom.setVisibility(View.VISIBLE);
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void deliveryReady() {
        String mode = null;
        layout_navigation_right.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        View view = View.inflate(getContext(), R.layout.layout_inflate_delivery_people_list, null);
        deliveryPeopleItems.add(0, new DeliveryPeopleItem("1","김성은","12:00am", "서울 서초구 반포대로 179 서초경찰서"));
        deliveryPeopleItems.add(1, new DeliveryPeopleItem("2","김진경","15:30am", "서울 서초구 반포대로 179"));
        deliveryPeopleItems.add(2, new DeliveryPeopleItem("3","조지희","15:30am", "서울 서초구 반포대로30길 40"));
        deliveryPeopleItems.add(3, new DeliveryPeopleItem("4","이영롱","16:00am", "서울 서초구 효령로 297 서초지구대"));

        ListView list_navigation = (ListView) view.findViewById(R.id.list_navigation);
        DeliveryAdapter navigationAdapter = new DeliveryAdapter(deliveryPeopleItems, getContext());

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
        /*mainActivity.setCancelListener(new MainActivity.CancelListener() {
            @Override
            public void callBack() {
                navigationOff();
            }
        });*/
        layout_phone_call.setPadding(50, 0, 50, 0);
        layout_text_bottom.setVisibility(View.GONE);
    }


    private void loadList() {

    }

    private void removeList() {

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
