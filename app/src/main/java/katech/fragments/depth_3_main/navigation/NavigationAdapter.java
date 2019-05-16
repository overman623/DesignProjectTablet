package katech.fragments.depth_3_main.navigation;

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
import android.widget.ToggleButton;

import java.util.ArrayList;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.R;
import katech.fragments.depth_3_main.music.MusicItem;

/**
 * Created by bjkim on 2017-07-23.
 */
public class NavigationAdapter extends BaseAdapter {


    private ArrayList<NavigationItem> navigationItems = null;
    private Context context;
    private int nowMode;
    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);

    private ArrayList<ToggleButton> toggleButtons = new ArrayList<>();

    public NavigationAdapter(ArrayList<NavigationItem> navigationItems, Context context, int nowMode) {
        this.navigationItems = navigationItems;
        this.context = context;
        this.nowMode = nowMode;
    }

    @Override
    public int getCount() {
        return navigationItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navigationItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Drawable d = null;
        NavigationItem item = (NavigationItem) getItem(position);
        if(this.nowMode == MainActivity.MODE_DELIVERY){
            d = context.getResources().getDrawable(R.drawable.deco_btn_38_orange);
        }else if(this.nowMode == MainActivity.MODE_HEALTH){
            d = context.getResources().getDrawable(R.drawable.deco_btn_38_mint);
        }else if(this.nowMode == MainActivity.MODE_SHARING){
            d = context.getResources().getDrawable(R.drawable.deco_btn_38_violet);
        }

        convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_navigation_item, null);
        ToggleButton toggle_layout_item = (ToggleButton) convertView.findViewById(R.id.toggle_layout_item);
        toggleButtons.add(position, toggle_layout_item);
        toggle_layout_item.setOnClickListener(new Toggle(position));
        toggle_layout_item.setBackground(d);
        convertView.setLayoutParams(params);

        TextView text_navigation_title = (TextView) convertView.findViewById(R.id.text_navigation_title);
        TextView text_navigation_address = (TextView) convertView.findViewById(R.id.text_navigation_address);

        text_navigation_title.setText(item.getTitle());
        text_navigation_address.setText(item.getAddress());

        return convertView;
    }


    class Toggle implements View.OnClickListener {

        private int position;

        public Toggle(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            for(int i = 0; i < toggleButtons.size(); i++){
                if(i != this.position){
                    toggleButtons.get(i).setChecked(false);
                }
            }
        }
    }

}
