package katech.frame;

import com.bumptech.glide.RequestManager;

import katech.designprojecttablet.R;

public class ButtonLayoutNormalSetting {

    private RequestManager requestManager;
    private ButtonLayout buttonLayout = null;

    public ButtonLayoutNormalSetting(RequestManager requestManager, ButtonLayout buttonLayout, String imageString){
        this.requestManager = requestManager;
        this.buttonLayout = buttonLayout;
        setImageAndClickAction(this.buttonLayout, imageString);
    }

/*
    protected void setImageAndClickAction(ButtonLayout... buttonLayouts){
        for(ButtonLayout layout : buttonLayouts){
            switch (layout.getId()){
                case R.id.btn_title_left:
                    layout.setImage(requestManager, "타이틀왼쪽");
                    break;
                default:
                    break;
            }
            layout.setOnTouchListener(requestManager);
        }
    }
*/

    protected void setImageAndClickAction(ButtonLayout buttonLayout, String imageString){
        buttonLayout.setImage(requestManager, imageString);
        buttonLayout.setOnTouchListener(requestManager);
    }

}
