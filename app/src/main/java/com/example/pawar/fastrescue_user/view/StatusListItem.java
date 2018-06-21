package com.example.pawar.fastrescue_user.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.view.state.BundleSavedState;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class StatusListItem extends BaseCustomViewGroup {
    ImageView statusPic;
    TextView statusName;
    TextView statusDetail;
    TextView statusStatus;

    public StatusListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public StatusListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public StatusListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public StatusListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_status, this);
    }

    private void initInstances() {
        statusPic = (ImageView) findViewById(R.id.statusPic);
        statusName = (TextView) findViewById(R.id.statusName);
        statusDetail = (TextView) findViewById(R.id.statusDetail);
        statusStatus = (TextView) findViewById(R.id.statusStatus);

        // findViewById here
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    public void setStatusName(String text) {
        statusName.setText(text);
    }

    public void setStatusDetail(String text) {
        statusDetail.setText(text);
    }

    public void setStatusStatus(String text) {
        statusStatus.setText(text);

        if (text.equals("ทำการตรวจสอบ")) {
            statusStatus.setTextColor(Color.parseColor("#F44336"));
        } else if (text.equals("ตรวจสอบข้อมูลการแจ้ง")) {
            statusStatus.setTextColor(Color.parseColor("#FF9800"));
        } else if (text.equals("กำลังดำเนินการเข้าช่วยเหลือ")) {
            statusStatus.setTextColor(Color.parseColor("#FFEB3B"));
        } else if (text.equals("เข้าช่วยเหลือเสร็จสิ้น")) {
            statusStatus.setTextColor(Color.parseColor("#4CAF50"));
        }

    }

    public void setStatusURL(String url) {
        Glide.with(getContext())
                .load("http://pohtecktung.welovepc.com/newproject/img/img_notification/" + url)
                .placeholder(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(statusPic);
    }
}
