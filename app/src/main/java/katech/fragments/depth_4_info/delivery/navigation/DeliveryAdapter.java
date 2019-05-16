package katech.fragments.depth_4_info.delivery.navigation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import katech.designprojecttablet.MainActivity;
import katech.designprojecttablet.R;
import katech.fragments.depth_3_main.navigation.NavigationItem;
import katech.frame.ButtonLayout;
import katech.frame.Data;
import katech.frame.ToggleButtonLayout;
import katech.frame.ToggleButtonLayoutNormalSetting;

/**
 * Created by bjkim on 2017-07-23.
 */
public class DeliveryAdapter extends BaseAdapter {


    private ArrayList<DeliveryPeopleItem> deliveryPeopleItems = null;
    private Context context;
    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);

    private ArrayList<ToggleButton> toggleButtons = new ArrayList<>();
    private ArrayList<ToggleButton> togglePhoneButtons = new ArrayList<>();
    private View.OnClickListener callButtonClickListener = null;
    private CompoundButton.OnCheckedChangeListener callButtonClickChangeListener = null;


    public DeliveryAdapter(ArrayList<DeliveryPeopleItem> deliveryPeopleItems, Context context) {
        this.deliveryPeopleItems = deliveryPeopleItems;
        this.context = context;
    }

    public void setCallButtonClickChangeListener(CompoundButton.OnCheckedChangeListener callButtonClickChangeListener){
        this.callButtonClickChangeListener = callButtonClickChangeListener;
    }

    public void setCallButtonClickListener(View.OnClickListener callButtonClickListener){
        this.callButtonClickListener = callButtonClickListener;
    }

    @Override
    public int getCount() {
        return deliveryPeopleItems.size();
    }

    @Override
    public Object getItem(int position) {
        return deliveryPeopleItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Drawable d = null;
        DeliveryPeopleItem item = (DeliveryPeopleItem) getItem(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_delivery_people_item, null);

        d = context.getResources().getDrawable(R.drawable.deco_btn_38_orange);

        ToggleButton toggle_layout_item = (ToggleButton) convertView.findViewById(R.id.toggle_layout_item);
        toggleButtons.add(position, toggle_layout_item);
        toggle_layout_item.setOnClickListener(new Toggle(position));
        toggle_layout_item.setBackground(d);
        convertView.setLayoutParams(params);

        TextView text_num = (TextView) convertView.findViewById(R.id.text_num);
        TextView text_navigation_title = (TextView) convertView.findViewById(R.id.text_navigation_title);
        TextView text_navigation_address = (TextView) convertView.findViewById(R.id.text_navigation_address);

        ButtonLayout btn_phone_call = (ButtonLayout) convertView.findViewById(R.id.btn_phone_call);
        /*ToggleButton btn_phone_call = (ToggleButton) toggle_btn_phone_call.findViewById(R.id.btn_clickable);
        togglePhoneButtons.add(position, btn_phone_call);
        toggle_btn_phone_call.setOnClickListener(new Toggle(position));
        new ToggleButtonLayoutNormalSetting(Glide.with(context), toggle_btn_phone_call, "배송모드_전화하기");*/

        text_num.setText(item.getNum());
        text_navigation_title.setText(item.getName() + " | " + item.getTime());
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
