package com.example.pawar.fastrescue_user.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.pawar.fastrescue_user.R;

import java.util.HashMap;
import java.util.Map;


public class ReceiveEmergencyFragment extends Fragment {
    final String URL = "http://pohtecktung.welovepc.com/finalproject/android/success.php";
    String url;
    Button btfinnish;
    TextView tv_event;
    TextView tv_detail;
    ImageView re_emer_image;

    public ReceiveEmergencyFragment() {
        super();
    }

    public static ReceiveEmergencyFragment newInstance() {
        ReceiveEmergencyFragment fragment = new ReceiveEmergencyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_receive_emergency, container, false);
        initInstances(rootView);
        setData();
        return rootView;
    }

    private void initInstances(View rootView) {
        tv_event = (TextView) rootView.findViewById(R.id.tv_event);
        tv_detail = (TextView) rootView.findViewById(R.id.tv_detail);
        re_emer_image = (ImageView) rootView.findViewById(R.id.re_emer_image);
        btfinnish = (Button) rootView.findViewById(R.id.btfinnish);
        btfinnish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendfinnish();
                clearTempReceive();
                getActivity().finish();
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

    public void clearTempReceive() {
        SharedPreferences sp = getActivity().getSharedPreferences("RECEIVE_EMER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putBoolean("recieve", false);
        editor.commit();
    }

    public  void setData(){
        SharedPreferences sp = getActivity().getSharedPreferences("RECEIVE_EMER", Context.MODE_PRIVATE);
        tv_event.setText(sp.getString("noti_event",null));
        tv_detail.setText(sp.getString("noti_detail",null));
        url = sp.getString("noti_filename",null);
        Glide
                .with(getActivity())
                .load("http://pohtecktung.welovepc.com/finalproject/img/img_notification/"+url)
                .centerCrop()
                .placeholder(R.drawable.addphoto)
                .into(re_emer_image);
    }

    public void sendfinnish() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("onResponse", response);
                //Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onError", error.toString());
                //Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("noti_filename", url);



                return params;
            }
        };
        requestQueue.add(request);
    }
}
