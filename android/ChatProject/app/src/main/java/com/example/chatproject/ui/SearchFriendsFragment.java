package com.example.chatproject.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.chatproject.MainActivity;
import com.example.chatproject.R;
import com.example.chatproject.data.model.ChatMessage;
import com.example.chatproject.data.model.LoggedInUser;
import com.example.chatproject.data.model.SearchUser;
import com.example.chatproject.ui.recyclerview_chatting.ChattingAdapter;
import com.example.chatproject.ui.recyclerview_search_friend.SearchFriendAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.ArrayList;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFriendsFragment extends Fragment {
    private String endpoint = "http://10.0.2.2:8080/friends/search/";
    private static SearchFriendAdapter adapter;
    RecyclerView recyclerView;
    Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFriendsFragment newInstance(String param1, String param2) {
        SearchFriendsFragment fragment = new SearchFriendsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_friends, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText searchEditText = view.findViewById(R.id.search_user_edittext);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getUsersList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        context = getContext();

        recyclerView = view.findViewById(R.id.search_users_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        adapter = new SearchFriendAdapter(context, null);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getUsersList(String searchStr) {
        System.out.println();
        try {
//            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//            RequestBody body = RequestBody.create(JSON, "\"searchString\":\"" + searchStr + "\"");
//
//            Request request  = new Request.Builder()
//                    .url(endpoint).post(body).get()
//                    .build();

            String url = HttpUrl.parse(endpoint).newBuilder()
                    .addQueryParameter("searchString", searchStr)
                    .build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            OkHttpClient client = new OkHttpClient();
            ResponseBody responseBody = client.newCall(request).execute().body();

            Gson gson = new Gson();
            Type arrayListType =  new TypeToken<ArrayList<SearchUser>>(){}.getType();
            ArrayList<SearchUser> responseEntity = gson.fromJson(responseBody.string(), arrayListType);

            adapter = new SearchFriendAdapter(context, responseEntity);
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            adapter.notifyDataSetChanged();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}