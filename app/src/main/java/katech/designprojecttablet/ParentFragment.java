package katech.designprojecttablet;

import android.app.Fragment;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

public class ParentFragment extends Fragment {

    protected MainActivity mainActivity = null;
    protected RequestManager requestManager = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = ((MainActivity) context);
        requestManager = Glide.with(this);
    }


}
