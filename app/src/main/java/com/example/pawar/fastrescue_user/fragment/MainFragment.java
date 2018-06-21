package com.example.pawar.fastrescue_user.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pawar.fastrescue_user.R;


public class MainFragment extends Fragment {
    String uusername, upassword,ousername, opassword;
    EditText loginusername, loginpassword;
    Button btuserlogin, btofficiallogin;
    Button btregister;

    public interface FragmentListener {
        void onUserLoginClicked(String susername, String spassword);
        void onRegisterClicked();
    }

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        loginusername = (EditText) rootView.findViewById(R.id.loginusername);
        loginpassword = (EditText) rootView.findViewById(R.id.loginpassword);
        btuserlogin = (Button) rootView.findViewById(R.id.btuserlogin);
        btuserlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uusername = loginusername.getText().toString().trim();
                upassword = loginpassword.getText().toString().trim();
                FragmentListener listener = (FragmentListener) getActivity();
                listener.onUserLoginClicked(uusername, upassword);


            }
        });


        btregister = (Button) rootView.findViewById(R.id.btregister);
        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentListener listener = (FragmentListener) getActivity();
                listener.onRegisterClicked();

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
