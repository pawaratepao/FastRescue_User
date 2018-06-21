package com.example.pawar.fastrescue_user.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.ServerResponse;
import com.example.pawar.fastrescue_user.manager.ActivityResultBus;
import com.example.pawar.fastrescue_user.manager.ActivityResultEvent;
import com.example.pawar.fastrescue_user.manager.Contextor;
import com.example.pawar.fastrescue_user.manager.HttpMeneger;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.squareup.otto.Subscribe;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class EmergencyFragment extends Fragment {
    Button btgetPhoto, btsendemer;
    EditText emer_detail, ed_other;
    Spinner emeritem_spinner;
    String sEmergency, sEmergencyDetail, sEmergencyStatus;
    RadioButton rb_me, rb_nome;
    CameraImagePicker imagePicker;
    ImagePickerCallback imagePickerCallback;
    String outputPath, noti_id;
    RequestBody requestBody, filename;
    MultipartBody.Part fileToUpload;
    ProgressDialog progress;
    ImageView im_emer;


    public interface EmergencyFragmentListener {
        void onGetPhotoClicked(String noti);

        void onSendEmergency(String sEmergency, String sEmergencyDetail, String sEmergencyStatus);

    }

    public EmergencyFragment() {
        super();
    }

    public static EmergencyFragment newInstance() {
        EmergencyFragment fragment = new EmergencyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emergency, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        im_emer = (ImageView) rootView.findViewById(R.id.emer_image);
        btsendemer = (Button) rootView.findViewById(R.id.btsendemer);
        btsendemer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sEmergency == null) {
                    sEmergency = ed_other.getText().toString();
                }
                Toast.makeText(getActivity(), sEmergency, Toast.LENGTH_SHORT).show();
                sEmergencyDetail = emer_detail.getText().toString();
                EmergencyFragmentListener listener = (EmergencyFragmentListener) getActivity();
                listener.onSendEmergency(sEmergency, sEmergencyDetail, sEmergencyStatus);

            }
        });

        btgetPhoto = (Button) rootView.findViewById(R.id.btgetPhoto);
        btgetPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker = new CameraImagePicker(getActivity());
                imagePicker.setImagePickerCallback(new ImagePickerCallback() {
                                                       @Override
                                                       public void onImagesChosen(List<ChosenImage> images) {

                                                           String path = images.get(0).getOriginalPath();
                                                           File imageFileName = new File(path);


                                                           requestBody = RequestBody.create(MediaType.parse("image/jpeg"), imageFileName);
                                                           fileToUpload = MultipartBody.Part.createFormData("file", imageFileName.getName(), requestBody);
                                                           filename = RequestBody.create(MediaType.parse("text/plain"), imageFileName.getName());
                                                           if(imageFileName.exists()){

                                                               Bitmap myBitmap = BitmapFactory.decodeFile(path);
                                                               im_emer.setImageBitmap(myBitmap);
                                                               btgetPhoto.setText("เพิ่มรูปถ่าย");

                                                           }
                                                           callServerUploadImageProfile(fileToUpload, filename);

                                                           // Display images
                                                       }

                                                       @Override
                                                       public void onError(String message) {
                                                           // Do error handling
                                                       }
                                                   }
                );
// imagePicker.shouldGenerateMetadata(false); // Default is true
// imagePicker.shouldGenerateThumbnails(false); // Default is true
                outputPath = imagePicker.pickImage();
            }



        });


        rb_me = (RadioButton) rootView.findViewById(R.id.rb_me);
        rb_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sEmergencyStatus = "ผู้ประสบเหตุ";

            }
        });
        rb_nome = (RadioButton) rootView.findViewById(R.id.rb_nome);
        rb_nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sEmergencyStatus = "ผู้อยู่ในเหตุการณ์";

            }
        });

        ed_other = (EditText) rootView.findViewById(R.id.ed_other);
        ed_other.setVisibility(rootView.INVISIBLE);
        emer_detail = (EditText) rootView.findViewById(R.id.emer_detail);
        emeritem_spinner = (Spinner) rootView.findViewById(R.id.emeritem_spinner);
        String[] itemEmer = getResources().getStringArray(R.array.emer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, itemEmer);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        emeritem_spinner.setAdapter(adapter);
        emeritem_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (emeritem_spinner.getItemAtPosition(position).toString().equalsIgnoreCase("อุบัติเหตุอื่น ๆ")) {
                    ed_other.setVisibility(View.VISIBLE);
                    sEmergency = null;

                } else {
                    ed_other.setVisibility(View.INVISIBLE);
                    sEmergency = parent.getItemAtPosition(position).toString();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode ==RESULT_OK)

        {
            if (requestCode == Picker.PICK_IMAGE_CAMERA) {
                if (imagePicker == null) {
                    imagePicker = new CameraImagePicker(getActivity());
                    imagePicker.reinitialize(outputPath);
                    // OR in one statement
                    // imagePicker = new CameraImagePicker(Activity.this, outputPath);
                    imagePicker.setImagePickerCallback(imagePickerCallback);
                }
                imagePicker.submit(data);
                //Toast.makeText(this, outputPath, Toast.LENGTH_LONG).show();
            }

        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        ActivityResultBus.getInstance().register(mActivityResultSubscriber);
    }

    @Override
    public void onStop() {
        super.onStop();
        ActivityResultBus.getInstance().unregister(mActivityResultSubscriber);
    }

    private Object mActivityResultSubscriber = new Object() {
        @Subscribe
        public void onActivityResultReceived(ActivityResultEvent event) {
            int requestCode = event.getRequestCode();
            int resultCode = event.getResultCode();
            Intent data = event.getData();
            onActivityResult(requestCode, resultCode, data);
        }
    };
    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("picker_path", outputPath);
        super.onSaveInstanceState(outState);
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

    private void callServerUploadImageProfile(MultipartBody.Part fileToUpload, RequestBody filename) {
        progress = ProgressDialog.show(getActivity(), "โปรดรอสักครู่",
                "Loading...", true);
        Call<ServerResponse> call = HttpMeneger.getInstance().getService().updateImageProfile(fileToUpload, filename);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse.getSuccess() == true) {
                        Toast.makeText(getActivity(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        noti_id = serverResponse.getId();
                        Toast.makeText(getActivity(), "ID: " + serverResponse.getId(), Toast.LENGTH_SHORT).show();
                        EmergencyFragmentListener listener = (EmergencyFragmentListener)getActivity();
                        listener.onGetPhotoClicked(noti_id);
                        progress.dismiss();

                    } else {
                        Toast.makeText(getActivity(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Log.v("Response", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),
                        "123" + t.toString(),
                        Toast.LENGTH_LONG).show();
                progress.dismiss();
                // handle error.
            }
        });
    }

}
