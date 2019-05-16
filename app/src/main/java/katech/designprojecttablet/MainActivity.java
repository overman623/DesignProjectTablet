package katech.designprojecttablet;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import katech.fragments.depth_0_start.SelectModeFragment;
import katech.frame.Data;

public class MainActivity extends Activity {

    public static String TAG = "MainActivity";

    public static final int MODE_DELIVERY = 0;
    public static final int MODE_HEALTH = 1;
    public static final int MODE_SHARING = 2;

    private CancelListener cancelListener = null;
    public int nowMode = MODE_DELIVERY;

    public FragmentManager fragmentManager;

    public static final boolean SAVE_FRAGMENT = true;
    public static final boolean SAVE_NO_FRAGMENT = false;

    public final static String COLOR_ORANGE = "#FEAF53";
    public final static String COLOR_MINT = "#34D9C9";
    public final static String COLOR_VIOLET = "#A1A5F5";

    public boolean navigating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(getApplicationContext(), IntroActivity.class));

        setContentView(R.layout.activity_main);
        Data data = new Data();
        fragmentManager = this.getFragmentManager();

        ImageView img_background = (ImageView) findViewById(R.id.img_background);
        Glide.with(this).load(R.drawable.t_intro_background).into(img_background);

        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new SelectModeFragment(), SelectModeFragment.TAG).commit();

        //replaceFragment(new StartFragment(), StartFragment.TAG, SAVE_NO_FRAGMENT);
       // replaceFragment(new SelectModeFragment(), SelectModeFragment.TAG, SAVE_FRAGMENT);

    }

    public void replaceFragment(Fragment fragment, String tag, boolean backStackInit){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(backStackInit){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag).commit();
    }

    public interface CancelListener{
        void callBack();
    }

    public void setCancelListener(CancelListener cancelListener){
        this.cancelListener = cancelListener;
    }

    public void goToMainFragment() {
        for(int i = 0; i < fragmentManager.getBackStackEntryCount(); i++){//현재 백스택에 몇개가 입력됬는지 보는거고
            fragmentManager.popBackStack(); //한칸 뒤로 가는것.
        }
    }

    @Override
    public void onBackPressed() {
        if(cancelListener != null){
            cancelListener.callBack();
            Log.d(TAG, "Cancel listener is not null");
        }else{
            Log.d(TAG, "Cancel listener is null");
            super.onBackPressed();
        }
    }

}
