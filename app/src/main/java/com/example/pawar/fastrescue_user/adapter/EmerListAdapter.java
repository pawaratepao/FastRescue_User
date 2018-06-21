package com.example.pawar.fastrescue_user.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.EmerCollectionDao;
import com.example.pawar.fastrescue_user.dao.EmerItemDao;
import com.example.pawar.fastrescue_user.view.EmerListItem;

/**
 * Created by pawar on 26-Jan-17.
 */

public class EmerListAdapter extends BaseAdapter {
    EmerCollectionDao dao;
    int lastPosition = -1;
    public void setDao(EmerCollectionDao dao) {
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
        EmerListItem item;
        if (convertView != null) {
            item = (EmerListItem) convertView;
        } else
            item = new EmerListItem(parent.getContext());
        EmerItemDao dao = (EmerItemDao) getItem(position);
        item.setEmerName(dao.getNotiEvent());
        item.setEmerDetail(dao.getNotiDetail());
        item.setEmerURL(dao.getNotiFilename());
        if(position > lastPosition) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(), R.anim.up_from_bottom);
            item.startAnimation(anim);
            lastPosition = position;
        }
        return item;
    }
}
