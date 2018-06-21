package com.example.pawar.fastrescue_user.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pawar.fastrescue_user.R;


public class MemDetailEditFragment extends Fragment {
    Button bteditcommit;
    EditText ud_firstname, ud_lastname, ud_tel, ud_teletc, ud_personalid, ud_birthday, ud_address, ud_disease,
            ud_bloodgroup, ud_allergic, ud_security;
    String username,firstname, lastname, tel, teletc, personalid, birthday, address, disease,
            bloodgroup, allergic, security;

    public interface MemDetailEditFragmentListener {
        void EditCommitClicked(String username,String firstname, String lastname, String tel, String teletc, String personalid, String birthday,
                               String address, String disease, String bloodgroup, String allergic, String security);
    }

    public MemDetailEditFragment() {
        super();
    }

    public static MemDetailEditFragment newInstance() {
        MemDetailEditFragment fragment = new MemDetailEditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mem_edit, container, false);
        initInstances(rootView);
        restoreData();
        return rootView;
    }

    private void initInstances(View rootView) {
        ud_firstname = (EditText) rootView.findViewById(R.id.ud_firstname);
        ud_lastname = (EditText) rootView.findViewById(R.id.ud_lastname);
        ud_tel = (EditText) rootView.findViewById(R.id.ud_tel);
        ud_teletc = (EditText) rootView.findViewById(R.id.ud_teletc);
        ud_personalid = (EditText) rootView.findViewById(R.id.ud_personalid);
        ud_birthday = (EditText) rootView.findViewById(R.id.ud_birthday);
        ud_address = (EditText) rootView.findViewById(R.id.ud_address);
        ud_disease = (EditText) rootView.findViewById(R.id.ud_disease);
        ud_bloodgroup = (EditText) rootView.findViewById(R.id.ud_bloodgroup);
        ud_allergic = (EditText) rootView.findViewById(R.id.ud_allergic);
        ud_security = (EditText) rootView.findViewById(R.id.ud_security);

        bteditcommit = (Button) rootView.findViewById(R.id.bteditcommit);
        bteditcommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToString();
                MemDetailEditFragmentListener listener = (MemDetailEditFragmentListener) getActivity();
                listener.EditCommitClicked(username,firstname, lastname, tel, teletc, personalid, birthday,
                        address, disease, bloodgroup, allergic, security);

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

    public void restoreData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("PREF_Login", getContext().MODE_PRIVATE);
        ud_firstname.setText(sharedPreferences.getString("firstname", null));
        ud_lastname.setText(sharedPreferences.getString("lastname", null));
        ud_tel.setText(sharedPreferences.getString("tel", null));
        ud_teletc.setText(sharedPreferences.getString("teletc", null));
        ud_personalid.setText(sharedPreferences.getString("personalid", null));
        ud_birthday.setText(sharedPreferences.getString("birthday", null));
        ud_address.setText(sharedPreferences.getString("address", null));
        ud_disease.setText(sharedPreferences.getString("disease", null));
        ud_bloodgroup.setText(sharedPreferences.getString("bloodgroup", null));
        ud_allergic.setText(sharedPreferences.getString("allergic", null));
        ud_security.setText(sharedPreferences.getString("security", null));
        username = sharedPreferences.getString("username",null);
    }

    public void textToString() {
        firstname = ud_firstname.getText().toString();
        lastname = ud_lastname.getText().toString();
        tel = ud_tel.getText().toString();
        teletc = ud_teletc.getText().toString();
        personalid = ud_personalid.getText().toString();
        birthday = ud_birthday.getText().toString();
        address = ud_address.getText().toString();
        disease = ud_disease.getText().toString();
        bloodgroup = ud_bloodgroup.getText().toString();
        allergic = ud_allergic.getText().toString();
        security = ud_security.getText().toString();

    }


}
