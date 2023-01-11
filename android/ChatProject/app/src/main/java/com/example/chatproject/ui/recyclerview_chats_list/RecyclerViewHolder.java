package com.example.chatproject.ui.recyclerview_chats_list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatproject.MainActivity;
import com.example.chatproject.R;
import com.example.chatproject.ui.recyclerview_contract.RecyclerViewInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView profileUserName, userMessage, sendDate;
    public ImageView profileImage;

    public RecyclerViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        profileUserName = itemView.findViewById(R.id.user_profile);
        sendDate = itemView.findViewById(R.id.send_date);
        userMessage = itemView.findViewById(R.id.user_message);
        profileImage = itemView.findViewById(R.id.chat_user_profile_img);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            }
        });
    }
}