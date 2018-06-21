package com.example.pawar.fastrescue_user.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.activity.EmerPhotoActivity;
import com.example.pawar.fastrescue_user.dao.StatusItemDao;


public class StatusListDetailFragment extends Fragment {
    TextView tvstatusname;
    TextView tvstatusdetail;
    TextView tvstatus;
    ImageView ivstatus;
    String url, username;
    ProgressDialog progress;
    StatusItemDao dao;
    Button btphoto;

    public StatusListDetailFragment() {
        super();
    }

    public static StatusListDetailFragment newInstance(StatusItemDao dao) {
        StatusListDetailFragment fragment = new StatusListDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_status_detail, container, false);
        initInstances(rootView);
        setData();
        return rootView;
    }

    private void initInstances(View rootView) {
        tvstatusname = (TextView) rootView.findViewById(R.id.tvstatusname);
        tvstatusdetail = (TextView) rootView.findViewById(R.id.tvstatusdetail);
        tvstatus = (TextView) rootView.findViewById(R.id.tvstatus);
        ivstatus = (ImageView) rootView.findViewById(R.id.ivstatus);
        btphoto = (Button) rootView.findViewById(R.id.btphoto);
        btphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmerPhotoActivity.class);
                intent.putExtra("noti_id", dao.getNotiId());
                startActivity(intent);
            }
        });

        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = getArguments().getParcelable("dao");
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


    public void setData() {
        tvstatusname.setText(dao.getNotiEvent());
        tvstatusdetail.setText(dao.getNotiDetail());
        tvstatus.setText("สถานะแจ้งเหตุ : "+dao.getNotiStatus());
        if (dao.getNotiStatus().equals("ทำการตรวจสอบ")) {
            tvstatus.setTextColor(Color.parseColor("#F44336"));
        } else if (dao.getNotiStatus().equals("ตรวจสอบข้อมูลการแจ้ง")) {
            tvstatus.setTextColor(Color.parseColor("#FF9800"));
        } else if (dao.getNotiStatus().equals("กำลังดำเนินการเข้าช่วยเหลือ")) {
            tvstatus.setTextColor(Color.parseColor("#FFEB3B"));
        } else if (dao.getNotiStatus().equals("เข้าช่วยเหลือเสร็จสิ้น")) {
            tvstatus.setTextColor(Color.parseColor("#4CAF50"));
        }
        url = dao.getNotiFilename();
        Glide
                .with(getActivity())
                .load("http://pohtecktung.welovepc.com/newproject/img/img_notification/" + url)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(ivstatus);
    }


}


