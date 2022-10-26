package com.example.chatproject.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatproject.MainActivity;
import com.example.chatproject.R;
import com.example.chatproject.data.model.Feed;
import com.example.chatproject.ui.recyclerview_chats_list.ChatsListAdapter;
import com.example.chatproject.ui.recyclerview_feeds.FeedRecyclerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class FeedsFragment extends Fragment {

    private RecyclerView feedRecycler;
    private ArrayList<Feed>arrayList;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeds, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        arrayList = new ArrayList<>();
        arrayList.add(new Feed(R.drawable.ic_launcher_background, R.drawable.solidlistkov, "Nate Higgers", "Solid principle"));
        arrayList.add(new Feed(R.drawable.ic_launcher_background, R.drawable.rdr2, "Keilni Gears", "Jennider in rdr2"));

        feedRecycler = getView().findViewById(R.id.feedRecycler);
        feedRecycler.setLayoutManager(new LinearLayoutManager(context));
        feedRecycler.setHasFixedSize(true);
        FeedRecyclerAdapter recyclerAdapter = new FeedRecyclerAdapter(arrayList);
        feedRecycler.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }

}