package com.example.pawar.fastrescue_user.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.StatusCollectionDao;
import com.example.pawar.fastrescue_user.dao.StatusItemDao;
import com.example.pawar.fastrescue_user.view.StatusListItem;

/**
 * Created by pawar on 26-Jan-17.
 */

public class StatusListAdapter extends BaseAdapter {
    StatusCollectionDao dao;
    int lastPosition = -1;

    public void setDao(StatusCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 0;
        if (dao.getData() == null)
            return 0;

        return dao.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return dao.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StatusListItem item;
        if (convertView != null) {
            item = (StatusListItem) convertView;
        } else
            item = new StatusListItem(parent.getContext());
        StatusItemDao dao = (StatusItemDao) getItem(position);
        item.setStatusName(dao.getNotiEvent());
        item.setStatusDetail(dao.getNotiDetail());
        item.setStatusStatus(dao.getNotiStatus());
        item.setStatusURL(dao.getNotiFilename());
        if (position > lastPosition) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(), R.anim.up_from_bottom);
            item.startAnimation(anim);
            lastPosition = position;
        }
        return item;
    }
}

