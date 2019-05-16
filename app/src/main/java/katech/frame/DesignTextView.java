package katech.frame;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import katech.designprojecttablet.R;

public class DesignTextView extends TextView {
    public DesignTextView(Context context) {
        super(context);
    }

    public DesignTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public DesignTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setCustomFont(context, attrs);
    }

    public DesignTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.DesignTextView);
        String customFont = a.getString(R.styleable.DesignTextView_customFont);
        setTypeface(Typefaces.get(ctx, customFont));
    }


}
