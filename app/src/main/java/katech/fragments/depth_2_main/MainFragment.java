package katech.fragments.depth_2_main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.ParentFragment;
import katech.designprojecttablet.R;
import katech.fragments.depth_2_main.depth_2_parts.FirstMainFragment;
import katech.fragments.depth_3_main.FreezeFragment;
import katech.fragments.depth_3_main.MusicFragment;
import katech.fragments.depth_3_main.NavigationFragment;
import katech.fragments.depth_4_info.delivery.DeliveryFragment;
import katech.fragments.depth_4_info.health.HealthFragment;
import katech.fragments.depth_4_info.share.ShareFragment;
import katech.frame.Data;
import katech.frame.ViewDecorator;
import katech.frame.ViewDecoratorWithColor;
import katech.frame.ViewFreezeDecoratorWithColor;
import katech.frame.ViewMusicDecoratorWithColor;

public class MainFragment extends ParentFragment {

    public static String TAG = "MainFragment";

    public ViewDecoratorWithColor decorator = new ViewDecoratorWithColor();

    private FragmentManager fragmentManager;

    public boolean headLight = false;
    public boolean doorOpen = false;
    public boolean seatBelt = false;

    private RelativeLayout layout_inflated = null;

    private Button btn_login = null;
    private Button btn_return = null;

    public boolean isMain = true;

    /*private View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.equals(btn_login)){
                Log.d(TAG, "go_to_login_key2");
                //mainActivity.onBackPressed();
                mainActivity.replaceFragment(new LoginFragment(), LoginFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
            }else if(v.equals(btn_return)){
                Log.d(TAG, "return_back2");
                checkClear();
                //mainActivity.onBackPressed();
                decorator.setTitleLeftImage(true);
                decorator.setTitleText("SMARTCAR");
                replaceFragment(new FirstMainFragment(), FirstMainFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
                //mainActivity.setCancelListener(null);
            }
        }
    };
*/
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_clickable:
                    boolean flag = (v.getTag(R.id.btn_clickable) == null) ? false : true;
                    if(!flag) break;
                    if(v.getTag(R.id.btn_clickable).equals("press_home_key")){
                        //모든 열었던 모드들이 처음으로 되돌아 오는것.
                        initInflatedLayout();
                        checkClear();
                        Log.d(TAG, "press_home_key");
                        decorator.setTitleLeftImage(true);
                        decorator.setTitleText("SMARTCAR");
                        if(!isMain){
                            mainActivity.setCancelListener(null);
                            checkClear();
                            isMain = true;
                            decorator.setTitleLeftImage(true);
                            replaceFragment(new FirstMainFragment(), FirstMainFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
                            //mainActivity.onBackPressed();//테스트는 성공했음.
                            isMain = true;
                        }
                    }else if(v.getTag(R.id.btn_clickable).equals("go_to_login")){
                        Log.d(TAG, "go_to_login_key");
                        mainActivity.onBackPressed();
                    }else if(v.getTag(R.id.btn_clickable).equals("return_back")){
                        Log.d(TAG, "return_back");
                        checkClear();
                        decorator.setTitleLeftImage(true);
                        mainActivity.onBackPressed();
                        isMain = true;
                        decorator.setTitleText("SMARTCAR");

                    }else{
                        Log.d(TAG, "else");
                    }
                    break;
                case R.id.btn_radio_find_load:
                    initInflatedLayout();
                    decorator.setTitleLeftImage(false);
                    decorator.setTitleText("길찾기");
                    replaceFragment(new NavigationFragment(), NavigationFragment.TAG, MainActivity.SAVE_FRAGMENT);
//                    replaceFragment(new MusicFragment(), MusicFragment.TAG, MainActivity.SAVE_FRAGMENT); //이것만 문제가 있음...
                    break;
                case R.id.btn_radio_freeze:
                    //냉방 인플레이터
                    break;
                case R.id.btn_radio_guide_changeable:
                    //배송안내, 예약안내, 대여안내 중에 하나를 레이아웃
                    initInflatedLayout();
                    decorator.setTitleLeftImage(false);
                    if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
                        replaceFragment(new DeliveryFragment(), DeliveryFragment.TAG, MainActivity.SAVE_FRAGMENT);//배송안내
                        decorator.setTitleText("배송안내");
                    }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
                        replaceFragment(new HealthFragment(), HealthFragment.TAG, MainActivity.SAVE_FRAGMENT);//예약안내
                        decorator.setTitleText("예약안내");
                    }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
                        replaceFragment(new ShareFragment(), ShareFragment.TAG, MainActivity.SAVE_FRAGMENT);//대여안내
                        decorator.setTitleText("대여안내");
                    }

                    break;
                case R.id.btn_radio_music:
                    //음악 인플레이터 -> 음악 레이아웃
                    break;
            }

        }
    };

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            String modeString = decorator.getModeString();
            int id = buttonView.getId();
            if(isChecked){
                switch (id){
                    case R.id.btn_radio_find_load:
                        requestManager.load(Data.Image.get(modeString + "모드_길찾기_클릭")).into((ImageView) buttonView.getTag(id));
                        initInflatedLayout();
//                        replaceFragment(new FirstMainFragment(), FirstMainFragment.TAG);
                        break;
                    case R.id.btn_radio_freeze:
                        addView("freeze");
                        requestManager.load(Data.Image.get(modeString + "모드_냉난방_클릭")).into((ImageView) buttonView.getTag(id));
                        //인플레이터 교체
                        break;
                    case R.id.btn_radio_guide_changeable:
                        initInflatedLayout();
                        requestManager.load(Data.Image.get(modeString + "모드_안내_클릭")).into((ImageView) buttonView.getTag(id));
//                        replaceFragment(new FirstMainFragment(), FirstMainFragment.TAG);
                        break;
                    case R.id.btn_radio_music:
                        addView("music");
                        requestManager.load(Data.Image.get(modeString + "모드_음악_클릭")).into((ImageView) buttonView.getTag(id));
                        break;
                }
            }else {
                requestManager.load(R.drawable.background_none).into((ImageView) buttonView.getTag(id));
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "MainFragment onAttach");
        fragmentManager = getChildFragmentManager();
        //mainActivity.setCancelListener(null);
       /* mainActivity.setCancelListener(new MainActivity.CancelListener() {
            @Override
            public void callBack() {
                mainActivity.replaceFragment(new LoginFragment(), LoginFragment.TAG, MainActivity.SAVE_NO_FRAGMENT);
                mainActivity.setCancelListener(null);
            }
        });*/
    }

    public void replaceFragment(Fragment fragment, String tag, boolean backStackInit){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("mode", mainActivity.nowMode);
        fragment.setArguments(bundle);
        if(backStackInit){
            fragmentTransaction.addToBackStack(null);
            //fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        fragmentTransaction.replace(R.id.fragment_main_container, fragment, tag).commit();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //decorator.setTitleLeftImage(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2_main, container, false);

        layout_inflated = (RelativeLayout) view.findViewById(R.id.layout_inflated);
        /*ButtonLayout btn_title_left_go_login = (ButtonLayout) view.findViewById(R.id.btn_title_left_go_login);
        ButtonLayout btn_title_left_return = (ButtonLayout) view.findViewById(R.id.btn_title_left_return);
        btn_login = (Button) btn_title_left_go_login.findViewById(R.id.btn_clickable);
        btn_return = (Button) btn_title_left_return.findViewById(R.id.btn_clickable);
        btn_login.setOnClickListener(onClickListener2);
        btn_return.setOnClickListener(onClickListener2);
        new ButtonLayoutNormalSetting(requestManager, btn_title_left_go_login, "공통모드_처음화면");
        new ButtonLayoutNormalSetting(requestManager, btn_title_left_return, "모드선택왼쪽화살표");*/

        decorator.setRequestManager(requestManager);
        decorator.setOnClickListener(onClickListener);
        decorator.setOnCheckedChangeListener(onCheckedChangeListener);

        if(mainActivity.nowMode == MainActivity.MODE_DELIVERY){
            decorator.setColorString(MainActivity.COLOR_ORANGE);
        }else if(mainActivity.nowMode == MainActivity.MODE_HEALTH){
            decorator.setColorString(MainActivity.COLOR_MINT);
        }else if(mainActivity.nowMode == MainActivity.MODE_SHARING){
            decorator.setColorString(MainActivity.COLOR_VIOLET);
        }

        replaceFragment(new FirstMainFragment(), FirstMainFragment.TAG, MainActivity.SAVE_NO_FRAGMENT); //보류

        decorator.setView(view);

        return decorator.getView();

    }

    public void addView(String input){
        initInflatedLayout();
        View view = null;
        ViewDecorator viewDecorator = null;
        if(input == "music"){ //음악 레이아웃의 기능 정의
            view = View.inflate(getActivity(), R.layout.layout_inflate_music, null);
            viewDecorator = new ViewMusicDecoratorWithColor(getActivity(), mainActivity.nowMode);
            viewDecorator.setOnclickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initInflatedLayout();
                    decorator.setTitleLeftImage(false);
                    decorator.setTitleText("음악");
                    replaceFragment(new MusicFragment(), MusicFragment.TAG, MainActivity.SAVE_FRAGMENT);
                }
            });
            viewDecorator.setView(view);
        }else if(input == "freeze"){//냉방 레이아웃의 기능 정의
            view = View.inflate(getActivity(), R.layout.layout_inflate_freeze, null);
            viewDecorator = new ViewFreezeDecoratorWithColor(getActivity(), mainActivity.nowMode);
            viewDecorator.setOnclickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initInflatedLayout();
                    decorator.setTitleLeftImage(false);
                    decorator.setTitleText("냉난방");
                    replaceFragment(new FreezeFragment(), FreezeFragment.TAG, MainActivity.SAVE_FRAGMENT);
                }
            });
            viewDecorator.setView(view);
        }
        ImageView img_inflater_delete = (ImageView) viewDecorator.getView().findViewById(R.id.img_inflater_delete);
        RelativeLayout layout_inflater_delete = (RelativeLayout) viewDecorator.getView().findViewById(R.id.layout_inflater_delete);
        requestManager.load(Data.Image.get("아래화살표")).into(img_inflater_delete);
        layout_inflater_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initInflatedLayout();
                checkClear();
            }
        });

        layout_inflated.addView(viewDecorator.getView());
    }

    public void initInflatedLayout(){
        if(layout_inflated.getChildCount() > 0){
            layout_inflated.removeAllViews();
        }
    }

    public void checkClear(){
        decorator.radio_group_bottom.clearCheck();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "MainFragment onDetach");
        super.onDetach();
        //mainActivity.setCancelListener(null);
    }

/*    public void setTitleLeftImage(boolean toggle){
        if(toggle){
            btn_login.setVisibility(View.VISIBLE);
            btn_return.setVisibility(View.GONE);
        }else{
            btn_login.setVisibility(View.GONE);
            btn_return.setVisibility(View.VISIBLE);
        }

    }*/

}
