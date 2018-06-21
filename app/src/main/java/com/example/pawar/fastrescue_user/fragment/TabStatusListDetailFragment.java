package com.example.pawar.fastrescue_user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.StatusItemDao;
import com.example.pawar.fastrescue_user.view.SlidingTabLayout;


public class TabStatusListDetailFragment extends Fragment {
    ViewPager viewPager;
    SlidingTabLayout slidingTab;
    StatusItemDao dao;
    String username;

    public TabStatusListDetailFragment() {
        super();
    }

    public static TabStatusListDetailFragment newInstance(StatusItemDao dao) {
        TabStatusListDetailFragment fragment = new TabStatusListDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_status_list_detail, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return StatusListDetailFragment.newInstance(dao);
                    case 1:
                        if (dao.getNotiStatus().equals("ทำการตรวจสอบ") || dao.getNotiStatus().equals("ตรวจสอบข้อมูลการแจ้ง")) {
                            return ShowMapStatusListFragment.newInstance(dao);
                        } else {
                            return ShowDirectionStatusListFragment.newInstance(dao);
                        }
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "รายละเอียดเหตุ";
                    case 1:
                        return "แผนที่";
                    default:
                        return null;
                }
            }
        });
        slidingTab = (SlidingTabLayout) rootView.findViewById(R.id.slidingTab);
        slidingTab.setViewPager(viewPager);
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



}
