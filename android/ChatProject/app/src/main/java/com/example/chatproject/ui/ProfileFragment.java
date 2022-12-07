package com.example.chatproject.ui;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatproject.MainActivity;
import com.example.chatproject.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        view.findViewById(R.id.profile).setOnClickListener(this);
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    FloatingActionButton imgPicker;
    String path;
    Uri uri;
    private ImageView captureImage;
    View inflatedView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView emailField = rootView.findViewById(R.id.logged_user_email);
        emailField.setText(MainActivity.loggedUser.getUser().getEmail());

        TextView phoneField = rootView.findViewById(R.id.logged_user_phone);
        phoneField.setText(MainActivity.loggedUser.getUser().getPhone());

        TextView nameAndSurname = rootView.findViewById(R.id.logged_user_name_surname);
        nameAndSurname.setText(MainActivity.loggedUser.getUser().getSurname() + " " + MainActivity.loggedUser.getUser().getName());

       // this.inflatedView = inflater.inflate(R.layout.fragment_profile, container, false);
        imgPicker = (FloatingActionButton)rootView.findViewById(R.id.button_profile);
        captureImage = rootView.findViewById(R.id.profile_image);
        Intent intent = getPickIntent(uri);//getActivity().getIntent();
//        imgPicker = requireView().findViewById(R.id.button_profile);
//        captureImage = getView().findViewById(R.id.profile_image);
        imgPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity().getApplicationContext(),"no image selected", Toast.LENGTH_SHORT).show();
//                ImagePicker.with(ProfileFragment.this)
//                        .crop()
//                        .compress(1024)
//                        .maxResultSize(1080,1080 )
//                        .start();
                File file = getActivity().getExternalFilesDir(Environment.DIRECTORY_DCIM);
                Uri cameraOutputUri = Uri.fromFile(file);
                Intent intent = getPickIntent(cameraOutputUri);
                startActivityResult.launch(intent);
             //   startActivityResult.launch(intent);
                //startActivityForResult(intent, 101);
            }
        });
        return rootView;
    }
    private Intent getPickIntent(Uri cameraOutputUri) {
        final List<Intent> intents = new ArrayList<Intent>();

        if (true) {
            intents.add(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        }

        if (true) {
            setCameraIntents(intents, cameraOutputUri);
        }

        if (intents.isEmpty()) return null;
        Intent result = Intent.createChooser(intents.remove(0), null);
        if (!intents.isEmpty()) {
            result.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents.toArray(new Parcelable[] {}));
        }
        return result;


    }

    private void setCameraIntents(List<Intent> cameraIntents, Uri output) {
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getActivity().getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
            cameraIntents.add(intent);
        }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//     //   super.onActivityResult(requestCode, resultCode, data);
//        //if(requestCode == 101 && resultCode == Activity.RESULT_OK){
//        uri = data.getData();
//        captureImage.setImageURI(uri);
////        } else {
////            Toast.makeText(getActivity().getApplicationContext(),"no image selected", Toast.LENGTH_SHORT).show();
////        }
//    }
    }



    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                       // doSomeOperations();
                        uri = data.getData();
                        captureImage.setImageURI(uri);
                    }
                }
            });

}