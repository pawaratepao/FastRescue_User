package com.example.pawar.fastrescue_user.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.EmerItemDao;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ShowMapListFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap mMap;
    EmerItemDao dao;
    Double latitude = 0.0;
    Double longitude =0.0;


    public ShowMapListFragment() {
        super();
    }

    public static ShowMapListFragment newInstance(EmerItemDao dao) {
        ShowMapListFragment fragment = new ShowMapListFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao",dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getEmerDetail();
        View rootView = inflater.inflate(R.layout.fragment_show_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        latitude = Double.valueOf(dao.getNotiLatitude());
        longitude = Double.valueOf(dao.getNotiLongtitude());

        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = getArguments().getParcelable("dao");
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

    public void getEmerDetail() {
        SharedPreferences sp = getActivity().getSharedPreferences("EMER_DETAIL", Context.MODE_PRIVATE);
        boolean chackfile = sp.getBoolean("file_status",false);
        if(chackfile != false){
            latitude = Double.valueOf(sp.getString("noti_latitude", null));
            longitude = Double.valueOf(sp.getString("noti_longtitude", null));
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng target = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(target).title("สถานที่เกิดเหตุ"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(target, 18));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


    }


}
