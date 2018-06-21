package com.example.pawar.fastrescue_user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.PhotoItemDao;


public class NewsDetailFragment extends Fragment {

    PhotoItemDao dao;
    TextView tvNewsName;
    TextView tvNewsDetail;
    ImageView ivNews;

    public NewsDetailFragment() {
        super();
    }

    public static NewsDetailFragment newInstance(PhotoItemDao dao) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao",dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dao = getArguments().getParcelable("dao");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_detail, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        tvNewsName = (TextView) rootView.findViewById(R.id.tvnewsname);
        tvNewsDetail = (TextView) rootView.findViewById(R.id.tvnewsdetail);
        ivNews = (ImageView) rootView.findViewById(R.id.ivNews);


        tvNewsName.setText(dao.getNewsTopic());
        tvNewsDetail.setText(dao.getNewsDetail());
        Glide.with(NewsDetailFragment.this)
                .load("http://pohtecktung.welovepc.com/newproject/img/img_news/"+dao.getNewsFilename())
                .placeholder(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivNews);
        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
