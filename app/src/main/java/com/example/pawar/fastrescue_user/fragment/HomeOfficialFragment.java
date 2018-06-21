package com.example.pawar.fastrescue_user.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pawar.fastrescue_user.R;


public class HomeOfficialFragment extends Fragment {
    Button imageOffDetail;
    Button imageOffNews;
    Button imageOffEmer;
    Button imageOffReceive;
    Button imageOffReEmer;
    Button btOffStatus;
    TextView notification_count;
    int count;

    public interface HomeOffFragmentListener {
        void OffNewsClicked();

        void OffDetailClicked();

        void OffStatusClicked();

        void OffEmergencyClicked();

        void OffReceiveClicked();

        void OffReEmerClicked();
    }

    public HomeOfficialFragment() {
        super();
    }

    public static HomeOfficialFragment newInstance() {
        HomeOfficialFragment fragment = new HomeOfficialFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_off, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        imageOffNews = (Button) rootView.findViewById(R.id.imageOffNews);

        imageOffReEmer = (Button) rootView.findViewById(R.id.imageReEmer);
        imageOffReEmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeOffFragmentListener listener = (HomeOffFragmentListener) getActivity();
                listener.OffReEmerClicked();
            }
        });
        imageOffNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeOffFragmentListener listener = (HomeOffFragmentListener) getActivity();
                listener.OffNewsClicked();
            }
        });

        imageOffDetail = (Button) rootView.findViewById(R.id.imageOffDetail);
        imageOffDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeOffFragmentListener listener = (HomeOffFragmentListener) getActivity();
                listener.OffDetailClicked();
            }
        });

        btOffStatus = (Button) rootView.findViewById(R.id.btOffStatus);
        btOffStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeOffFragmentListener listener = (HomeOffFragmentListener) getActivity();
                listener.OffStatusClicked();
            }
        });

        imageOffEmer = (Button) rootView.findViewById(R.id.imageOffEmer);
        imageOffEmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeOffFragmentListener listener = (HomeOffFragmentListener) getActivity();
                listener.OffEmergencyClicked();
            }
        });

        imageOffReceive = (Button) rootView.findViewById(R.id.imageOffReceive);
        imageOffReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeOffFragmentListener listener = (HomeOffFragmentListener) getActivity();
                listener.OffReceiveClicked();

            }
        });

        notification_count = (TextView) rootView.findViewById(R.id.notification_count);

        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onStart() {
        getCount();
        if (count == 0) {
            notification_count.setVisibility(View.INVISIBLE);
        } else {
            notification_count.setVisibility(View.VISIBLE);
            notification_count.setText(count + "");
        }
        super.onStart();
    }

    @Override
    public void onResume() {
        getCount();
        if (count == 0) {
            notification_count.setVisibility(View.INVISIBLE);
        } else {
            notification_count.setVisibility(View.VISIBLE);
            notification_count.setText(count + "");
        }
        super.onResume();
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

    public void getCount() {
        SharedPreferences sp = getActivity().getSharedPreferences("CHECK_NOTI", Context.MODE_PRIVATE);
        count = sp.getInt("count_noti", 0);
    }
}
