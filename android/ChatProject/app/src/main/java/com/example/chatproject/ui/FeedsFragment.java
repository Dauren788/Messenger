package com.example.chatproject.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatproject.R;

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
        //setContentView(R.layout.fragment_feeds);
        //feedRecycler = findViewById(R.id.feedRecycler);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        arrayList = new ArrayList<>();
        feedRecycler = getView().findViewById(R.id.feedRecycler);
        arrayList.add(new Feed(R.drawable.ic_launcher_background, R.drawable.feed_image, "title", "message"));
        FeedRecyclerAdapter recyclerAdapter = new FeedRecyclerAdapter(arrayList);
        feedRecycler.setAdapter(recyclerAdapter);
        feedRecycler.setLayoutManager(new LinearLayoutManager(context));
        return inflater.inflate(R.layout.fragment_feeds, container, false);
    }

}