package com.example.pawar.fastrescue_user.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.pawar.fastrescue_user.R;

/**
 * Created by pawar on 26-Jan-17.
 */

public class CustomViewGroup extends FrameLayout {
    public CustomViewGroup(Context context) {
        super(context);
        initInflate();
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
    }

    public void initInflate(){
        inflate(getContext(), R.layout.list_item_news,this);
    }

}
