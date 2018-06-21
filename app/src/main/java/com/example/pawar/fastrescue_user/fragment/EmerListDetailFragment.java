package com.example.pawar.fastrescue_user.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.EmerItemDao;

import java.util.HashMap;
import java.util.Map;


public class EmerListDetailFragment extends Fragment {
    final String commit_URL = "http://pohtecktung.welovepc.com/finalproject/android/accept.php";
    final String cancal_URL = "http://pohtecktung.welovepc.com/finalproject/android/cancelrub.php";
    Button btcommitre;
    Button btcancalre;
    TextView tv_alert_event;
    TextView tv_alert_detail;
    ImageView alert_image;
    String url, username;
    ProgressDialog progress;
    EmerItemDao dao;

    public EmerListDetailFragment() {
        super();
    }

    public static EmerListDetailFragment newInstance(EmerItemDao dao) {
        EmerListDetailFragment fragment = new EmerListDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alert_detail, container, false);
        initInstances(rootView);
        setData();
        return rootView;
    }

    private void initInstances(View rootView) {
        tv_alert_event = (TextView) rootView.findViewById(R.id.tv_alert_event);
        tv_alert_detail = (TextView) rootView.findViewById(R.id.tv_alert_detail);
        alert_image = (ImageView) rootView.findViewById(R.id.alert_image);
        btcommitre = (Button) rootView.findViewById(R.id.btcommitre);
        btcommitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getActivity().getSharedPreferences("RECEIVE_EMER", Context.MODE_PRIVATE);
                Boolean check = sp.getBoolean("recieve", false);
                if (check == false) {
                    progress = ProgressDialog.show(getActivity(), "โปรดรอสักครู่",
                            "Loading...", true);
                    sendCommit();

                } else {
                    Toast.makeText(getActivity(), "มีเหตุที่รับอยู่แล้วกรุณายกเลิกหรือช่วยเหลือให้เสร็จก่อน", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btcancalre = (Button) rootView.findViewById(R.id.btcancalre);
        btcancalre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = ProgressDialog.show(getActivity(), "โปรดรอสักครู่",
                        "Loading...", true);
                sendCancal();
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
        tv_alert_event.setText(dao.getNotiEvent());
        tv_alert_detail.setText(dao.getNotiDetail());
        url = dao.getNotiFilename();
        Glide
                .with(getActivity())
                .load("http://pohtecktung.welovepc.com/finalproject/img/img_notification/" + url)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(alert_image);
    }

    public void sendCommit() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, commit_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("onResponse", response);
                getActivity().finish();
                emertofile();
                progress.dismiss();
                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onError", error.toString());
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("noti_filename", url);
                params.put("official_username", username);

                return params;
            }
        };
        requestQueue.add(request);
    }

    public void sendCancal() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, cancal_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("onResponse", response);
                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                progress.dismiss();
                getActivity().finish();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onError", error.toString());
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("noti_filename", url);
                params.put("official_username", username);

                return params;
            }
        };
        requestQueue.add(request);
    }



    public void emertofile() {
        SharedPreferences sp = getActivity().getSharedPreferences("RECEIVE_EMER", Context.MODE_PRIVATE);
        String check = sp.getString("noti_filename", null);
        if (check == null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("noti_latitude", dao.getNotiLatitude());
            editor.putString("noti_longtitude", dao.getNotiLongtitude());
            editor.putString("noti_event", dao.getNotiEvent());
            editor.putString("noti_detail", dao.getNotiDetail());
            editor.putString("noti_filename", dao.getNotiFilename());
            editor.putBoolean("recieve", true);
            editor.commit();
        }



    }
}

