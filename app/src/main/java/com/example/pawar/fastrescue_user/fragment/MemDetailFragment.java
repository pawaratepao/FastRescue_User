package com.example.pawar.fastrescue_user.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pawar.fastrescue_user.R;


public class MemDetailFragment extends Fragment {
    Button btedit;
    TextView tv_name, tv_sex, tv_tel, tv_teletc, tv_personalid, tv_birthday, tv_address, tv_disease,
            tv_bloodgroup, tv_allergic, tv_security;


    public interface MemDetailFragmentListener {
        void MemEditClick();
    }

    public MemDetailFragment() {
        super();
    }

    public static MemDetailFragment newInstance() {
        MemDetailFragment fragment = new MemDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mem_detail, container, false);
        initInstances(rootView);
        setText();
        return rootView;
    }

    private void initInstances(View rootView) {
        tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        tv_sex = (TextView) rootView.findViewById(R.id.tv_sex);
        tv_tel = (TextView) rootView.findViewById(R.id.tv_tel);
        tv_teletc = (TextView) rootView.findViewById(R.id.tv_teletc);
        tv_personalid = (TextView) rootView.findViewById(R.id.tv_personalid);
        tv_birthday = (TextView) rootView.findViewById(R.id.tv_birthday);
        tv_address = (TextView) rootView.findViewById(R.id.tv_address);
        tv_disease = (TextView) rootView.findViewById(R.id.tv_disease);
        tv_bloodgroup = (TextView) rootView.findViewById(R.id.tv_bloodgroup);
        tv_allergic =(TextView) rootView.findViewById(R.id.tv_allergic);
        tv_security = (TextView) rootView.findViewById(R.id.tv_security);

        btedit = (Button) rootView.findViewById(R.id.btedit);
        btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemDetailFragmentListener listener = (MemDetailFragmentListener) getActivity();
                listener.MemEditClick();
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

    @Override
    public void onResume() {
        setText();
        super.onResume();
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

    public void setText(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("PREF_Login", getContext().MODE_PRIVATE);
        tv_name.setText("ชื่อ : "+sharedPreferences.getString("firstname", null)+" "+sharedPreferences.getString("lastname" , null));
        tv_sex.setText("เพศ : "+sharedPreferences.getString("sex",null));
        tv_tel.setText("เบอร์ติดต่อ : "+sharedPreferences.getString("tel",null));
        tv_teletc.setText("เบอร์ติดต่อญาติ : "+sharedPreferences.getString("teletc",null));
        tv_personalid.setText("รหัสบัตรประชาชน : "+sharedPreferences.getString("personalid",null));
        tv_birthday.setText("วันเกิด : "+sharedPreferences.getString("birthday",null));
        tv_address.setText("ที่อยู่ : "+sharedPreferences.getString("address",null));
        tv_disease.setText("โรคประจำตัว : "+sharedPreferences.getString("disease",null));
        tv_bloodgroup.setText("กลุ๊ปเลือด : "+sharedPreferences.getString("bloodgroup",null));
        tv_allergic.setText("ยาที่แพ้ : "+sharedPreferences.getString("allergic",null));
        tv_security.setText("สิทธิประกันสังคม : "+sharedPreferences.getString("security",null));
    }


}
