package com.example.pawar.fastrescue_user.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pawar.fastrescue_user.R;


public class HomeMemFragment extends Fragment {
    Button imageMemDetail;
    Button imageMemNews;
    Button imageMemEmer;
    Button btStatus;

    public interface HomeMemFragmentListener {
        void MemNewsClicked();

        void MemDetailClicked();

        void MemStatusClicked();

        void MemEmergencyClicked();
    }

    public HomeMemFragment() {
        super();
    }

    public static HomeMemFragment newInstance() {
        HomeMemFragment fragment = new HomeMemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_mem, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        imageMemNews = (Button) rootView.findViewById(R.id.imageMemNews);
        imageMemNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeMemFragmentListener listener = (HomeMemFragmentListener) getActivity();
                listener.MemNewsClicked();
            }
        });

        imageMemDetail = (Button) rootView.findViewById(R.id.imageMemDetail);
        imageMemDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeMemFragmentListener listener = (HomeMemFragmentListener) getActivity();
                listener.MemDetailClicked();
            }
        });

        btStatus = (Button) rootView.findViewById(R.id.btStatus);
        btStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeMemFragmentListener listener = (HomeMemFragmentListener) getActivity();
                listener.MemStatusClicked();
            }
        });

        imageMemEmer = (Button) rootView.findViewById(R.id.imageMemEmer);
        imageMemEmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeMemFragmentListener listener = (HomeMemFragmentListener) getActivity();
                listener.MemEmergencyClicked();
            }
        });
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
