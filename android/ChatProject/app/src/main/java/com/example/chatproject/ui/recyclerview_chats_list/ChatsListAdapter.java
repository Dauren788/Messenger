package com.example.chatproject.ui.recyclerview_chats_list;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatproject.R;
import com.example.chatproject.data.model.ChatMessage;
import com.example.chatproject.ui.recyclerview_contract.RecyclerViewInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class ChatsListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements RecyclerViewInterface {
    Context context;
    private String urlGetImage = "http://10.0.2.2:8080/img/";
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

        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getProfileImageBytes(chatMessage.getProfileImage()));
            holder.profileImage.setImageBitmap(Bitmap.createScaledBitmap(bitmap, holder.profileImage.getDrawable().getIntrinsicWidth(), holder.profileImage.getDrawable().getIntrinsicHeight(), false));
        } catch (Exception e) {
            Log.e("Create file error : ", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }

    @Override
    public void onItemClick(int position) {

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