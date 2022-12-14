package com.example.chatproject.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chatproject.MainActivity;
import com.example.chatproject.R;
import com.example.chatproject.data.model.ChatMessage;
import com.example.chatproject.ui.recyclerview_chats_list.ChatsListAdapter;
import com.example.chatproject.ui.recyclerview_chatting.ChattingAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChattingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChattingFragment extends Fragment {
    public static ArrayList<ChatMessage> chatMessages;
    public static ChattingAdapter adapter;
    private String urlGetImage = "http://10.0.2.2:8080/img/";
    RecyclerView recyclerView;
    private String conversationID;
    private String profileImage;
    private String userName;
    long timeout = 1000;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String test;

    public ChattingFragment() {
        // Required empty public constructor
    }

    public ChattingFragment(String conversationID, String profileImage, String userName) {
        this.conversationID = conversationID;
        this.profileImage = profileImage;
        this.userName = userName;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChattingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChattingFragment newInstance(String param1, String param2) {
        System.out.println(param1 + " " + param2);
        ChattingFragment fragment = new ChattingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();

        recyclerView = view.findViewById(R.id.chat_messages_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        adapter = new ChattingAdapter(context, chatMessages);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        adapter.notifyDataSetChanged();

        setSendMessageListener(conversationID, context, view);
    }

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
        final View rootView = inflater.inflate(R.layout.fragment_chatting, container, false);

        TextView usernameTextView = rootView.findViewById(R.id.conversation_username);
        usernameTextView.setText(userName);

        ImageView profileImageView = rootView.findViewById(R.id.user_profile_img);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getProfileImageBytes(this.profileImage));
            profileImageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, profileImageView.getDrawable().getIntrinsicWidth(), profileImageView.getDrawable().getIntrinsicHeight(), false));
        } catch (Exception e) {
            Log.e("Create file error : ", e.getMessage());
        }

        return rootView;
    }

    private void setSendMessageListener (String conversationID, Context context, View view) {
        Button sendBtn = (Button) view.findViewById(R.id.chat_send_msg);
        EditText messageField = (EditText) view.findViewById(R.id.chat_input_msg);

        sendBtn.setOnClickListener(View -> {
            String json = "{\"type\":1, \"conversation_id\":\"" + conversationID + "\", \"text\":\"" + messageField.getText().toString() + "\"}";
            MainActivity.wsListener.ws.send(json);
            messageField.getText().clear();
        });
    }

    private InputStream getProfileImageBytes(String fileName) {
        try {
            String url = Objects.requireNonNull(HttpUrl.parse(urlGetImage + fileName )).newBuilder()
                    .build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            OkHttpClient client = new OkHttpClient();
            ResponseBody responseBody = client.newCall(request).execute().body();

            return responseBody.byteStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}