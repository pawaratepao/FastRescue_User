package com.example.pawar.fastrescue_user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pawar.fastrescue_user.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btcommit;
    RadioButton male, female;
    Spinner bloodgroup_spinner;
    EditText etUsername, etPassword, etFirstName, etLastName, etTel, etTeletc,
            etPersonalId, etBirthday, etAddress, etDisease, etBloodGroup, etAllergic, etSecurity;
    String username, password, firstname, lastname, tel, teletc, personalid, birthday, address,
            disease, bloodgroup, allergic, security, sex;
    private static final String URL = "http://pohtecktung.welovepc.com/newproject/android/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initInstances();
        findText();


    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("สมัครสมาชิก");
        btcommit = (Button) findViewById(R.id.btregiscommit);
        btcommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textToString();
                onButtonClick();

            }
        });

        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "ชาย";
                Toast.makeText(RegisterActivity.this,sex,Toast.LENGTH_SHORT).show();

            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "หญิง";
                Toast.makeText(RegisterActivity.this,sex,Toast.LENGTH_SHORT).show();
            }
        });
        bloodgroup_spinner = (Spinner) findViewById(R.id.bloodgroup_spinner);
        String[] itemBlood = getResources().getStringArray(R.array.bloodgroup);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item, itemBlood);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bloodgroup_spinner.setAdapter(adapter);
        bloodgroup_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bloodgroup = parent.getItemAtPosition(position).toString();
                //Toast.makeText(RegisterActivity.this,bloodgroup,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }


    public void findText() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etFirstName = (EditText) findViewById(R.id.userFirstname);
        etLastName = (EditText) findViewById(R.id.userLastname);
        etTel = (EditText) findViewById(R.id.userTel);
        etTeletc = (EditText) findViewById(R.id.userTelETC);
        etPersonalId = (EditText) findViewById(R.id.userPersonalID);
        etBirthday = (EditText) findViewById(R.id.userBirthday);
        etAddress = (EditText) findViewById(R.id.userAddress);
        etDisease = (EditText) findViewById(R.id.userDisease);
        etAllergic = (EditText) findViewById(R.id.userAllergic);
        etSecurity = (EditText) findViewById(R.id.userSecurity);

    }

    public void textToString() {

        username = "user_"+etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        firstname = etFirstName.getText().toString().trim();
        lastname = etLastName.getText().toString().trim();
        tel = etTel.getText().toString().trim();
        teletc = etTeletc.getText().toString().trim();
        personalid = etPersonalId.getText().toString().trim();
        birthday = etBirthday.getText().toString().trim();
        address = etAddress.getText().toString().trim();
        disease = etDisease.getText().toString().trim();
        allergic = etAllergic.getText().toString().trim();
        security = etSecurity.getText().toString().trim();

    }

    private void onButtonClick() {
        if (username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || tel.isEmpty() || personalid.isEmpty() || birthday.isEmpty() ||
                address.isEmpty() || bloodgroup.isEmpty()) {
            Toast.makeText(this, "กรุณากรอกข้อมูลจำเป็นให้ครบทุกช่อง", Toast.LENGTH_LONG).show();
        } else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("onResponse", response);
                    Toast.makeText(RegisterActivity.this, "เพิ่มข้อมูลแล้วจ้า", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onError", error.toString());
                    Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegisterActivity.this, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_username", username);
                    params.put("user_password", password);
                    params.put("user_firstname", firstname);
                    params.put("user_lastname", lastname);
                    params.put("user_sex", sex);
                    params.put("user_tel", tel);
                    params.put("user_teletc", teletc);
                    params.put("user_personalid", personalid);
                    params.put("user_birthday", birthday);
                    params.put("user_address", address);
                    params.put("user_disease", disease);
                    params.put("user_bloodgroup", bloodgroup);
                    params.put("user_allergic", allergic);
                    params.put("user_security", security);


                    return params;
                }
            };
            requestQueue.add(request);
        }
    }
}