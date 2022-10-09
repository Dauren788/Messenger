package com.example.chatproject.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatproject.MainActivity;
import com.example.chatproject.R;
import com.example.chatproject.data.model.ChatMessage;
import com.example.chatproject.ui.recyclerview_chats_list.ChatsListAdapter;
import com.example.chatproject.ui.recyclerview_chatting.ChattingAdapter;
import com.example.chatproject.ui.recyclerview_contract.RecyclerViewInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ChatsFragment extends Fragment implements RecyclerViewInterface {
    public static ArrayList<ChatMessage> chatsList;
    RecyclerView recyclerView;

    public ChatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();

        String json = "{\"type\":0}";
        MainActivity.wsListener.ws.send(json);

        recyclerView = view.findViewById(R.id.chats_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        ChatsListAdapter adapter = new ChatsListAdapter(context, chatsList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        String conversationId = chatsList.get(position).getConversationId();

        String json = "{\"type\":2, \"conversation_id\":\"" + conversationId + "\"}";

        MainActivity.wsListener.ws.send(json);

        Fragment newFragment = new ChattingFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, newFragment);
        ft.commit();
    }
}