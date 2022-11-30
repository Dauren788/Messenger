package com.example.chatproject.ui;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
        this.inflatedView = inflater.inflate(R.layout.)
        Intent intent = getActivity().getIntent();
//        imgPicker = requireView().findViewById(R.id.button_profile);
//        captureImage = getView().findViewById(R.id.profile_image);
        imgPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(ProfileFragment.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080,1080 )
                        .start();
                startActivityForResult(intent, 101);
            }
        });

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView emailField = rootView.findViewById(R.id.logged_user_email);
        emailField.setText(MainActivity.loggedUser.getUser().getEmail());

        TextView phoneField = rootView.findViewById(R.id.logged_user_phone);
        phoneField.setText(MainActivity.loggedUser.getUser().getPhone());

        TextView nameAndSurname = rootView.findViewById(R.id.logged_user_name_surname);
        nameAndSurname.setText(MainActivity.loggedUser.getUser().getSurname() + " " + MainActivity.loggedUser.getUser().getName());



        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if(requestCode == 101 && resultCode == Activity.RESULT_OK){
        uri = data.getData();
        captureImage.setImageURI(uri);
//        } else {
//            Toast.makeText(getActivity().getApplicationContext(),"no image selected", Toast.LENGTH_SHORT).show();
//        }
    }
}