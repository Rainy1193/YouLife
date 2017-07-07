package rain.endless.youlife.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import rain.endless.youlife.R;
import rain.endless.youlife.utils.FontManager;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {


    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        typedArray.recycle();
        init();
    }

    public void init() {
        setTypeface(FontManager.getTypeface(getContext()));
    }
}
