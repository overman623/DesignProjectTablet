package katech.frame;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by bjkim on 2017-07-29.
 */
public class TextChangedListener implements TextWatcher {

    private GetChanged getChanged;
    private boolean changeToggle = false;

    public void setChangedListener(GetChanged getChanged){
        this.getChanged = getChanged;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        if(s.toString().equals("")){
            changeToggle = false;
        }else{

        }

        if(changeToggle){
            getChanged.getMessage(s, start, count, after);
            changeToggle = false;
        }

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface GetChanged{
        void getMessage(CharSequence s, int start, int count, int after);
    }

}
