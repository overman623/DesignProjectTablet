package katech.designprojecttablet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by bjkim on 2017-07-20.
 */
public class IntroActivity extends Activity {
    private ImageView img_intro_sub;
    private Handler handler;

    private Context context;
    private RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        img_intro_sub = (ImageView) findViewById(R.id.img_intro_sub);
        ImageView img_intro_main = (ImageView) findViewById(R.id.img_intro_main);
        ImageView img_background = (ImageView) findViewById(R.id.img_background);

        ImageView img_intro_bottom = (ImageView) findViewById(R.id.img_intro_bottom);

        context = getApplicationContext();
        requestManager = Glide.with(context);
        requestManager.load(R.drawable.t_intro_background).into(img_background);
        requestManager.load(R.drawable.t_intro_smartmobility_icon).into(img_intro_main);
        requestManager.load(R.drawable.t_intro_loading_image1).into(img_intro_sub);
        requestManager.load(R.drawable.t_intro_smartmobility).into(img_intro_bottom);

        handler = new Handler(){
            int i = 0;
            @Override
            public void handleMessage(Message msg) {
                animate(i++);
            }
        };

        handler.sendEmptyMessageDelayed(0, 400);
        handler.sendEmptyMessageDelayed(0, 400);
        handler.sendEmptyMessageDelayed(0, 400);
        handler.sendEmptyMessageDelayed(0, 400);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.removeMessages(0);
                finish();
            }
        }, 3000);

    }

    private void animate(int num){
        if(num == 0){
            requestManager.load(R.drawable.t_intro_loading_image2).into(img_intro_sub);
        }else if(num == 1){
            requestManager.load(R.drawable.t_intro_loading_image3).into(img_intro_sub);
        }else if(num == 2){
            requestManager.load(R.drawable.t_intro_loading_image3).into(img_intro_sub);
        }else if(num == 3){
            requestManager.load(R.drawable.t_intro_loading_image4).into(img_intro_sub);
        }else if(num == 4){
            requestManager.load(R.drawable.t_intro_loading_image5).into(img_intro_sub);
        }
    }


}
