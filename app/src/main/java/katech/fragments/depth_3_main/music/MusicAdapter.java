package katech.fragments.depth_3_main.music;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.R;

/**
 * Created by bjkim on 2017-07-22.
 */
public class MusicAdapter extends BaseAdapter {

    private ArrayList<MusicItem> musicItemArrayList = null;
    private Context context;
    private RequestManager requestManager;
    private int nowMode;

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);

    public MusicAdapter(ArrayList<MusicItem> musicItemArrayList, Context context, RequestManager requestManager, int nowMode) {
        this.musicItemArrayList = musicItemArrayList;
        this.context = context;
        this.requestManager = requestManager;
        this.nowMode = nowMode;
    }

    @Override
    public int getCount() {
        return musicItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return musicItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Drawable d = null;
        MusicItem item = (MusicItem) getItem(position);
        if(this.nowMode == MainActivity.MODE_DELIVERY){
            d = context.getResources().getDrawable(R.drawable.deco_btn_38_orange);
        }else if(this.nowMode == MainActivity.MODE_HEALTH){
            d = context.getResources().getDrawable(R.drawable.deco_btn_38_mint);
        }else if(this.nowMode == MainActivity.MODE_SHARING){
            d = context.getResources().getDrawable(R.drawable.deco_btn_38_violet);
        }

        convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_music_item, null);
        RelativeLayout layout_item = (RelativeLayout) convertView.findViewById(R.id.layout_item);
        layout_item.setBackground(d);
        convertView.setLayoutParams(params);

        TextView text_num = (TextView) convertView.findViewById(R.id.text_num);
        TextView text_music_title = (TextView) convertView.findViewById(R.id.text_music_title);
        TextView text_music_singer = (TextView) convertView.findViewById(R.id.text_music_singer);

        if(position == 0){
            text_num.setTextColor(Color.parseColor("#ffffff"));
        }
        text_num.setText(item.getNum()+"");
        text_music_title.setText(item.getTitle());
        text_music_singer.setText(item.getSinger());

        return convertView;
    }
}
