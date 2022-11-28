package com.example.chatproject.ui.recyclerview_search_friend;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatproject.R;
import com.example.chatproject.ui.recyclerview_contract.RecyclerViewInterface;

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView profileUserName, name;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        profileUserName = itemView.findViewById(R.id.tvUsername);
        name = itemView.findViewById(R.id.tvName);
    }

}