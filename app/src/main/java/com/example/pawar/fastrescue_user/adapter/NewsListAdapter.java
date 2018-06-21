package com.example.pawar.fastrescue_user.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.PhotoCollectionDao;
import com.example.pawar.fastrescue_user.dao.PhotoItemDao;
import com.example.pawar.fastrescue_user.view.NewsListItem;

/**
 * Created by pawar on 26-Jan-17.
 */

public class NewsListAdapter extends BaseAdapter {
    PhotoCollectionDao dao;
    int lastPosition = -1;
    public void setDao(PhotoCollectionDao dao) {
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
        NewsListItem item;
        if (convertView != null) {
            item = (NewsListItem) convertView;
        } else
            item = new NewsListItem(parent.getContext());
        PhotoItemDao dao = (PhotoItemDao) getItem(position);
        item.setNewsName(dao.getNewsTopic());
        item.setNewsDetail(dao.getNewsDetail());
        item.setNewsURL(dao.getNewsFilename());
        if(position > lastPosition) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(), R.anim.up_from_bottom);
            item.startAnimation(anim);
            lastPosition = position;
        }
        return item;
    }
}
