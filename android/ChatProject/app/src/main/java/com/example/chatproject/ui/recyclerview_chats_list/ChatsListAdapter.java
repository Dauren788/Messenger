package com.example.chatproject.ui.recyclerview_chats_list;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatproject.R;
import com.example.chatproject.data.model.ChatMessage;
import com.example.chatproject.ui.recyclerview_contract.RecyclerViewInterface;

import java.util.List;

public class ChatsListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements RecyclerViewInterface {
    Context context;
    List<ChatMessage> chatsList;
    private final RecyclerViewInterface recyclerViewInterface;

    public ChatsListAdapter(Context context, List<ChatMessage> chatsList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.chatsList = chatsList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_chats_list_view, parent, false);
        return new RecyclerViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ChatMessage chatMessage = chatsList.get(position);
        holder.profileUserName.setText(chatMessage.getProfileUsername());
        holder.userMessage.setText(chatMessage.getMessageText());
        holder.sendDate.setText(chatMessage.getMessageSendDate());
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }

    @Override
    public void onItemClick(int position) {

    }
}