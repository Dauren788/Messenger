package com.example.chatproject.ui.recyclerview_search_friend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chatproject.R;
import com.example.chatproject.data.model.ChatMessage;
import com.example.chatproject.data.model.Profile;
import com.example.chatproject.data.model.SearchUser;

import java.util.ArrayList;
import java.util.List;

public class SearchFriendAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    Context context;
    List<SearchUser> userList;

    public SearchFriendAdapter(Context context, List<SearchUser> usersList) {
        this.context = context;
        this.userList = usersList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.search_user, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        SearchUser user = this.userList.get(position);

        String username = user.getUsername();
        String name = user.getName();

        holder.profileUserName.setText(username);
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        if (userList!=null) {
            return userList.size();
        }

        return 0;
    }

}