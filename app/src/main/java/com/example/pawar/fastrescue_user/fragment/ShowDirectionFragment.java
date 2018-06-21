package com.example.pawar.fastrescue_user.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.constant.Unit;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.example.pawar.fastrescue_user.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


public class ShowDirectionFragment extends Fragment implements OnMapReadyCallback {
    String serverKey = "AIzaSyB57-3Ucs3MPGaD7cMkM-eoiaIJK9rtJF8";
    LatLng origin;
    LatLng destination;
    GoogleMap mMap;
    Double noti_latitude = 0.0;
    Double noti_longitude =0.0;
    Double off_latitude = 0.0;
    Double off_longitude =0.0;


    public ShowDirectionFragment() {
        super();
    }

    public static ShowDirectionFragment newInstance() {
        ShowDirectionFragment fragment = new ShowDirectionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_show_direct, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        getOffciallatlng();
        mapFragment.getMapAsync(this);
        initInstances(rootView);



        return rootView;
    }

    private void initInstances(View rootView) {


        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        GoogleDirection.withServerKey(serverKey)
                .from(origin)
                .to(destination)
                .transitMode(TransportMode.DRIVING)
                .unit(Unit.METRIC)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        if (direction.isOK()) {
                            mMap.addMarker(new MarkerOptions().position(origin).title("ที่เอยู่ปัจจุบัน")).showInfoWindow();

                            mMap.addMarker(new MarkerOptions().position(destination).title("ที่เกิดเหตุ")).showInfoWindow();


                            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
                            PolylineOptions polylineOptions = DirectionConverter.createPolyline(getActivity(), directionPositionList, 5, Color.BLUE);
                            mMap.addPolyline(polylineOptions);

                            boolean hasPoints = false;
                            Double maxLat = null, minLat = null, minLon = null, maxLon = null;

                            if (polylineOptions != null && polylineOptions.getPoints() != null) {
                                List<LatLng> pts = polylineOptions.getPoints();
                                for (LatLng coordinate : pts) {
                                    // Find out the maximum and minimum latitudes & longitudes
                                    // Latitude
                                    maxLat = maxLat != null ? Math.max(coordinate.latitude, maxLat) : coordinate.latitude;
                                    minLat = minLat != null ? Math.min(coordinate.latitude, minLat) : coordinate.latitude;

                                    // Longitude
                                    maxLon = maxLon != null ? Math.max(coordinate.longitude, maxLon) : coordinate.longitude;
                                    minLon = minLon != null ? Math.min(coordinate.longitude, minLon) : coordinate.longitude;

                                    hasPoints = true;
                                }
                            }

                            if (hasPoints) {
                                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                                builder.include(new LatLng(maxLat, maxLon));
                                builder.include(new LatLng(minLat, minLon));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 48));
                            }
                            // Do something
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void getOffciallatlng() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        off_latitude = Double.valueOf(sharedPreferences.getString("lat", null));
        off_longitude = Double.valueOf(sharedPreferences.getString("lng", null));
        origin = new LatLng(off_latitude, off_longitude);
        SharedPreferences sp = getActivity().getSharedPreferences("RECEIVE_EMER", Context.MODE_PRIVATE);
        noti_latitude = Double.valueOf(sp.getString("noti_latitude", null));
        noti_longitude = Double.valueOf(sp.getString("noti_longtitude", null));
        destination = new LatLng(noti_latitude, noti_longitude);

    }


}

